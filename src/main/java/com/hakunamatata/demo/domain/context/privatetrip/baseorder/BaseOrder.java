package com.hakunamatata.demo.domain.context.privatetrip.baseorder;

import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PaymentBalanceInsufficientException;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PaymentFailedException;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.Payment;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PaymentConfirmation;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.PurchaseService;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/21
 * @description:
 */
@SuperBuilder
public abstract class BaseOrder {
    @Getter
    protected final long id;

    @Builder.Default
    protected BigDecimal totalAmount = BigDecimal.ZERO;

    protected String description;

    @Getter
    protected final String ownerId;

    List<Payment> payments;

    LocalDateTime createAt;

    public void submit() throws IllegalOrderStateException {
        this.createAt = LocalDateTime.now();
    }

    protected void increaseAmount(BigDecimal amount) {
        this.totalAmount = this.totalAmount.add(amount);
    }

    public void pay(PurchaseService service) throws IllegalOrderStateException {
        Optional.ofNullable(payments)
                .orElseGet(() -> {
                    this.payments = new ArrayList<>();
                    return payments;
                })
                .add(service.pay(this.id, this.totalAmount, this.description));
    }

    public void paid(PaymentConfirmation confirmation) throws PaymentBalanceInsufficientException, PaymentFailedException, IllegalOrderStateException {
        this.payments.stream()
                .filter(p -> p.getId() == confirmation.getPaymentId())
                .findAny()
                .orElseThrow(InvalidParameterException::new)
                .confirm(confirmation);
    }

    protected boolean isTimeout() {
        return this.payments.stream()
                .filter(payment -> payment.getConfirmation() == null)
                .findAny()
                .orElseThrow(RuntimeException::new)
                .getCreateAt().plusMinutes(30).isBefore(LocalDateTime.now());
    }

    public Payment getPayment() {
        return payments.stream()
                .filter(payment -> payment.getConfirmation() == null)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
