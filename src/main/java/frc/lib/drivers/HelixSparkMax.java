package frc.lib.drivers;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController.ArbFFUnits;

public class HelixSparkMax {
    // Add methods to this class if there is ever more things we need to mess with on the spark max
    private CANSparkMax motor;
    private RelativeEncoder encoder;
    private SparkMaxPIDController controller;

    public HelixSparkMax(int deviceId) {
        motor = new CANSparkMax(deviceId, MotorType.kBrushless);
        motor.restoreFactoryDefaults();
        motor.enableVoltageCompensation(12);
        encoder = motor.getEncoder();
        controller = motor.getPIDController();
    }

    public void follow(HelixSparkMax master, boolean invert) {
        motor.follow(master.getRawSparkMax());
    }

    public void setInverted(boolean invert) {
        motor.setInverted(invert);
    }

    public void setConversionFactor(double conversionFactor) {
        encoder.setPositionConversionFactor(conversionFactor);
        encoder.setVelocityConversionFactor(conversionFactor / 60.0);
    }

    public void setReference(double target, ControlType type) {
        controller.setReference(target, type);
    }

    public void setReference(double target, ControlType type, double arbFeedforward, ArbFFUnits u) {
        controller.setReference(target, type, 0, arbFeedforward, u);
    }

    public void set(double speed) {
        motor.set(speed);
    }

    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }

    public void stopMotor() {
        motor.stopMotor();
    }

    public void setPIDF(double P, double I, double D, double ff) {
        controller.setP(P);
        controller.setI(I);
        controller.setD(D);
        controller.setFF(ff);
    }

    public void setIdleMode(IdleMode idleMode) {
        motor.setIdleMode(idleMode);
    }

    public double getPosition() {
        return encoder.getPosition();
    }

    public void setPosition(double position) {
        encoder.setPosition(position);
    }

    public double getVelocity() {
        return encoder.getVelocity();
    }

    public double getOutputCurrent() {
        return motor.getOutputCurrent();
    }

    public void setSmartCurrentLimit(int currentLimit) {
        motor.setSmartCurrentLimit(currentLimit);
    }

    public void setClosedLoopRampRate(double rate) {
        motor.setClosedLoopRampRate(rate);
    }

    private CANSparkMax getRawSparkMax() {
        return motor;
    }
}