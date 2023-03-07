// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {
  /** Creates a new ArmSubsystem. */
  private final CANSparkMax left;
  private final CANSparkMax right;
 // private final DigitalInput top;
 // private final DigitalInput bottom;

  private final DoubleSolenoid brake;
  private final DoubleSolenoid hand;
  private final DoubleSolenoid wrist;

  private final DutyCycleEncoder encoder;

  private boolean isBrake = false;
  private boolean isWrist = false;

  public ArmSubsystem() {
    left = new CANSparkMax(Constants.ArmSparks.LEFT, MotorType.kBrushless);
    right = new CANSparkMax(Constants.ArmSparks.RIGHT, MotorType.kBrushless);
  //  top = new DigitalInput(Constants.Limits.TOP);
 //   bottom = new DigitalInput(Constants.Limits.BOTTOM);
    brake = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.PCMDevices.BRAKE_FORWARD, Constants.PCMDevices.BRAKE_BACKWARD);
    hand = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.PCMDevices.HAND_FORWARD, Constants.PCMDevices.HAND_BACKWARD);
    wrist = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.PCMDevices.WRIST_FORWARD, Constants.PCMDevices.WRIST_BACKWARD);
    right.follow(left, true);
    encoder = new DutyCycleEncoder(Constants.ENCOER_PORT);
    encoder.setDistancePerRotation(360.0);
    encoder.reset();
    hand.set(Value.kForward);
    brake.set(Value.kReverse);
    wrist.set(Value.kReverse);
  }

  public void manualControl(double speed) {
    // if (!isBrake){
    //   if (top.get() && speed > 0) {
    //     left.set(0);
    //   } else if (bottom.get() && speed < 0) {
    //     left.set(0);
    //   } else {
    //     left.set(speed);
    //   }
    // } else {
    //   left.set(0);
    // }
    // if (on) {
    //   if (speed) {
    //     left.set(0.2);
    //   } else {
    //     left.set(0.0);
    //   }
    // } else {
    //   left.set(0);
    // }
    //System.out.println(encoder.getDistance());
    speed = speed / 5.0;
    if (speed < 0) {
      speed = 0;
    }
    left.set(speed);
  }

  public void grip() {
    hand.toggle();
  }

  public void brake() {
    brake.toggle();
    if (isBrake == false) {
      isBrake = true;
    } else {
      isBrake = false;
    }
    SmartDashboard.putBoolean("brake", isBrake);
  }



  public double getEncoderValue() {
    return encoder.getDistance();
  }

  public void wrist() {
    wrist.toggle();
  }

  public boolean getIsBrake() {
    return isBrake;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
