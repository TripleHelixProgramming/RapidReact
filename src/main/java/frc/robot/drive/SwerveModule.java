// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive;

import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.drivers.HelixSparkMax;
import frc.robot.Constants.ModuleConstants;

public class SwerveModule extends SubsystemBase {

    private final HelixSparkMax driveMotor, turningMotor;
    private final CANCoder turningCANCoder;

    /**
     * Constructs a SwerveModule.
     *
     * @param driveMotorChannel   ID for the drive motor.
     * @param turningMotorChannel ID for the turning motor.
     */
    public SwerveModule(
                        int driveMotorChannel,
                        int turningMotorChannel,
                        int turningCANCoderChannel,
                        double turningCANCoderOffsetDegrees) {

        driveMotor = new HelixSparkMax(driveMotorChannel);
        turningMotor = new HelixSparkMax(turningMotorChannel);

        turningCANCoder = new CANCoder(turningCANCoderChannel);
        turningCANCoder.configAbsoluteSensorRange(AbsoluteSensorRange.Unsigned_0_to_360);
        turningCANCoder.setPosition(0);
        
        driveMotor.setIdleMode(IdleMode.kCoast);
        turningMotor.setIdleMode(IdleMode.kBrake);

        driveMotor.setConversionFactor(ModuleConstants.kDriveConversionFactor);
        turningMotor.setConversionFactor(360.0 / ModuleConstants.kTurnPositionConversionFactor);

        turningMotor.setPIDF(ModuleConstants.kTurningP,
                            ModuleConstants.kTurningI,
                            ModuleConstants.kTurningD,
                            0);

        driveMotor.setPIDF(ModuleConstants.kDriveP,
                            ModuleConstants.kDriveI,
                            ModuleConstants.kDriveD,
                            ModuleConstants.kDriveFF);
    }

    /**
     * Returns the current state of the module.
     *
     * @return The current state of the module.
     */
    public SwerveModuleState getState() {
        return new SwerveModuleState(driveMotor.getVelocity(), Rotation2d.fromDegrees(getTurnCANcoderAngle()));
    }

    public double getTurnCANcoderAngle() {
        return turningCANCoder.getAbsolutePosition();
    }

    /**
     * Sets the desired state for the module.
     *
     * @param state Desired state with speed (in meters per second?) and angle (in
     *              degrees).
     */
    public void setDesiredState(SwerveModuleState state) {
        state = SwerveModuleState.optimize(state, Rotation2d.fromDegrees(turningMotor.getPosition()));
        turningMotor.setReference(state.angle.getDegrees(), ControlType.kPosition);        
        driveMotor.setReference(state.speedMetersPerSecond, ControlType.kVelocity);
    }

    public double getDriveDistanceMeters() {
        return driveMotor.getPosition();
    }

    public void resetDistance() {
        driveMotor.setPosition(0.0);
    }

    public void syncTurningEncoders() {
        turningMotor.setPosition(turningCANCoder.getAbsolutePosition());
    }

    /** Zeros all the SwerveModule encoders. */
    public void resetEncoders() {
        // Reset the cumulative rotation counts of the SparkMax motors
        turningMotor.setPosition(0.0);

        turningCANCoder.setPosition(0.0);
        turningCANCoder.configMagnetOffset(turningCANCoder.configGetMagnetOffset() - turningCANCoder.getAbsolutePosition());
    }
}