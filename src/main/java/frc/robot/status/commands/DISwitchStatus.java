package frc.robot.status.commands;

import frc.robot.status.Status;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DISwitchStatus extends CommandBase {
    private Status status;
    int diState = -1;
    AddressableLEDBuffer buffer = new AddressableLEDBuffer(Status.ADDRESSABLE_LED_COUNT);

    public DISwitchStatus(int dioState) {
        this.status = Status.getInstance();
        this.diState = dioState;
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

        switch (diState) {
            case 0:
                buffer.setLED(0, Color.kDarkGreen);
                buffer.setLED(1, Color.kDarkGreen);
            break;
            case 1:
                buffer.setLED(2, Color.kDarkOrange);
                buffer.setLED(3, Color.kDarkOrange);
            break;
            case 2:
                buffer.setLED(4, Color.kDarkSlateBlue);
                buffer.setLED(5, Color.kDarkSlateBlue);
            break;
            case 3:
                buffer.setLED(6, Color.kDarkSalmon);
                buffer.setLED(7, Color.kDarkSalmon);
            break;
            case 4:
                buffer.setLED(8, Color.kDarkViolet);
                buffer.setLED(9, Color.kDarkViolet);
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
