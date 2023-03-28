package study.spring.transactional.readonly;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Transactional(readOnly = true)
@Service
public class ReadOnlyService {

    private final NotReadOnlyService notReadOnlyService;

    public ReadOnlyService(NotReadOnlyService notReadOnlyService) {
        this.notReadOnlyService = notReadOnlyService;
    }

    public void test() {
        boolean readonly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        System.out.println(readonly);
        notReadOnlyService.test();
    }
}
