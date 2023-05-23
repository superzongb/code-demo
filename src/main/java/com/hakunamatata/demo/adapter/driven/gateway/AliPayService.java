package com.hakunamatata.demo.adapter.driven.gateway;

import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.Payment;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PurchaseService;

import java.math.BigDecimal;

public class AliPayService implements PurchaseService {

    public static final String ALI_PAY = "AliPay";

    @Override
    public Payment pay(Long orderId, BigDecimal amount, String description) {
        return null;
    }

    @Override
    public String getType() {
        return ALI_PAY;
    }
}
