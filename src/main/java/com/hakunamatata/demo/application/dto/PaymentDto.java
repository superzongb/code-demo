package com.hakunamatata.demo.application.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentDto {
    private final long id;
    private final LocalDateTime createAt;
    private final String purchaseType;
    private final BigDecimal amount;
    private final String paymentUrl;
}
