package com.hakunamatata.demo.domain.context.privatetrip.purchaseservice;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/21
 * @description:
 */
@Getter
@SuperBuilder
public class PaymentConfirmation {
    private final long id;
    private final long orderId;
    private final long paymentId;
    private final LocalDateTime createAt;
    private final BigDecimal amount;

    private final ConfirmType confirmType;
}
