package frc.robot.status.commands;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.status.Status;

public class SetColor extends CommandBase{
    private Status status;
    private Color color;
    private int brightness = 255;
    private int percent = 100;

    public SetColor(Status status, Color color) {
        this(status, color, 255);
    }

    public SetColor(Status status, Color color, int brightness) {
        this(status, color, brightness, 100);
    }

    /**
     * @param status - the Status object
     * @param color - the color to set
     * @param brightness - 0-255
     * @param percent - 0-100, percent of the buffer to fill, the rest will be set to black
     */
    public SetColor(Status status, Color color, int brightness, int percent) {
        this.status = status;
        this.color = color;
        this.brightness = brightness;
        this.percent = percent;
        addRequirements(status);
    }


    @Override
    public void initialize() {
        status.setColor(color, brightness, percent);
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
