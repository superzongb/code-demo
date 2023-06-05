package com.hakunamatata.demo.domain.context.projectequipment;

import com.hakunamatata.demo.domain.context.projectequipment.cycle.InspectionCycle;
import com.hakunamatata.demo.domain.context.projectequipment.record.ActiveRecord;
import com.hakunamatata.demo.domain.context.projectequipment.record.DeactivateRecord;
import com.hakunamatata.demo.domain.context.projectequipment.record.QuitRecord;
import com.hakunamatata.demo.domain.context.projectequipment.record.TransferRecord;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class SpecialEquipment {
    private final String id;

    private final String projectId;

    private EquipmentState state = EquipmentState.ACTIVE;

    private Location location;

    private InspectionCycle inspectionCycle;

    public ActiveRecord active() {
        if (state != EquipmentState.DEACTIVATE) {
            throw new InvalidEquipmentStateException("Can't active.");
        }
        this.state = EquipmentState.ACTIVE;
        return new ActiveRecord();
    }

    public DeactivateRecord deactivate() {
        if (state != EquipmentState.ACTIVE) {
            throw new InvalidEquipmentStateException("Can't deactivate.");
        }
        this.state = EquipmentState.DEACTIVATE;
        return new DeactivateRecord();
    }

    public QuitRecord quit() {
        this.state = EquipmentState.QUIT;
        return new QuitRecord();
    }

    public TransferRecord transferTo(Location location) {
        this.location = location;
        return new TransferRecord();
    }

    public void checkAndCreateInspectionTask() {
        if (state == EquipmentState.ACTIVE) {
            inspectionCycle.checkAndCreateTask(this.projectId);
        }
    }
}
