// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.shooter;

import frc.robot.Constants.ElectricalConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.shooter.commands.StopShooter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  public static boolean UP = true;
  public static boolean DOWN = false;

  private CANSparkMax hoodMotor;
  private CANSparkMax masterMotor;
  private CANSparkMax slaveMotor;

  private RelativeEncoder hoodEncoder;
  private RelativeEncoder masterEncoder;

  public Shooter() {
    hoodMotor = new CANSparkMax(ElectricalConstants.kShooterHoodPort, MotorType.kBrushless);
    masterMotor = new CANSparkMax(ElectricalConstants.kShooterMasterPort, MotorType.kBrushless);
    slaveMotor = new CANSparkMax(ElectricalConstants.kShooterSlavePort, MotorType.kBrushless);

    hoodEncoder = hoodMotor.getEncoder();
    masterEncoder = masterMotor.getEncoder();

    hoodEncoder.setPosition(0.0); // Assume hood starts completely down/retracted.

    setDefaultCommand(new StopShooter(this));
  }

  public void moveHood(boolean direction) {

    double speed = direction ? ShooterConstants.kHoodSpeed : -1.0 * ShooterConstants.kHoodSpeed;

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

  public void stopHood() {
    hoodMotor.stopMotor();
  }

  public void stopShooter() {
    masterMotor.stopMotor();
    slaveMotor.stopMotor();
  }

  public double getHoodAngle() {
    double rotations = hoodEncoder.getPosition(); // Number of rotations by default.

    // Convert number of rotations to angle based on gearing 
    double angle = ShooterConstants.kHoodMinAngle +  (rotations * ShooterConstants.kHoodGearingRatio);
    return angle;
  }

}
