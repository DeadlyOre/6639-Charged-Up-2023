// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class ManualArmCommand extends CommandBase {
  /** Creates a new ArmCommand. */
  private final ArmSubsystem arm;
  // private final boolean speed;
  // private final boolean on;
  private final XboxController controller;


  public ManualArmCommand(ArmSubsystem arm, XboxController controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.arm = arm;
    // this.speed = speed;
    // this.on = on;
    this.controller = controller;
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (arm.getIsBrake() == false) {
      arm.manualControl(-controller.getRightY());
    } else {
      arm.manualControl(0.0);
    }
    SmartDashboard.putBoolean("Brake", arm.getIsBrake());
    SmartDashboard.putNumber("encoder", arm.getEncoderValue());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
