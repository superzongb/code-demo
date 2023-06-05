package com.hakunamatata.demo.domain.context.projectequipment;


import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CreateEquipmentCommand {
    private final String projectId;

    private final String sn;
}
