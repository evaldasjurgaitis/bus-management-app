package ej.config;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class TransactionExecutor {

    @Transactional(propagation = Propagation.REQUIRED)
    public void execute(Runnable operation) {
        operation.run();
    }

}
