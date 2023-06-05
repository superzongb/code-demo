package com.hakunamatata.demo.domain.context.projectequipment.cycle;

import java.util.Optional;

public interface InspectionTaskCollection {
    void addTask(InspectionTask task);

    Optional<InspectionTask> findActiveTask();

    boolean isExistActiveTask();
}
