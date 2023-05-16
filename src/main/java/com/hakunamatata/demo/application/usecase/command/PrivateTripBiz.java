package com.hakunamatata.demo.application.usecase.command;

import com.hakunamatata.demo.domain.context.privatetrip.PrivateTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/22
 * @description:
 */

@RequiredArgsConstructor
public class PrivateTripBiz {
    private final PlatformTransactionManager transactionManager;

    private final PrivateTripService privateTripService;

    public void confirmOrderPayment() {
        TransactionStatus transaction = transactionManager.getTransaction(null);
        try {
            privateTripService.paid(1L, null);
            transactionManager.commit(transaction);
        } catch (Exception ex) {
            transactionManager.rollback(transaction);
        }
    }
}
