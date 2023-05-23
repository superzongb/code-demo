package com.hakunamatata.demo.domain.context.privatetrip.trip;

import com.hakunamatata.demo.domain.context.privatetrip.baseorder.AbstractOrderState;

public enum ChangeState implements AbstractOrderState {
    WAITING_FOR_PAY, PAYING, PAID, TIMEOUT, PAY_FAILED
}
