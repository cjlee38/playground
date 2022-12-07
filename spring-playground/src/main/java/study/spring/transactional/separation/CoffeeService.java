package study.spring.transactional.separation;

import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@AllArgsConstructor
@Transactional
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;
    private final ApiClient apiClient;

    /**
     * spring.datasource.hikari.auto-commit: false
     * spring.jpa.properties.hibernate.connection.provider_disables_autocommit: true -> setAutoCommit을 false로 설정해준다.
     * (스프링부트 2버전부터는 auto-commit을 false로 설정하면 hibernate 설정이 자동으로 설정된다.) 이 떄, getConnectionForTransasctionManagement() 를
     * 수행하는 과정에서 DB connection의 획득을 나중으로 미룬다.
     *
     * @Override public void begin() { initiallyAutoCommit = !doConnectionsFromProviderHaveAutoCommitDisabled() &&
     * determineInitialAutoCommitMode( getConnectionForTransactionManagement() ); super.begin(); }
     * <p>
     * 위 메소드를 실행할 때, short circuit으로 인해 앞쪽만 수행되고, 뒤쪽 메소드가 실행되지 않는다. 즉, getConnectionForTransactionManagement()를 수행하지 않아서
     * 물리적인 DB connection을 얻지 않는다.
     * <p>
     * https://kwonnam.pe.kr/wiki/java/hibernate/performance
     * https://pkgonan.github.io/2019/01/hibrnate-autocommit-tuning
     */
    public void makeCoffee(int amount) {
        /*
        하지만 실제로 커넥션 획득을 미루는지 체크하는데는 실패했다. 다른 방법을 찾아보자.
         */

        Map<Object, Object> resourceMap = TransactionSynchronizationManager.getResourceMap();
        System.out.println("resourceMap = " + resourceMap);
        System.out.println(
                "actualTransactionActive = " + TransactionSynchronizationManager.isActualTransactionActive());
        Optional<Coffee> findCoffee = coffeeRepository.findById(1L);
        System.out.println("findCoffee.isPresent() = " + findCoffee.isPresent());
        System.out.println(
                "actualTransactionActive = " + TransactionSynchronizationManager.isActualTransactionActive());
        Coffee coffee = new Coffee(null, amount);
        coffeeRepository.save(coffee);
    }
}
