package com.hakunamatata.demo.domain.context.projectequipment.cycle;

public abstract class InspectionTask {
    protected InspectionResult result;

    public void recordResult(InspectionResult result) {
        this.result = result;
    }
}
