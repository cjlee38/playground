package study.util;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityManagerUtils {

    @Autowired
    private EntityManager entityManager;

    public void init() {
        System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=entity manager init start *=*=*=*=*=*=*=*=*=*=*=*=*");
        entityManager.flush();
        entityManager.clear();
        System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=entity manager init end *=*=*=*=*=*=*=*=*=*=*=*=*");
    }
}
