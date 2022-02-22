package frc.robot.status.commands;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.status.Status;

public class SetColor extends CommandBase{
    private Status status;
    private Color color;
    private int brightness;

    public SetColor(Status status, Color color) {
        this(status, color, 255);
    }

    public SetColor(Status status, Color color, int brightness) {
        this.status = status;
        this.color = color;
        this.brightness = brightness;
        addRequirements(status);
    }

    @Override
    public void initialize() {
        status.setColor(color, brightness);
    }

    @Override
    public boolean runsWhenDisabled() {
        return true;
    }

    @Override
    public boolean isFinished() {
      return true;
    }      
}
