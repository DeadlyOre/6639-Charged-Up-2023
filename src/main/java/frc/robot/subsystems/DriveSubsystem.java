// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenixpro.hardware.TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */

  private final TalonFX left1;
  private final TalonFX left2;
  private final TalonFX left3;
  private final TalonFX right1;
  private final TalonFX right2;
  private final TalonFX right3;

  private final DifferentialDrive mDrive;

  //private final CANcoder fleft;
//  private final CANcoder fright;

  private boolean isShift = false;

  private final DoubleSolenoid gearShfit;

  public DriveSubsystem() {
    left1 = new TalonFX(Constants.DriveMotors.LEFT1);
    left2 = new TalonFX(Constants.DriveMotors.LEFT2);
    left3 = new TalonFX(Constants.DriveMotors.LEFT3);
    right1 = new TalonFX(Constants.DriveMotors.RIGHT1);
    right2 = new TalonFX(Constants.DriveMotors.RIGHT2);
    right3 = new TalonFX(Constants.DriveMotors.RIGHT3);

    //fleft = new CANcoder(left1.getDeviceID());
    //fright = new CANcoder (right1.getDeviceID());

   // fleft.setPosition(0);
    //fright.setPosition(0);

    left2.setInverted(true);

    right1.setInverted(true);
    right3.setInverted(true);

    gearShfit = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.PCMDevices.GEAR_SHIFT_FORWARD, Constants.PCMDevices.GEAR_SHIFT_BACKWARD);

    MotorControllerGroup left = new MotorControllerGroup(left1, left2, left3);
    MotorControllerGroup right = new MotorControllerGroup(right1, right2, right3);

    gearShfit.set(Value.kForward);

    mDrive = new DifferentialDrive(left, right);
  }

  public void drive (double forward, double clockwise){
    mDrive.arcadeDrive(forward, clockwise); 
    left1.getPosition().refresh();
    //System.out.println (left1.getPosition().getValue()); 
  }

  public void shift() {
    gearShfit.toggle();
  }

  public double getPosition () {
    left1.getPosition().refresh();
    return left1.getPosition().getValue();
  }

  public boolean getShift() {
    return isShift;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
