package frc.robot.shooter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.SparkMaxPIDController.ArbFFUnits;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.util.InterpolationTable;
import frc.robot.Constants.ElectricalConstants;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {

  public static boolean UP = true;
  public static boolean DOWN = false;

  public static final InterpolationTable INTERPOLATION_TABLE = new InterpolationTable(new double[][]{
    {1.7, 1800, 78.25},
    {2.4, 1875, 73.25},
    {2.95, 1980, 73.25}
  });

  private boolean hoodDirection;
  int highCurrentCount = 0;
  int lowCurrentCount = 0;

  private double targetVelocity = 0.0; // Target velocity of the shooter.
  private boolean triggerPull = false;
  private boolean triggerEject = false;
  
  private CANSparkMax triggerMotor;
  private CANSparkMax hoodMotor;
  private CANSparkMax shooterLeader;
  private CANSparkMax shooterFollower;

  private Encoder shooterEncoder;

  private RelativeEncoder hoodEncoder;
  private RelativeEncoder masterEncoder;

  private SparkMaxPIDController hoodController;
  private SparkMaxPIDController shooterController;

  public Shooter() {
    triggerMotor = new CANSparkMax(ElectricalConstants.kTriggerPort, MotorType.kBrushless);
    hoodMotor = new CANSparkMax(ElectricalConstants.kShooterHoodPort, MotorType.kBrushless);
    shooterLeader = new CANSparkMax(ElectricalConstants.kShooterLeaderPort, MotorType.kBrushless);
    shooterFollower = new CANSparkMax(ElectricalConstants.kShooterFollowerPort, MotorType.kBrushless);

    shooterEncoder = new Encoder(8,9,false,EncodingType.k4X);
    shooterEncoder.setDistancePerPulse(1.0/360.0);

    triggerMotor.restoreFactoryDefaults();
    hoodMotor.restoreFactoryDefaults();
    shooterLeader.restoreFactoryDefaults();
    shooterFollower.restoreFactoryDefaults();
  
    shooterFollower.follow(shooterLeader, true);

    triggerMotor.enableVoltageCompensation(12);
    hoodMotor.enableVoltageCompensation(12);
    shooterLeader.enableVoltageCompensation(12);
    shooterFollower.enableVoltageCompensation(12);

    // shooterLeader.setClosedLoopRampRate(0.5);

    hoodMotor.setSmartCurrentLimit((int)Math.round(ShooterConstants.kHoodSafetyCurrentLimit));

    hoodEncoder = hoodMotor.getEncoder();
    masterEncoder = shooterLeader.getEncoder();

    shooterLeader.setOpenLoopRampRate(0.1);

    hoodEncoder.setPositionConversionFactor(ShooterConstants.kHoodGearingRatio);

    hoodController = hoodMotor.getPIDController();
    shooterController = shooterLeader.getPIDController();

    hoodController.setP(ShooterConstants.kHoodP);
    hoodController.setI(ShooterConstants.kHoodI);
    hoodController.setD(ShooterConstants.kHoodD);

    shooterController.setP(ShooterConstants.kShooterP);
    shooterController.setI(ShooterConstants.kShooterI); 
    shooterController.setD(ShooterConstants.kShooterD);

    shooterLeader.setIdleMode(IdleMode.kBrake);
    hoodMotor.setIdleMode(IdleMode.kBrake);
    triggerMotor.setIdleMode(IdleMode.kBrake);

    hoodEncoder.setPosition(ShooterConstants.kHoodMinAngle); // Assume hood starts completely down/retracted.
  }

  public void periodic() {
    SmartDashboard.putNumber("Hood Motor Current", hoodMotor.getOutputCurrent());
    SmartDashboard.putNumber("Hood Angle", getHoodAngle());
    SmartDashboard.putNumber("Shooter Velocity", getShooterVelocity());
    SmartDashboard.putNumber("Shooter Encoder", shooterEncoder.getDistance());
    SmartDashboard.putNumber("Leader Current", shooterLeader.getOutputCurrent());
    SmartDashboard.putNumber("Follower Current", shooterFollower.getOutputCurrent());

    checkHoodCurrentLimit();

    runTrigger();
  }

  public void resetHoodAngle() {
    hoodEncoder.setPosition(ShooterConstants.kHoodMinAngle);
    setHoodPosition(ShooterConstants.kHoodMinAngle);
  }

  public void resetHoodEncoder() {
    hoodEncoder.setPosition(ShooterConstants.kHoodMinAngle);
  }

  public boolean checkHoodCurrentLimit() {
    if ( ShooterConstants.kHoodStopCurrentLimit < hoodMotor.getOutputCurrent()) {
      highCurrentCount++;
      if (10 <= highCurrentCount) {
        SmartDashboard.putBoolean("Hood Hard Stop Hit", true);
        // Reset accumulator
        highCurrentCount = 0;
        stopHood();
        // Assume a high current means we are at the bottom. And reset the encoder.
        resetHoodAngle();
        return true;
      } else {  // high current, but accumulator not full
        return false;
      }
    } else { // Within current limit
      lowCurrentCount++;
      if (10 <= lowCurrentCount) {
        highCurrentCount = 0;
        lowCurrentCount = 0;
      }
      return false;
    }
  }

  public void moveHood(boolean direction) {
    this.hoodDirection = direction;

    double speed = direction ? ShooterConstants.kHoodSpeed : -1.0 * ShooterConstants.kHoodSpeed;
    if (checkHoodCurrentLimit()) {
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

  public void setHoodSpeed(double speed) {
    hoodMotor.set(speed);
  }

  public double getHoodCurrent() {
    return hoodMotor.getOutputCurrent();
  }

  public double getEncoderPosition() {
    return shooterEncoder.getDistance() / 2.0;
  }

  public void setHoodPosition(double degrees) {
    // degrees = Math.min(Math.max(degrees, ShooterConstants.kHoodMinAngle), ShooterConstants.kHoodMaxAngle);
    double reference = Math.max(60, Math.min(100, degrees));
    hoodController.setReference(reference, ControlType.kPosition, 0, 0.0, ArbFFUnits.kPercentOut);
  }

  public void setShooterVelocity(double velocity) {
    this.targetVelocity = velocity;
    shooterController.setReference(targetVelocity, ControlType.kVelocity, 0, targetVelocity * ShooterConstants.kShooterFF, ArbFFUnits.kPercentOut);
  }

  public void setShooterVoltage(double voltage) {
    shooterLeader.setVoltage(voltage);
  }

  public double getShooterVelocity() {
    return masterEncoder.getVelocity();
  }

  public double getShooterPosition() {
    return masterEncoder.getPosition();
  }

  public double getTargetVelocity() {
    return this.targetVelocity;
  }

  public void stopHood() {
    hoodMotor.stopMotor();
  }

  public void stopShooter() {
    shooterLeader.stopMotor();
    this.targetVelocity = 0.0;
  }

  public double getHoodAngle() {
    return hoodEncoder.getPosition();
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
      triggerMotor.set(ShooterConstants.kTriggerSpeed);
    } else if (triggerPull && getShooterVelocity() > targetVelocity * ShooterConstants.kTriggerDeadband) {
      triggerMotor.set(-ShooterConstants.kTriggerSpeed);
    } else {
      triggerMotor.stopMotor();
    }
  }

  public static class ShooterState {
    public final double hoodAngle;
    public final double velocity;
    public ShooterState(double hoodAngle, double velocity) {
      this.hoodAngle = hoodAngle;
      this.velocity = velocity;
    }
  }

  public static double lookupVelocity(double distance) {
    return INTERPOLATION_TABLE.sample(distance)[0];
  }

  public static double lookupHoodAngle(double distance) {
    return INTERPOLATION_TABLE.sample(distance)[1];
  }
}