package com.hakunamatata.demo.domain.context.privatetrip.baseorder;

import com.hakunamatata.demo.domain.core.exception.DomainException;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/4/12
 * @description:
 */
public class IllegalOrderStateException extends DomainException {
    public IllegalOrderStateException(String message, AbstractOrderState state) {
        super(message + " Current state is: " + state.toString());
    }

}
