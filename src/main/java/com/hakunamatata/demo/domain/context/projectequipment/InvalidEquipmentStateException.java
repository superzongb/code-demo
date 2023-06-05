package com.hakunamatata.demo.domain.context.projectequipment;

import com.hakunamatata.demo.domain.core.exception.DomainRuntimeException;

public class InvalidEquipmentStateException extends DomainRuntimeException {
    public InvalidEquipmentStateException(String message) {
        super(message);
    }
}
