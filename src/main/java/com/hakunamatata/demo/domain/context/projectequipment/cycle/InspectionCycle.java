package com.hakunamatata.demo.domain.context.projectequipment.cycle;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
public class InspectionCycle {
    private final InspectionTaskCollection tasks;
    private final InspectionTaskFactory factory;

    @Builder.Default
    private boolean shouldCreateNextTask = false;

    private LocalDate nextTaskDate;

    private ProjectInspectionConfiguration configuration;

    public void checkAndCreateTask(String projectId) {
        if (shouldCreateNextTask
                && nextTaskDate.isBefore(LocalDate.now())
                && !tasks.isExistActiveTask()) {
            tasks.addTask(factory.createTask(projectId));
            shouldCreateNextTask = false;
            nextTaskDate = configuration.getNextTaskDate(nextTaskDate);
        }
    }
}
