package com.hakunamatata.demo.domain.context.privatetrip.privatetriporder;

import com.hakunamatata.demo.domain.context.privatetrip.baseorder.AbstractOrderState;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/3/21
 * @description:
 */
public enum OrderState implements AbstractOrderState {
    DRAFT, WAITING_FOR_PAY, PAYMENT_ONGOING, PAID, TIMEOUT, PAY_FAILED
}
