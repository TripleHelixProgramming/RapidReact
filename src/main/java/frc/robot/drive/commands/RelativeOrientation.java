// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.Constants.DriveConstants;
import frc.robot.drive.Drivetrain;

import frc.lib.Curve;
import frc.lib.LinCurve;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RelativeOrientation extends JoystickDrive {

    double dt = 0.02;   //20 ms
    double joyScale = DriveConstants.kMaxRotationalVelocity * dt;
    Curve joyMap = new LinCurve(0.0, joyScale, 0.4);
    
    public RelativeOrientation(Drivetrain subsystem) {
        super(subsystem);
    }

    @Override
    public double getTheta() {
        double thetaRaw = m_OI.getRotation();
        double thetaTreated = joyMap.calculateMappedVal(thetaRaw);
        Rotation2d deltaTheta = new Rotation2d(thetaTreated);
        drivetrain.rotateRelative(deltaTheta);
        return drivetrain.getThetaDot();
    }
}
