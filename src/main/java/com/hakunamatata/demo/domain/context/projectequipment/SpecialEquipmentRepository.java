package com.hakunamatata.demo.domain.context.projectequipment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SpecialEquipmentRepository {
    void save(SpecialEquipment equipment);

    Optional<SpecialEquipment> findById(String equipmentId);

    List<SpecialEquipment> findWhichShouldCreateInspectionTaskAt(String projectId, LocalDate date, int limit, int offset);

    int countWhichShouldCreateInspectionTaskAt(String projectId, LocalDate date);

    void saveAll(List<SpecialEquipment> equipments);
}
