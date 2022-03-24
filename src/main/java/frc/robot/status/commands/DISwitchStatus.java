package frc.robot.status.commands;

import frc.robot.status.Status;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DISwitchStatus extends CommandBase {
    private Status status;
    int dioState = -1;
    AddressableLEDBuffer buffer = new AddressableLEDBuffer(Status.ADDRESSABLE_LED_COUNT);

    public DISwitchStatus(int dioState) {
        this.status = Status.getInstance();
        this.dioState = dioState;
        addRequirements(status);
    }
    
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        switch (dioState) {

        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
