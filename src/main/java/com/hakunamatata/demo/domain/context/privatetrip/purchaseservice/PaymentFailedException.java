package com.hakunamatata.demo.domain.context.privatetrip.purchaseservice;

import com.hakunamatata.demo.domain.core.exception.DomainException;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/21
 * @description:
 */
public class PaymentFailedException extends DomainException {
    public PaymentFailedException(String message) {
        super(message);
    }

    public PaymentFailedException() {
    }
}
