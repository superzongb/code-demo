package com.hakunamatata.demo.domain.core.exception;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/4/13
 * @description:
 */
public class DomainRuntimeException extends RuntimeException {
    public DomainRuntimeException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
