// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.drive.Drivetrain;

public class AbsoluteOrientation extends JoystickDrive {
    
    public AbsoluteOrientation(Drivetrain subsystem) {
        super(subsystem);
    }

    @Override
    public double getTheta() {
        double thetaRaw = m_OI.getRotation();
        Rotation2d theta = Rotation2d.fromDegrees(thetaRaw * 180.0);
        drivetrain.rotateAbsolute(theta);
        return drivetrain.getThetaDot();
    }
}
