// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
// import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
// import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class ElectricalConstants {
    public static final PneumaticsModuleType kPneumaticHub = PneumaticsModuleType.CTREPCM;
    public static final ModuleType kPowerDistributionModule = ModuleType.kCTRE;

    public static final int kPowerDistributionPort = 1;

    public static final int kRearRightDriveMotorPort = 10;
    public static final int kFrontRightDriveMotorPort = 12;
    public static final int kFrontLeftDriveMotorPort = 22;
    public static final int kRearLeftDriveMotorPort = 24;

    public static final int kRearRightTurningMotorPort = 11;  
    public static final int kFrontRightTurningMotorPort = 13;
    public static final int kFrontLeftTurningMotorPort = 23;
    public static final int kRearLeftTurningMotorPort = 25;

    public static final int kRearRightTurningEncoderPort = 31;
    public static final int kFrontRightTurningEncoderPort = 33;
    public static final int kFrontLefTurningEncoderPort = 43;
    public static final int kRearLeftTurningEncoderPort = 45;
    
    public static final int kGyroPort = 20;

    public static final int kIntakeDeployPort = 0;
    public static final int kIntakeRetractPort = 1;
    public static final int kIntakeRollerPort = 2;

    public static final int kIndexerPort = 2;

    public static final int kShooterPort = 1;
  }

  public static final class DriveConstants {

    // Define the conventional order of our modules when putting them into arrays
    public static final int FRONT_LEFT =0;
    public static final int FRONT_RIGHT =1;
    public static final int REAR_LEFT =2;
    public static final int REAR_RIGHT =3;

    public static final class CANCoder {
      public static final double kRearRightTurningEncoderOffset = 180.0;
      public static final double kFrontRightTurningEncoderOffset = 180.0;
      public static final double kFrontLefTurningEncoderOffset = 180.0;
      public static final double kRearLeftTurningEncoderOffset = 180.0;
    }

    public static final boolean kFrontLeftDriveEncoderReversed = false;
    public static final boolean kFrontRightDriveEncoderReversed = false;
    public static final boolean kRearLeftDriveEncoderReversed = true;
    public static final boolean kRearRightDriveEncoderReversed = true;

    public static final boolean kFrontLeftTurningEncoderReversed = false;
    public static final boolean kFrontRightTurningEncoderReversed = false;
    public static final boolean kRearLeftTurningEncoderReversed = true;
    public static final boolean kRearRightTurningEncoderReversed = true;

    // Units are meters.
    // Distance between centers of right and left wheels on robot
    public static final double kTrackWidth = 0.5715; // 22.5 in
    
    // Distance between front and back wheels on robot
    public static final double kWheelBase = 0.6223; // 24.5 in

    // Units are meters per second
    public static final double kMaxTranslationalVelocity = 0.75; //max 4.5

    // Units are radians per second
    public static final double kMaxRotationalVelocity = 5.0; //max 5.0

    //The locations for the modules must be relative to the center of the robot. 
    // Positive x values represent moving toward the front of the robot 
    // Positive y values represent moving toward the left of the robot.
    public static final SwerveDriveKinematics kDriveKinematics =
        new SwerveDriveKinematics(
            new Translation2d(kWheelBase / 2.0, kTrackWidth / 2.0),   // front left
            new Translation2d(kWheelBase / 2.0, -kTrackWidth / 2.0),  // front right
            new Translation2d(-kWheelBase / 2.0, kTrackWidth / 2.0),  // rear left
            new Translation2d(-kWheelBase / 2.0, -kTrackWidth / 2.0)  // rear right
            );

    public static final boolean kGyroReversed = false;

    public static final double kMaxSpeedMetersPerSecond = 3.0;
  }

  public static final class ModuleConstants {

    public static final double kDriveP = 0.1;
    public static final double kDriveI = 0.0;
    public static final double kDriveD = 0.0;
    public static final double kDriveFF = 2.96;

    public static final double kTurningP = 0.01;
    public static final double kTurningI = 0.0;
    public static final double kTurningD = 0.005;
    
    public static final double kMaxModuleAngularSpeedRadiansPerSecond = 0.000005 * Math.PI;
    public static final double kMaxModuleAngularAccelerationRadiansPerSecondSquared = 0.000005 * Math.PI;

    // public static final SimpleMotorFeedforward driveFF = new SimpleMotorFeedforward(0.254, 0.137);
    
    public static final double kWheelDiameterMeters = 0.098; // 0.09398; // 3.7 in

    // The drive encoder reports in RPM by default. Calculate the conversion factor
    // to make it report in meters per second.
    public static final double kDriveGearRatio = 6.75;
    public static final double kDriveConversionFactor = (kWheelDiameterMeters * Math.PI) / kDriveGearRatio;

    public static final double kTurnPositionConversionFactor = 12.8;
  }

  public static final class IntakeConstants {
    public static final double kRollerSpeed = 0.7; // power percentage
  }

  public static final class IndexerConstants {
    public static final double kShootSpeed = 0.7; // power percentage
  }

  public static final class ShooterConstants {
    public static final double kShootHighSpeed = 3500;
    public static final double kShootLowSpeed = 2000;
  }

  public static final class OIConstants {
    public static final String kXbox = "XBOX";
    public static final String kPS4 = "P";
    public static final String kRadioMaster = "TX16S";
    public static final String[] kDriverControllers = new String[]{kXbox, kRadioMaster};
    public static final String[] kOperatorControllers = new String[]{kPS4};

    public static final int kDriverPort = 0;
    public static final int kOperatorPort = 1;
  }

  public static final class AutoConstants {
    public static final double kMaxSpeedMetersPerSecond = 3.0;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3.0;
    public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
    public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

    public static final double kPTranslationController = 4.0;
    public static final double kPThetaController = 2.0;

    // Constraint for the motion profilied robot angle controller
    public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
        new TrapezoidProfile.Constraints(
            kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
  }
}