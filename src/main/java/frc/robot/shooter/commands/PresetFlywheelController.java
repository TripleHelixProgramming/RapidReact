package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj.Preferences;
import frc.robot.shooter.Shooter;
import frc.robot.vision.Limelight;

public class PresetFlywheelController extends FlywheelController {

    private String preset;

    public PresetFlywheelController(Shooter shooter, String preset) {
        super(shooter, 0, 0); // Create with bogus speed and angle. Set real values in initialize.
        this.preset = preset;
    }

    @Override
    public void initialize() {
        switch (preset) {
            case "BUF":
                this.rpm = Preferences.getInt("BUF.Velocity", 1625);
                this.hoodAngle = Preferences.getDouble("BUF.Angle", 65.0); // baseline, upper goal, front shot    
                break;
            case "BUR":
                this.rpm = Preferences.getInt("BUR.Velocity", 1625);
                this.hoodAngle = Preferences.getDouble("BUR.Angle", 65.0); // baseline, upper goal, rear shot    
                break;
            case "TLR":
                this.rpm = Preferences.getInt("TLR.Velocity", 1625);
                this.hoodAngle = Preferences.getDouble("TLR.Angle", 65.0); // tarmac, lower goal, rear shot    
                break;
            case "TUR":
                this.rpm = Preferences.getInt("TUR.Velocity", 1625);
                this.hoodAngle = Preferences.getDouble("TUR.Angle", 65.0); // tarmac, upper goal, rear shot    
                break;
            case "BLP":
                this.rpm = Preferences.getInt("BLP.Velocity", 500);
                this.hoodAngle = Preferences.getDouble("BLP.Angle", 60.0); // tarmac, upper goal, rear shot    
                break;
            default:
                this.rpm = 500;
                this.hoodAngle = 80.0;
                break;
        }
        shooter.setHoodPosition(this.hoodAngle);
        controller.startPeriodic(0.02);        
    }

}