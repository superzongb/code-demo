package com.hakunamatata.demo.adapter.driven.gateway;

import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.Payment;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PurchaseService;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PurchaseServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AliPayService implements PurchaseService {

    private final PurchaseServiceRepository purchaseServiceRepository;

    public AliPayService(PurchaseServiceRepository purchaseServiceRepository) {
        this.purchaseServiceRepository = purchaseServiceRepository;
        purchaseServiceRepository.register(this);
    }

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
