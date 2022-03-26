package frc.robot.status.commands;

import frc.robot.status.Status;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DIOSwitchStatus extends CommandBase {
    private Status status;
    int dioState = -1;
    int ledOffset = 10;
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
        if (dioState < 0) {
            buffer.setLED(0, Color.kDarkRed);
        } else {
            if (dioState >= 0) {
                buffer.setLED(ledOffset+ 0, Color.kDarkRed);
                buffer.setLED(ledOffset+ 1, Color.kDarkRed);
            }
            if (dioState >= 1) {
                buffer.setLED(ledOffset+ 2, Color.kOlive);
                buffer.setLED(ledOffset+ 3, Color.kOlive);
            }
            if (dioState >= 2) {
                buffer.setLED(ledOffset+ 4, Color.kDarkGreen);
                buffer.setLED(ledOffset+ 5, Color.kDarkGreen);
            }
            if (dioState >= 3) {
                buffer.setLED(ledOffset+ 6, Color.kDarkBlue);
                buffer.setLED(ledOffset+ 7, Color.kDarkBlue);
            }
            if (dioState >= 4) {
                buffer.setLED(ledOffset+ 8, Color.kDarkGray);
                buffer.setLED(ledOffset+ 9, Color.kDarkGray);
            }
        }
        status.setLedData(buffer);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
