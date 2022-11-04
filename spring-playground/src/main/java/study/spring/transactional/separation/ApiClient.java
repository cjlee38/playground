package study.spring.transactional.separation;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public void request() {

    }
}
