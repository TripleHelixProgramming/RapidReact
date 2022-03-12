package frc.robot.drive.commands;

import frc.robot.drive.Drivetrain;

// import com.team2363.utilities.ControllerMap;

// import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.lib.Curve;
import frc.lib.HelixJoysticks;
import frc.lib.LinCurve;

public class JoystickDrive extends Drive {

    Curve xyJoyMap = new LinCurve(0.0, 1.0, 0.2);
    Curve thetaJoyMap = new LinCurve(0.0, 1.0, 0.2);

    HelixJoysticks m_controller;

    public JoystickDrive(Drivetrain subsystem, HelixJoysticks joysticks){
        super(subsystem);
        this.m_controller = joysticks;
    }
 
    @Override
    public double getX() {
        double xRaw = m_controller.getX();
//        SmartDashboard.putNumber("JoystickX",  xyJoyMap.calculateMappedVal(xRaw));
        return xyJoyMap.calculateMappedVal(xRaw);
    }

    @Override
    public double getY() {
        double yRaw = m_controller.getY();
//        SmartDashboard.putNumber("JoystickY",  xyJoyMap.calculateMappedVal(yRaw));
        return xyJoyMap.calculateMappedVal(yRaw);
    }

    @Override
    public double getTheta() {
        double thetaRaw = m_controller.getRotation();
//        SmartDashboard.putNumber("JoystickTheta",  xyJoyMap.calculateMappedVal(thetaRaw));
        return thetaJoyMap.calculateMappedVal(thetaRaw);
    }

    @Override
    public boolean getFieldRelative() {
        return true;
    }
}