// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.shooter;

import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.SparkMaxPIDController.ArbFFUnits;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.drivers.HelixSparkMax;
import frc.robot.Constants.ElectricalConstants;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {

  public static boolean UP = true;
  public static boolean DOWN = false;

  private boolean hoodDirection;

  private double targetVelocity = 0.0; // Target velocity of the shooter.
  private boolean triggerPull = false;
  private boolean triggerEject = false;
  
  private HelixSparkMax trigger;
  private HelixSparkMax hood;
  private HelixSparkMax shooterMaster;
  private HelixSparkMax shooterSlave;

  public Shooter() {
    trigger = new HelixSparkMax(ElectricalConstants.kTriggerPort);
    hood = new HelixSparkMax(ElectricalConstants.kShooterHoodPort);
    shooterMaster = new HelixSparkMax(ElectricalConstants.kShooterMasterPort);
    shooterSlave = new HelixSparkMax(ElectricalConstants.kShooterSlavePort);
  
    shooterSlave.follow(shooterMaster, true);

    shooterMaster.setClosedLoopRampRate(0.1);

    // hood.setSmartCurrentLimit(10);
    hood.setSmartCurrentLimit((int)Math.round(ShooterConstants.kHoodSafetyCurrentLimit));

    hood.setConversionFactor(ShooterConstants.kHoodGearingRatio);

    shooterMaster.setPIDF(ShooterConstants.kShooterP, 
                ShooterConstants.kShooterI, 
                ShooterConstants.kShooterD, 
                ShooterConstants.kShooterFF);

    hood.setPIDF(ShooterConstants.kHoodP, 
                ShooterConstants.kHoodI, 
                ShooterConstants.kHoodD, 
                0);

    hood.setIdleMode(IdleMode.kBrake);
    hood.setPosition(ShooterConstants.kHoodMinAngle); // Assume hood starts completely down/retracted.
  }

  public void periodic() {
    SmartDashboard.putNumber("Hood Motor Current", hood.getOutputCurrent());
    SmartDashboard.putNumber("Hood Angle", getHoodAngle());

    runTrigger();
  }

  public void resetHoodAngle() {
    hood.setPosition(ShooterConstants.kHoodMinAngle);
    setHoodPosition(ShooterConstants.kHoodMinAngle);
  }

  public boolean checkHoodCurrentLimit() {
    if ( ShooterConstants.kHoodStopCurrentLimit < hood.getOutputCurrent()) {
      SmartDashboard.putBoolean("Hood Hard Stop Hit", true);
      stopHood();
      // Assume a high current means we are at the bottom? And reset the encoder?
      
      if (DOWN == hoodDirection) {
        resetHoodAngle();
      }
      return true;
    }
    else return false;
  }

  public void moveHood(boolean direction) {
    this.hoodDirection = direction;

    double speed = direction ? ShooterConstants.kHoodSpeed : -1.0 * ShooterConstants.kHoodSpeed;
    if (checkHoodCurrentLimit()) {
      return;
    }

    // Allow the hood to move UP if the current angle is less than the max
    if ((getHoodAngle() < ShooterConstants.kHoodMaxAngle) && (UP == direction)) {      
      hood.set(speed);
    // Allow the hood to move DOWN if the current angle is greater than the min
    } else if (ShooterConstants.kHoodMinAngle <  getHoodAngle() && (DOWN == direction)) {
      hood.set(speed);
    } else { // Out of range! STOP!!!
      stopHood();
    }
  }

  public void setHoodSpeed(double speed) {
    hood.set(speed);
  }

  public double getHoodCurrent() {
    return hood.getOutputCurrent();
  }

  public void setHoodPosition(double degrees) {
    // degrees = Math.min(Math.max(degrees, ShooterConstants.kHoodMinAngle), ShooterConstants.kHoodMaxAngle);
    hood.setReference(degrees, ControlType.kPosition, 0.0, ArbFFUnits.kPercentOut);
  }

  public void setShooterVelocity(double velocity) {
    this.targetVelocity = velocity;
    shooterMaster.setReference(targetVelocity, ControlType.kVelocity, targetVelocity * ShooterConstants.kShooterFF, ArbFFUnits.kPercentOut);
  }

  public double getShooterVelocity() {
    return shooterMaster.getVelocity();
  }

  public double getTargetVelocity() {
    return this.targetVelocity;
  }

  public void stopHood() {
    hood.stopMotor();
  }

  public void stopShooter() {
    shooterMaster.stopMotor();
    this.targetVelocity = 0.0;
  }

  public double getHoodAngle() {
    return hood.getPosition();
  }

  public void startTrigger() {
    triggerEject = false;
    triggerPull = true;
  }

  public void reverseTrigger() {
    triggerEject = true;
    triggerPull = true;
  }

  public void stopTrigger() {
    triggerPull = false;
    triggerEject = false;
  }

  private void runTrigger() {
    if (triggerEject) {
      trigger.set(ShooterConstants.kTriggerSpeed);
    } else if (triggerPull && getShooterVelocity() > targetVelocity * ShooterConstants.kTriggerDeadband) {
      trigger.set(-ShooterConstants.kTriggerSpeed);
    } else {
      trigger.stopMotor();
    }
  }
}