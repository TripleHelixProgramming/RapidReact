package frc.robot.status.commands;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.status.Status;

/**
 * Fll the LED strip with the color from the first 
 * element in the LED Buffer.
 * 
 * The usage for this is that pushing a shooter button sets the LED strip
 * with 1/2 a color and 1/2 off. When the wheel spins up to close to the 
 * target speed, the strip should be completely filled with the color.
 *
 */
public class FillLEDsCommand extends CommandBase {
    private Status status;
    
    public FillLEDsCommand() {
        this.status = Status.getInstance();
        addRequirements(status);
    }

    @Override
    public void initialize() {
        // Find the coloor of the first LED
        AddressableLEDBuffer buffer = status.getLedData();
        Color color = buffer.getLED(0);
        // Set the color of the whole buffer
        status.setColor(color);
    }

    @Override
    public boolean isFinished() {
      return true;
    }      
    
}
