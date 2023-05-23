package com.hakunamatata.demo.application.usecase.command;

import com.hakunamatata.demo.application.dto.PaymentDto;
import com.hakunamatata.demo.domain.context.privatetrip.ChangeService;
import com.hakunamatata.demo.domain.context.privatetrip.baseorder.IllegalOrderStateException;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrivateTripChangeCmd {

    private final ChangeService changeService;

    public PaymentDto payChangeOrder(String enterpriseId, Long orderId, Long changeId, String purchaseType) {
        Payment payment = changeService.payForChange(enterpriseId, orderId, changeId, purchaseType);
        return new PaymentDto(payment.getId(),
                payment.getCreateAt(),
                payment.getPurchaseType(),
                payment.getAmount(),
                payment.getPaymentUrl());
    }
}
