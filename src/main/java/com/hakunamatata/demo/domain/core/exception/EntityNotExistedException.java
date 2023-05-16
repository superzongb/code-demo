package com.hakunamatata.demo.domain.core.exception;

/**
 * @authoer: Hakuna Matata
 * @createDate: 2023/4/13
 * @description:
 */
public class EntityNotExistedException extends DomainRuntimeException {
    public EntityNotExistedException(String message) {
        super(message);
    }
}
