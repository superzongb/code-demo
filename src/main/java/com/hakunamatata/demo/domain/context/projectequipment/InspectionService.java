package com.hakunamatata.demo.domain.context.projectequipment;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class InspectionService {
    private final SpecialEquipmentRepository equipmentRepository;

    public void createInspectionTasks(String projectId, LocalDate date) {
        int count = equipmentRepository.countWhichShouldCreateInspectionTaskAt(projectId, date);
        int offset = 0;
        while (offset < count) {
            int limit = 20;
            List<SpecialEquipment> equipments = equipmentRepository.findWhichShouldCreateInspectionTaskAt(projectId, date, limit, offset);
            equipments.forEach(SpecialEquipment::checkAndCreateInspectionTask);
            equipmentRepository.saveAll(equipments);
            offset += limit;
        }
    }
}
