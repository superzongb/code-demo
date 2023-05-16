package com.hakunamatata.demo.domain.core.exception;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/4/13
 * @description:
 */
public class IllegalEntityStateException extends DomainRuntimeException {

    public IllegalEntityStateException(String message) {
        super(message);
    }
}
