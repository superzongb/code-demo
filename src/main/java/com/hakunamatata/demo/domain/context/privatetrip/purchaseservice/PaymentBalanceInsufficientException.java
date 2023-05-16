package com.hakunamatata.demo.domain.context.privatetrip.purchaseservice;

import com.hakunamatata.demo.domain.core.exception.DomainException;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/21
 * @description:
 */
public class PaymentBalanceInsufficientException extends DomainException {
    public PaymentBalanceInsufficientException(String message) {
        super(message);
    }

    public PaymentBalanceInsufficientException() {
        super();
    }
}
