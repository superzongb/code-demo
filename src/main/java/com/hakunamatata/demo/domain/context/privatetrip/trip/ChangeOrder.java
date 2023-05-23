package com.hakunamatata.demo.domain.context.privatetrip.trip;

import com.hakunamatata.demo.domain.context.privatetrip.airlineservice.Flight;
import com.hakunamatata.demo.domain.context.privatetrip.baseorder.BaseOrder;
import com.hakunamatata.demo.domain.context.privatetrip.baseorder.IllegalOrderStateException;
import com.hakunamatata.demo.domain.context.privatetrip.purchaseservice.*;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/21
 * @description:
 */
@SuperBuilder
public class ChangeOrder extends BaseOrder {
    private final Trip cancelTrip;

    private final Flight newFlight;

    @Builder.Default
    @Getter
    private ChangeState state = ChangeState.WAITING_FOR_PAY;

    @Override
    public void pay(PurchaseServiceRepository purchaseServiceRepository, String purchaseType) throws IllegalOrderStateException {
        if (state != ChangeState.WAITING_FOR_PAY) {
            throw new IllegalOrderStateException("Cannot pay.", state);
        }
        super.pay(purchaseServiceRepository, purchaseType);
        state = ChangeState.PAYING;
    }

    @Override
    public void paid(PaymentConfirmation confirmation) throws PaymentBalanceInsufficientException, PaymentFailedException, IllegalOrderStateException {
        super.paid(confirmation);
    }
}
