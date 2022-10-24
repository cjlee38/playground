package study.spring.transactional.invocation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class SpringJava {

    @Transactional
    public void transactionalCaller() {
        boolean transactionActive = TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println("SpringJava.transactionalCaller, active = " + transactionActive);
        nonTransactionalReceiver();
    }

    public void nonTransactionalReceiver() {
        boolean transactionActive = TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println("SpringJava.nonTransactionalReceiver, active = " + transactionActive);
    }

    public void nonTransactionalCaller() {
        boolean transactionActive = TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println("SpringJava.nonTransactionalCaller, active = " + transactionActive);
        transactionalReceiver();
    }

    @Transactional
    public void transactionalReceiver() {
        boolean transactionActive = TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println("SpringJava.transactionalReceiver, active = " + transactionActive);
    }
}
