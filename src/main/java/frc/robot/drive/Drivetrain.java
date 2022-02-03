// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive;

import  com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.*;
import edu.wpi.first.math.kinematics.*;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ElectricalConstants;
import frc.robot.Constants.ModuleConstants;

@SuppressWarnings("PMD.ExcessiveImports")
public class Drivetrain extends SubsystemBase {

  // Robot swerve modules
  private final SwerveModule m_frontLeft =
      new SwerveModule(
          ElectricalConstants.kFrontLeftDriveMotorPort,
          ElectricalConstants.kFrontLeftTurningMotorPort,
          ElectricalConstants.kFrontLefTurningEncoderPort,
          DriveConstants.CANCoder.kFrontLefTurningEncoderOffset
          );

  private final SwerveModule m_frontRight =
      new SwerveModule(
          ElectricalConstants.kFrontRightDriveMotorPort,
          ElectricalConstants.kFrontRightTurningMotorPort,
          ElectricalConstants.kFrontRightTurningEncoderPort,
          DriveConstants.CANCoder.kFrontRightTurningEncoderOffset
          );

  private final SwerveModule m_rearLeft =
      new SwerveModule(
          ElectricalConstants.kRearLeftDriveMotorPort,
          ElectricalConstants.kRearLeftTurningMotorPort,
          ElectricalConstants.kRearLeftTurningEncoderPort,
          DriveConstants.CANCoder.kRearLeftTurningEncoderOffset
          );

  private final SwerveModule m_rearRight =
      new SwerveModule(
          ElectricalConstants.kRearRightDriveMotorPort,
          ElectricalConstants.kRearRightTurningMotorPort,
          ElectricalConstants.kRearRightTurningEncoderPort,
          DriveConstants.CANCoder.kRearRightTurningEncoderOffset
          );

  private SwerveModule[] modules = {m_frontLeft, m_frontRight, m_rearLeft, m_rearRight};
  private double[] lastDistances;
  private Timer timer;
  private double lastTime;

  // The gyro sensor
  private final AHRS m_ahrs = new AHRS();
//  private final Gyro m_gyro =  new ADIS16470_IMU(); // new ADXRS450_Gyro();
  // private final PigeonIMU m_pigeon = new PigeonIMU(DriveConstants.kPigeonPort);

  // Odometry class for tracking robot pose
  SwerveDriveOdometry m_odometry;

  //target pose and controller
  Pose2d m_targetPose;
  PIDController m_thetaController = new PIDController(1.0, 0.0, 0.05);
    
  /** Creates a new DriveSubsystem. */
  public Drivetrain() {

    // Zero out the gyro.
    m_ahrs.calibrate();

    m_odometry = new SwerveDriveOdometry(DriveConstants.kDriveKinematics, getHeading());

    for (SwerveModule module: modules) {
      module.resetDistance();
      module.syncTurningEncoders();
    }

    m_targetPose = m_odometry.getPoseMeters();
    m_thetaController.reset();
    m_thetaController.enableContinuousInput(-Math.PI, Math.PI);
    lastDistances = new double[]{
      m_frontLeft.getDriveDistanceMeters(),
      m_frontRight.getDriveDistanceMeters(),
      m_rearLeft.getDriveDistanceMeters(),
      m_rearRight.getDriveDistanceMeters(),
    };
    timer.reset();
    timer.start();
    lastTime = 0;
  }

  @Override
  public void periodic() {
    // Update the odometry in the periodic block
    // m_odometry.update(
    //     getHeading(),
    //     m_frontLeft.getState(),
    //     m_frontRight.getState(),
    //     m_rearLeft.getState(),
    //     m_rearRight.getState());
    
    SmartDashboard.putNumber("Heading", getHeading().getDegrees());
    
    SmartDashboard.putNumber("currentX", getPose().getX());
    SmartDashboard.putNumber("currentY", getPose().getY());
    SmartDashboard.putNumber("currentAngle", getPose().getRotation().getRadians());
    SmartDashboard.putNumber("targetPoseAngle", m_targetPose.getRotation().getRadians());

    SmartDashboard.putNumber("Distance 0", modules[0].getDriveDistanceMeters());
    SmartDashboard.putNumber("Distance 1", modules[1].getDriveDistanceMeters());
    SmartDashboard.putNumber("Distance 2", modules[2].getDriveDistanceMeters());
    SmartDashboard.putNumber("Distance 3", modules[3].getDriveDistanceMeters());
  }

  public void updateOdometry() {
    double[] distances = new double[]{
      m_frontLeft.getDriveDistanceMeters(),
      m_frontRight.getDriveDistanceMeters(),
      m_rearLeft.getDriveDistanceMeters(),
      m_rearRight.getDriveDistanceMeters(),
    };
    double time = timer.get();
    double dt = time - lastTime;
    lastTime = time;
    if (dt == 0) return;
    m_odometry.updateWithTime(time, 
                            getHeading(), 
                            new SwerveModuleState((distances[0] - lastDistances[0]) / dt, m_frontLeft.getState().angle),
                            new SwerveModuleState((distances[1] - lastDistances[1]) / dt, m_frontRight.getState().angle),
                            new SwerveModuleState((distances[2] - lastDistances[2]) / dt, m_rearLeft.getState().angle),
                            new SwerveModuleState((distances[3] - lastDistances[3]) / dt, m_rearRight.getState().angle));
    lastDistances = distances;
  }

  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    /* Don't reset all the motors' positions. Otherwise the robot thinks it has teleported!
    for (SwerveModule module: modules) {
      module.resetDistance();
    }
    */
    m_odometry.resetPosition(pose, getHeading());
  }

  /**
   * Method to rotate the relative orientation of the target pose at a given rate.
   *
   * @param deltaTheta How much to rotate the target orientation per loop.
   */
  public void rotateRelative(Rotation2d deltaTheta) {
    Transform2d transform = new Transform2d(new Translation2d(), deltaTheta);
    m_targetPose = m_targetPose.transformBy(transform);
  }

  /**
   * Method to set the absolute orientation of the target pose.
   *
   * @param theta The target orientation.
   */
  public void rotateAbsolute(Rotation2d theta) {
    m_targetPose = new Pose2d(new Translation2d(), theta);
  }

  /**
   * Method to get the output of the chassis orientation PID controller.
   *
   */
  public double getThetaDot() {
    double setpoint = m_targetPose.getRotation().getRadians();
    double measurement = getPose().getRotation().getRadians();
    double output = m_thetaController.calculate(measurement, setpoint);
    SmartDashboard.putNumber("PID out", output);
    return output;
  }

  /**
   * Method to drive the robot with given velocities.
   *
   * @param speeds ChassisSpeeds object with the desired chassis speeds [m/s and rad/s].
   */
  @SuppressWarnings("ParameterName")
  public void drive(ChassisSpeeds speeds, boolean normalize) {

    if (speeds.vxMetersPerSecond == 0 && speeds.vyMetersPerSecond == 0 && speeds.omegaRadiansPerSecond == 0) {
      brake();
      return;
    }

    SwerveModuleState[] swerveModuleStates =
        DriveConstants.kDriveKinematics.toSwerveModuleStates(speeds);
           
    if (normalize) normalizeDrive(swerveModuleStates, speeds);
    
    setModuleStates(swerveModuleStates);
  }

  public void normalizeDrive(SwerveModuleState[] desiredStates, ChassisSpeeds speeds) {
    double translationalK = Math.hypot(speeds.vxMetersPerSecond, speeds.vyMetersPerSecond) / DriveConstants.kMaxTranslationalVelocity;
    double rotationalK = Math.abs(speeds.omegaRadiansPerSecond) / DriveConstants.kMaxRotationalVelocity;
    double k = Math.max(translationalK, rotationalK);

    // Find the how fast the fastest spinning drive motor is spinning                                       
    double realMaxSpeed = 0.0;
    for (SwerveModuleState moduleState : desiredStates) {
      realMaxSpeed = Math.max(realMaxSpeed, Math.abs(moduleState.speedMetersPerSecond));
    }

    double scale = Math.min(k * ModuleConstants.kMaxSpeedMetersPerSecond / realMaxSpeed, 1);
    for (SwerveModuleState moduleState : desiredStates) {
      moduleState.speedMetersPerSecond *= scale;
    }
  }

  public void brake() {
    for (SwerveModule module : modules) {
      module.setDesiredState(new SwerveModuleState(0, module.getState().angle));
    }
  }

  /**
   * Sets the swerve ModuleStates.
   *
   * @param desiredStates The desired SwerveModule states.
   */
  public void setModuleStates(SwerveModuleState[] desiredStates) {

    SwerveDriveKinematics.desaturateWheelSpeeds(
        desiredStates, Preferences.getDouble("kMaxSpeedMetersPerSecond",ModuleConstants.kMaxSpeedMetersPerSecond));

        for (int i = 0; i <= 3; i++) {
          modules[i].setDesiredState(desiredStates[i]);
        }
  }

  public SwerveModuleState[] getModuleStates() {

    SwerveModuleState[] states = new SwerveModuleState[4];

    for (int i = 0; i <= 3; i++) {
      states[i++] = modules[i].getState();
    }

    return states;
  }

  /** Resets the drive encoders to currently read a position of 0. */
  public void resetEncoders() {

    for (SwerveModule module: modules) {
      module.resetEncoders();
    }
  }

  /** Zeroes the heading of the robot. */
  public void zeroHeading() {
    m_ahrs.zeroYaw();
    m_targetPose = new Pose2d(new Translation2d(), new Rotation2d());
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading as a Rotation2d
   */
  public Rotation2d getHeading() {
    float raw_yaw = m_ahrs.getYaw(); // Returns yaw as -180 to +180.
    float calc_yaw = raw_yaw;

    if (0.0 > raw_yaw ) { // yaw is negative
      calc_yaw += 360.0;
    }
    return Rotation2d.fromDegrees(-calc_yaw);
  }
}