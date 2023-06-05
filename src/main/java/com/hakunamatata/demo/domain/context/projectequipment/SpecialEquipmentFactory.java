package com.hakunamatata.demo.domain.context.projectequipment;

public interface SpecialEquipmentFactory {
    SpecialEquipment create(CreateEquipmentCommand command);
}
