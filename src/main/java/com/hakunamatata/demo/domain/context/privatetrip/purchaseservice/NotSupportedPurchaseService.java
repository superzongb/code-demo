package com.hakunamatata.demo.domain.context.privatetrip.purchaseservice;

import com.hakunamatata.demo.domain.core.exception.DomainRuntimeException;

public class NotSupportedPurchaseService extends DomainRuntimeException {
    public NotSupportedPurchaseService(String message) {
        super(message);
    }
}
