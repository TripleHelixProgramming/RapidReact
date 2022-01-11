// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.drive.Drivetrain;

public class ResetEncoders extends CommandBase {
    // The subsystem the command runs on
    private final Drivetrain drivetrain;

    public ResetEncoders(Drivetrain subsystem){
        drivetrain = subsystem;
        addRequirements(drivetrain);
    }
 
    @Override
    public void initialize() {
    }
            
    @Override
    public boolean runsWhenDisabled() {
        return true;
    }
      
    @Override
    public void execute() {
        drivetrain.resetEncoders();
    }
      
    @Override
    public boolean isFinished() {
        return true;
    }
}