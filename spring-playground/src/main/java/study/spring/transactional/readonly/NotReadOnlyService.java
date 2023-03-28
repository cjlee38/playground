package study.spring.transactional.readonly;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Transactional
@Service
public class NotReadOnlyService {

//    private final ReadOnlyService readOnlyService;
//
//    public NotReadOnlyService(ReadOnlyService readOnlyService) {
//        this.readOnlyService = readOnlyService;
//    }

    public void test() {
        boolean readonly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        System.out.println(readonly);
//        readOnlyService.test();
    }
}
