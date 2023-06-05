package com.hakunamatata.demo.adapter.driven.gateway;

import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.Payment;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PurchaseService;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PurchaseServiceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AliPayService implements PurchaseService {

    public static final String ALI_PAY = "AliPay";
    private final PurchaseServiceRepository purchaseServiceRepository;

    public AliPayService(PurchaseServiceRepository purchaseServiceRepository) {
        this.purchaseServiceRepository = purchaseServiceRepository;
        purchaseServiceRepository.register(this);
    }

    @Override
    public Payment pay(Long orderId, BigDecimal amount, String description) {
        return null;
    }

    @Override
    public String getType() {
        return ALI_PAY;
    }
}
