// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {
  /** Creates a new ArmSubsystem. */
  private final CANSparkMax left;
  private final CANSparkMax right;

  private final WPI_VictorSPX leftHandSpinner;
  private final WPI_VictorSPX rightHandSpinner;

  private final DoubleSolenoid hand;
  private final DoubleSolenoid wrist;

  private final DutyCycleEncoder encoder;

  private boolean isWrist = false;

  public ArmSubsystem() {
    left = new CANSparkMax(Constants.ArmSparks.LEFT, MotorType.kBrushless);
    right = new CANSparkMax(Constants.ArmSparks.RIGHT, MotorType.kBrushless);
    hand = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.PCMDevices.HAND_FORWARD, Constants.PCMDevices.HAND_BACKWARD);
    wrist = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.PCMDevices.WRIST_FORWARD, Constants.PCMDevices.WRIST_BACKWARD);
    leftHandSpinner = new WPI_VictorSPX(Constants.HandSpinners.LEFT);
    rightHandSpinner = new WPI_VictorSPX(Constants.HandSpinners.RIGHT);
    right.follow(left, true);
    rightHandSpinner.setInverted(false);
    rightHandSpinner.follow(leftHandSpinner);
    encoder = new DutyCycleEncoder(Constants.ENCOER_PORT);
    encoder.setDistancePerRotation(360.0);
    encoder.reset();
    wrist.set(Value.kReverse);
    hand.set(Value.kForward);
  }

  public void manualControl(double speed) {
    if (speed >= 0) {
      speed = speed * (4.0 / 5.0);
      // if (encoder.getDistance() > Constants.ArmLimits.TOP_LIMIT) {
      //   speed = 0;
      // }
    } else if (speed < 0) {
      speed = speed * (1.0 / 5.0);
      // if (encoder.getDistance() < Constants.ArmLimits.BOTTOM_LIMIT) {
      //   speed = 0;
      // }
    }

    left.set(speed);
  }

  public void resetEncoder() {
    encoder.reset();
  }

  public void grip() {
    hand.toggle();
  }

  public void handSpin(boolean direction, boolean on) {
    if (on == true) {
      if (direction == true) {
        leftHandSpinner.set(0.3);
      } else {
        leftHandSpinner.set(-0.3);
      }
    } else {
      leftHandSpinner.set(0);
    }
  }

  public boolean getIsWrist() {
    return isWrist;
  }

  public double getEncoderValue() {
    return encoder.getDistance();
  }

  public void wrist() {
    wrist.toggle();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
