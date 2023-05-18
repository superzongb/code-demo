package com.hakunamatata.demo.application.usecase.command;

import com.hakunamatata.demo.application.dto.PaymentDto;
import org.springframework.stereotype.Service;

@Service
public class PrivateTripChangeCmd {
    public PaymentDto pay(String enterpriseId, Long orderId, Long changeId) {
        return null;
    }
}
