// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

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

  private final DoubleSolenoid hand;
  private final DoubleSolenoid wrist;

  private final DutyCycleEncoder encoder;

  private boolean isWrist = false;

  public ArmSubsystem() {
    left = new CANSparkMax(Constants.ArmSparks.LEFT, MotorType.kBrushless);
    right = new CANSparkMax(Constants.ArmSparks.RIGHT, MotorType.kBrushless);
    hand = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.PCMDevices.HAND_FORWARD, Constants.PCMDevices.HAND_BACKWARD);
    wrist = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.PCMDevices.WRIST_FORWARD, Constants.PCMDevices.WRIST_BACKWARD);
    right.follow(left, true);
    encoder = new DutyCycleEncoder(Constants.ENCOER_PORT);
    encoder.setDistancePerRotation(360.0);
    encoder.reset();
    hand.set(Value.kForward);
    wrist.set(Value.kReverse);
  }

  public void manualControl(double speed) {
    speed = speed / 5.0;
    if (speed < 0 || encoder.getDistance() >= Constants.ArmLimits.TOP_LIMIT) {
      speed = 0;
    }
    left.set(speed);
  }

  public void grip() {
    hand.toggle();
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
