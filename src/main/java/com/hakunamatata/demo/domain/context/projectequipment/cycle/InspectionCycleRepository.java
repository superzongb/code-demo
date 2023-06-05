package com.hakunamatata.demo.domain.context.projectequipment.cycle;

import java.util.List;

public interface InspectionCycleRepository {
    List<InspectionCycle> findCyclesWhichShouldCreateTask();
}
