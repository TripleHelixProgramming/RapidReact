// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.shooter.commands;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.shooter.Shooter;

/**
 * Raise the hood.
 * Hood moves at a fixed rate - to limit stress on components.
 * Hood hard stops based on current draw.
 * 
 */
public class MoveHood extends CommandBase {

    private Shooter shooter;
    private boolean direction = Shooter.DOWN;


    public MoveHood(Shooter shooter, boolean direction) {
        this.shooter = shooter;
        addRequirements(shooter);
        this.direction = direction;
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // Start the hood motor.
        shooter.moveHood(direction);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // Check if hard stop hit. Stop if so.
        if ((Shooter.UP == direction) && (ShooterConstants.kHoodMaxAngle < shooter.getHoodAngle())
            || (Shooter.DOWN == direction) && (ShooterConstants.kHoodMinAngle > shooter.getHoodAngle())) {
                end(true);
            }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // Stop the motor
        shooter.stopHood();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
