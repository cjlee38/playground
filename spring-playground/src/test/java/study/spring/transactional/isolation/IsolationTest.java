package study.spring.transactional.isolation;

import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import study.util.TestHttpUtils;

@SpringBootTest
@Slf4j
public class IsolationTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("UPDATE member SET name='hello' WHERE name='world'");
    }

    @Test
    void test() throws InterruptedException {
        Thread thread = new Thread(() -> TestHttpUtils.send("/uncommitted"));
        thread.start();

        Thread.sleep(1000);
        jdbcTemplate.execute("UPDATE member SET name='world' WHERE name='hello'");

        thread.join();
    }

    @Test
    void test2() throws InterruptedException {
        Thread thread = new Thread(() -> TestHttpUtils.send("/no-transaction"));
        thread.start();

        Thread.sleep(1000);
        jdbcTemplate.execute("UPDATE member SET name='world' WHERE name='hello'");

        thread.join();
    }

    @Test
    void test3() throws InterruptedException {
        Thread thread = new Thread(() -> TestHttpUtils.send("/repeatable-read"));
        thread.start();

        Thread.sleep(1000);
        jdbcTemplate.execute("UPDATE member SET name='world' WHERE name='hello'");

        thread.join();
    }
}
