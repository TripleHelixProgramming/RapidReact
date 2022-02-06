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

    private final HelixSparkMax m_driveMotor;
    private final HelixSparkMax m_turningMotor;

    private final CANCoder m_turningCANCoder;

    // absolute offset for the CANCoder so that the wheels can be aligned when the
    // robot is turned on
//    private final Rotation2d m_CANCoderOffset;

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

        m_driveMotor = new HelixSparkMax(driveMotorChannel);
        m_turningMotor = new HelixSparkMax(turningMotorChannel);

        m_turningCANCoder = new CANCoder(turningCANCoderChannel);
        m_turningCANCoder.configAbsoluteSensorRange(AbsoluteSensorRange.Unsigned_0_to_360);
        m_turningCANCoder.setPosition(0);
        
//        m_CANCoderOffset = Rotation2d.fromDegrees(turningCANCoderOffsetDegrees);

        m_driveMotor.setIdleMode(IdleMode.kCoast);
        m_turningMotor.setIdleMode(IdleMode.kBrake);

        m_driveMotor.setConversionFactor(ModuleConstants.kDriveConversionFactor);
        m_turningMotor.setConversionFactor(360.0 / ModuleConstants.kTurnPositionConversionFactor);

        m_turningMotor.setPIDF(ModuleConstants.kTurningP,
                            ModuleConstants.kTurningI,
                            ModuleConstants.kTurningD,
                            0);

        m_driveMotor.setPIDF(ModuleConstants.kDriveP,
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
        // getPosition() returns the number of cumulative rotations.
        // Convert that to 0.0 to 1.0
        // double m1 = m_turningEncoder.getPosition() % 360.0;
        // double m2 = (m1 < 0) ? m1 + 360 : m1;

        double m2 = (m_turningMotor.getPosition() % 360 + 360) % 360;

        return new SwerveModuleState(m_driveMotor.getVelocity(), new Rotation2d(m2 * Math.PI / 180));
    }

    public CANCoder getTurnCANcoder() {
        return m_turningCANCoder;
    }

    public double getTurnCANcoderAngle() {
        return m_turningCANCoder.getAbsolutePosition();
    }

    public Rotation2d adjustedAngle = new Rotation2d();

    /**
     * Sets the desired state for the module.
     *
     * @param state Desired state with speed (in meters per second?) and angle (in
     *              degrees).
     */
    public void setDesiredState(SwerveModuleState state) {
        Rotation2d curAngle = Rotation2d.fromDegrees(m_turningMotor.getPosition());

        double delta = deltaAdjustedAngle(state.angle.getDegrees(), curAngle.getDegrees());

        // Calculate the drive motor output from the drive PID controller.
        double driveOutput = state.speedMetersPerSecond;

        if (Math.abs(delta) > 90) {
            driveOutput *= -1;
            delta -= Math.signum(delta) * 180;
        }

        adjustedAngle = Rotation2d.fromDegrees(delta + curAngle.getDegrees());

        m_turningMotor.setReference(adjustedAngle.getDegrees(), ControlType.kPosition);        
        m_driveMotor.setReference(driveOutput, ControlType.kVelocity);
    }

    //calculate the angle motor setpoint based on the desired angle and the current angle measurement
    // Arguments are in radians.
    public double deltaAdjustedAngle(double targetAngle, double currentAngle) {
        return ((targetAngle - currentAngle + 180) % 360 + 360) % 360 - 180;
    }

    public double getDriveDistanceMeters() {
        return m_driveMotor.getPosition();
    }

    public void resetDistance() {
        m_driveMotor.setPosition(0.0);
    }

    public void syncTurningEncoders() {
        m_turningMotor.setPosition(m_turningCANCoder.getAbsolutePosition());
    }

    /** Zeros all the SwerveModule encoders. */
    public void resetEncoders() {
        // Reset the cumulative rotation counts of the SparkMax motors
        m_turningMotor.setPosition(0.0);

        m_turningCANCoder.setPosition(0.0);
        m_turningCANCoder.configMagnetOffset(m_turningCANCoder.configGetMagnetOffset() - m_turningCANCoder.getAbsolutePosition());
    }
}