// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class SpinnerCommand extends CommandBase {
  /** Creates a new SpinnerCommand. */
  private final ArmSubsystem arm;
  private final boolean direction;
  private final boolean on;

  public SpinnerCommand(ArmSubsystem arm, boolean direction, boolean on) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.arm = arm;
    this.direction = direction;
    this.on = on;
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    arm.handSpin(direction, on);
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
