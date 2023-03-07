// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenixpro.hardware.CANcoder;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveUntil extends CommandBase {
  /** Creates a new DriveUntil. */
  private final DriveSubsystem drive;
  private final double rotations;
  //private final CANcoder fleft;


  public DriveUntil(DriveSubsystem drive, double rotations) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.rotations = rotations;
    this.drive = drive;
   // this.fleft = drive.getFleft();
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //fleft.setPosition(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //fleft.getPosition().refresh();
   // double position = fleft.getPosition().getValue();
  double position = drive.getPosition();
    if (position <= rotations) { //TODO set correctly
      drive.drive(0.25, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
