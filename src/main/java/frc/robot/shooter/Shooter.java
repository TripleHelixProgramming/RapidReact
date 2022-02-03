// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.shooter;

import frc.robot.Constants.ElectricalConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.shooter.commands.StopShooter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController.ArbFFUnits;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  public static boolean UP = true;
  public static boolean DOWN = false;

  private double targetVelocity = 0.0; // Target velocity of the shooter.
  
  private CANSparkMax triggerMotor;
  private CANSparkMax hoodMotor;
  private CANSparkMax masterMotor;
  private CANSparkMax slaveMotor;

  private RelativeEncoder hoodEncoder;
  private RelativeEncoder masterEncoder;

  private SparkMaxPIDController hoodController;
  private SparkMaxPIDController shooterController;

  public Shooter() {
    triggerMotor = new CANSparkMax(ElectricalConstants.kTriggerPort, MotorType.kBrushless);
    hoodMotor = new CANSparkMax(ElectricalConstants.kShooterHoodPort, MotorType.kBrushless);
    masterMotor = new CANSparkMax(ElectricalConstants.kShooterMasterPort, MotorType.kBrushless);
    slaveMotor = new CANSparkMax(ElectricalConstants.kShooterSlavePort, MotorType.kBrushless);

    triggerMotor.restoreFactoryDefaults();
    hoodMotor.restoreFactoryDefaults();
    masterMotor.restoreFactoryDefaults();
    slaveMotor.restoreFactoryDefaults();
  
    slaveMotor.follow(masterMotor, true);

    triggerMotor.enableVoltageCompensation(12);
    hoodMotor.enableVoltageCompensation(12);
    masterMotor.enableVoltageCompensation(12);

    masterMotor.setClosedLoopRampRate(0.1);

    // hoodMotor.setSmartCurrentLimit(10);
    hoodMotor.setSmartCurrentLimit(70);

    hoodEncoder = hoodMotor.getEncoder();
    masterEncoder = masterMotor.getEncoder();

    hoodEncoder.setPositionConversionFactor(ShooterConstants.kHoodGearingRatio);

    hoodController = hoodMotor.getPIDController();
    shooterController = masterMotor.getPIDController();

    hoodController.setP(ShooterConstants.kHoodP);
    hoodController.setI(ShooterConstants.kHoodI);
    hoodController.setD(ShooterConstants.kHoodD);

    shooterController.setP(ShooterConstants.kShooterP);
    shooterController.setI(ShooterConstants.kShooterI);
    shooterController.setD(ShooterConstants.kShooterD);

    hoodMotor.setIdleMode(IdleMode.kBrake);
    hoodEncoder.setPosition(ShooterConstants.kHoodMinAngle); // Assume hood starts completely down/retracted.
  }

  public void moveHood(boolean direction) {

    double speed = direction ? ShooterConstants.kHoodSpeed : -1.0 * ShooterConstants.kHoodSpeed;

    if (ShooterConstants.kHoodCurrentLimit < hoodMotor.getOutputCurrent()) {
      stopHood();
      // Should we assume a high current means we are at the bottom? And reset the encoder?
      /*
      if (DOWN == direction) {
        hoodEncoder.setPosition(0.0);
      }
      */
      return;
    }

    // Allow the hood to move UP if the current angle is less than the max
    if ((getHoodAngle() < ShooterConstants.kHoodMaxAngle) && (UP == direction)) {      
      hoodMotor.set(speed);
    // Allow the hood to move DOWN if the current angle is greater than the min
    } else if (ShooterConstants.kHoodMinAngle <  getHoodAngle() && (DOWN == direction)) {
      hoodMotor.set(speed);
    } else { // Out of range! STOP!!!
      stopHood();
    }
  }

  public void setHoodPosition(double degrees) {
    degrees = Math.min(Math.max(degrees, ShooterConstants.kHoodMinAngle), ShooterConstants.kHoodMaxAngle);
    hoodController.setReference(degrees, ControlType.kPosition, 0, 0.0, ArbFFUnits.kPercentOut);
  }

  public void setShooterVelocity(double velocity) {
    this.targetVelocity = velocity;
    shooterController.setReference(targetVelocity, ControlType.kVelocity, 0, targetVelocity * ShooterConstants.kShooterFF, ArbFFUnits.kPercentOut);
  }

  public double getShooterVelocity() {
    return masterEncoder.getVelocity();
  }

  public double getTargetVelocity() {
    return this.targetVelocity;
  }

  public void stopHood() {
    hoodMotor.stopMotor();
  }

  public void stopShooter() {
    masterMotor.stopMotor();
    this.targetVelocity = 0.0;
  }

  public double getHoodAngle() {
    return hoodEncoder.getPosition();
  }

  public void startTrigger() {
    triggerMotor.set(ShooterConstants.kTriggerSpeed);
  }

  public void stopTrigger() {
    triggerMotor.stopMotor();
  }

}
