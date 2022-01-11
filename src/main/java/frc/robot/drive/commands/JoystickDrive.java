// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive.commands;

import frc.robot.drive.Drivetrain;
import frc.robot.oi.OI;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.lib.Curve;
import frc.lib.LinCurve;

public class JoystickDrive extends Drive {

    Curve xyJoyMap = new LinCurve(0.0, 1.0, 0.2);
    Curve thetaJoyMap = new LinCurve(0.0, 1.0, 0.2);

    OI m_OI = OI.getInstance();

    public JoystickDrive(Drivetrain subsystem){
        super(subsystem);
    }
 
    @Override
    public double getX() {
        double xRaw = m_OI.getTranslateX();
        SmartDashboard.putNumber("JoystickX",  xyJoyMap.calculateMappedVal(xRaw));
        return xyJoyMap.calculateMappedVal(xRaw);
    }

    @Override
    public double getY() {
        double yRaw = m_OI.getTranslateY();
        SmartDashboard.putNumber("JoystickY",  xyJoyMap.calculateMappedVal(yRaw));
        return xyJoyMap.calculateMappedVal(yRaw);
    }

    @Override
    public double getTheta() {
        double thetaRaw = m_OI.getRotation();
        SmartDashboard.putNumber("JoystickTheta",  xyJoyMap.calculateMappedVal(thetaRaw));
        return thetaJoyMap.calculateMappedVal(thetaRaw);
    }

    @Override
    public boolean getFieldRelative() {
        return true;
    }
}