// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class driveCommand extends CommandBase {
  /** Creates a new driveCommand. */
  private final DriveSubsystem mdrive;
  private final XboxController mxbox;

 
  public driveCommand(DriveSubsystem drive, XboxController Xbox) {
    // Use addRequirements() here to declare subsystem dependencies.
    mdrive = drive;
    mxbox = Xbox;
    addRequirements(mdrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mdrive.drive(-mxbox.getLeftY(),-mxbox.getLeftX());
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
