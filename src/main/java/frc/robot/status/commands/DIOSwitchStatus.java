package frc.robot.status.commands;

import frc.robot.status.Status;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DIOSwitchStatus extends CommandBase {
    private Status status;
    int dioState = -1;
    AddressableLEDBuffer buffer = new AddressableLEDBuffer(Status.ADDRESSABLE_LED_COUNT);

    public DIOSwitchStatus(int dioState) {
        this.status = Status.getInstance();
        this.dioState = dioState;
        addRequirements(status);
    }
    
    @Override
    public boolean runsWhenDisabled() {
        return true;
    }

    @Override
    public void initialize() {
        // Set the buffer to black to start
        for (var i = 0; i < buffer.getLength(); i++) {
            buffer.setLED(i, Color.kBlack);
        }
    }

    @Override
    public void execute() {

        switch (dioState) {
            case 0:
                buffer.setLED(0, Color.kDarkOrange);
                buffer.setLED(1, Color.kDarkOrange);
            break;
            case 1:
                buffer.setLED(2, Color.kOlive);
                buffer.setLED(3, Color.kOlive);
            break;
            case 2:
                buffer.setLED(4, Color.kDarkGreen);
                buffer.setLED(5, Color.kDarkGreen);
            break;
            case 3:
                buffer.setLED(6, Color.kDarkBlue);
                buffer.setLED(7, Color.kDarkBlue);
            break;
            case 4:
                buffer.setLED(8, Color.kDarkGray);
                buffer.setLED(9, Color.kDarkGray);
            break;
            default:
                buffer.setLED(0, Color.kDarkRed);
            break;
        }
        status.setLedData(buffer);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
