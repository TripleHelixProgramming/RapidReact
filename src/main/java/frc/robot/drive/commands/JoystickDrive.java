// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive.commands;

import frc.robot.drive.Drivetrain;

import com.team2363.utilities.ControllerMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.lib.Curve;
import frc.lib.HelixJoysticks;
import frc.lib.LinCurve;

public class JoystickDrive extends Drive {

    Curve xyJoyMap = new LinCurve(0.0, 1.0, 0.2);
    Curve thetaJoyMap = new LinCurve(0.0, 1.0, 0.2);

    HelixJoysticks controller;

    public JoystickDrive(Drivetrain subsystem, HelixJoysticks controller){
        super(subsystem);
        controller = this.controller;
    }
 
    @Override
    public double getX() {
        double xRaw = controller.getX();
        SmartDashboard.putNumber("JoystickX",  xyJoyMap.calculateMappedVal(xRaw));
        return xyJoyMap.calculateMappedVal(xRaw);
    }

    @Override
    public double getY() {
        double yRaw = controller.getY();
        SmartDashboard.putNumber("JoystickY",  xyJoyMap.calculateMappedVal(yRaw));
        return xyJoyMap.calculateMappedVal(yRaw);
    }

    @Override
    public double getTheta() {
        double thetaRaw = controller.getRotation();
        SmartDashboard.putNumber("JoystickTheta",  xyJoyMap.calculateMappedVal(thetaRaw));
        return thetaJoyMap.calculateMappedVal(thetaRaw);
    }

    @Override
    public boolean getFieldRelative() {
        return true;
    }
}