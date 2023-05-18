package com.hakunamatata.demo.domain.context.privatetrip.purchaseservice;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/21
 * @description:
 */
@Getter
@SuperBuilder
public class Payment {
    private final long id;
    private final LocalDateTime createAt;
    private final String purchaseType;
    private final BigDecimal amount;
    private final String description;
    private final String paymentUrl;
    private PaymentConfirmation confirmation;


    public void confirm(PaymentConfirmation confirmation) throws PaymentBalanceInsufficientException, PaymentFailedException {
        if (this.id != confirmation.getPaymentId()) {
            throw new InvalidParameterException("Invalid payment id");
        }

        if (!this.amount.equals(confirmation.getAmount())) {
            throw new InvalidParameterException("Invalid amount");
        }

        if (confirmation.getCreateAt().minusMinutes(30).isAfter(this.createAt)) {
            throw new InvalidParameterException("payment time out");
        }

        if (confirmation.getConfirmType() == ConfirmType.BALANCE_INSUFFICIENT) {
            throw new PaymentBalanceInsufficientException();
        }

        if (confirmation.getConfirmType() == ConfirmType.OTHER) {
            throw new PaymentFailedException("payment failed.");
        }
    }

}
