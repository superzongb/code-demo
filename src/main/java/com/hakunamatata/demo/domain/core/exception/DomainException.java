package com.hakunamatata.demo.domain.core.exception;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/4/11
 * @description:
 */
public class DomainException extends Exception {
    public DomainException(String message) {
        super(message);
    }

    public DomainException() {

    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
