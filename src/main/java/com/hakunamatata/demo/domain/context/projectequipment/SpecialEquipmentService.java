package com.hakunamatata.demo.domain.context.projectequipment;

import com.hakunamatata.demo.domain.context.projectequipment.record.CreateEquipmentRecord;
import com.hakunamatata.demo.domain.context.projectequipment.record.EquipmentRecord;
import com.hakunamatata.demo.domain.context.projectequipment.record.EquipmentRecordRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityExistsException;

@RequiredArgsConstructor
public class SpecialEquipmentService {
    private final SpecialEquipmentRepository equipmentRepository;
    private final EquipmentRecordRepository recordRepository;
    private final SpecialEquipmentFactory equipmentFactory;

    public SpecialEquipment createEquipment(CreateEquipmentCommand command) {
        SpecialEquipment equipment = equipmentFactory.create(command);
        CreateEquipmentRecord record = new CreateEquipmentRecord();
        equipmentRepository.save(equipment);
        recordRepository.save(record);
        return equipment;
    }

    public void activeEquipment(String equipmentId) {
        SpecialEquipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(EntityExistsException::new);
        EquipmentRecord record = equipment.active();
        equipmentRepository.save(equipment);
        recordRepository.save(record);
    }

    public void deactivateEquipment(String equipmentId) {
        SpecialEquipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(EntityExistsException::new);
        EquipmentRecord record = equipment.deactivate();
        equipmentRepository.save(equipment);
        recordRepository.save(record);
    }

    public void quitEquipment(String equipmentId) {
        SpecialEquipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(EntityExistsException::new);
        EquipmentRecord record = equipment.quit();
        equipmentRepository.save(equipment);
        recordRepository.save(record);
    }

    public void transferEquipmentTo(String equipmentId, Location location) {
        SpecialEquipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(EntityExistsException::new);
        EquipmentRecord record = equipment.transferTo(location);
        equipmentRepository.save(equipment);
        recordRepository.save(record);
    }
}
