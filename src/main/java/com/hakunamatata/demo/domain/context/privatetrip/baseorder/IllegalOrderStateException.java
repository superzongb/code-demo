package com.hakunamatata.demo.domain.context.privatetrip.baseorder;

import com.hakunamatata.demo.domain.core.exception.DomainRuntimeException;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/4/12
 * @description:
 */
public class IllegalOrderStateException extends DomainRuntimeException {
    public IllegalOrderStateException(String message, AbstractOrderState state) {
        super(message + " Current state is: " + state.toString());
    }

}
