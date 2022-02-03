// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import frc.robot.drive.Drivetrain;

public abstract class Drive extends CommandBase {

    private double xDot;
    private double yDot;
    private double thetaDot;
    private boolean fieldRelative;
    private ChassisSpeeds chassisSpeeds, chassisPercent;

    // The subsystem the command runs on
    public final Drivetrain drivetrain;

    public Drive(Drivetrain subsystem){
        drivetrain = subsystem;
        addRequirements(drivetrain);
    }
 
    @Override
    public void initialize() {
    }
            
    @Override
    public void execute() {
        xDot = getX() * DriveConstants.kMaxTranslationalVelocity;
        yDot = getY() * DriveConstants.kMaxTranslationalVelocity;
        thetaDot = getTheta() * DriveConstants.kMaxRotationalVelocity;
        fieldRelative = getFieldRelative();

        chassisSpeeds = fieldRelative
            ? ChassisSpeeds.fromFieldRelativeSpeeds(xDot, yDot, thetaDot, drivetrain.getHeading())
            : new ChassisSpeeds(xDot, yDot, thetaDot);
        
        drivetrain.drive(chassisSpeeds, true);
    }

    abstract public double getX();
    abstract public double getY();
    abstract public double getTheta();
    abstract public boolean getFieldRelative();
}