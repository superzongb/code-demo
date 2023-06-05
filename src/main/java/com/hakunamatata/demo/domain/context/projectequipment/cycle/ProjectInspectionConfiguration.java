package com.hakunamatata.demo.domain.context.projectequipment.cycle;

import java.time.LocalDate;

public class ProjectInspectionConfiguration {
    private long cycle;

    public LocalDate getNextTaskDate(LocalDate nextTaskDate) {
        return nextTaskDate.plusDays(cycle);

    }
}
