// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class EasyDrive extends CommandBase {
  /** Creates a new EasyDrive. */
  private final DriveSubsystem drive;
  private final double speed;
  private final double rotation;

  public EasyDrive(DriveSubsystem drive, double speed, double rotation) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drive = drive;
    this.speed = speed;
    this.rotation = rotation;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.drive(speed, rotation);
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
