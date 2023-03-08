// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.*;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  XboxController controller = new XboxController(Constants.CONTROLLER);
  SendableChooser<Command> mChooser = new SendableChooser<>();
  
  
  private final DriveSubsystem mDrive = new DriveSubsystem();
  private final ArmSubsystem mArm = new ArmSubsystem();
  private final Command easy = new EasyDrive(mDrive, -0.4, 0).withTimeout(5);
  private final Command hard = new SequentialCommandGroup(
    new AutoArmCommand(mArm, 0.3).withTimeout(2),
    new WristCommand(mArm).withTimeout(0.1),
    new GripCommand(mArm).withTimeout(2),
    new EasyDrive(mDrive, -0.4, 0).withTimeout(6)
  );
  private final Command justScore = new SequentialCommandGroup(
    new AutoArmCommand(mArm, 0.3).withTimeout(2),
    new WristCommand(mArm).withTimeout(0.1),
    new GripCommand(mArm).withTimeout(2)
  );

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    SmartDashboard.putBoolean("gear Shift", mDrive.getShift());
    configureButtonBindings();
    mDrive.setDefaultCommand(new driveCommand(mDrive, controller));
    mArm.setDefaultCommand(new ManualArmCommand(mArm, controller));
    mChooser.setDefaultOption("None", null);
    mChooser.addOption("Just Drive", easy);
    mChooser.addOption("Score and drive", hard);
    mChooser.addOption("Just Score", justScore);

    SmartDashboard.putData(mChooser);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {



    JoystickButton grip = new JoystickButton(controller, XboxController.Button.kLeftBumper.value);
    grip.onTrue(new GripCommand(mArm).withTimeout(0.1));
    JoystickButton gearShift = new JoystickButton(controller, XboxController.Button.kX.value);
    gearShift.onTrue(new ShiftCommand(mDrive).withTimeout(0.1));
    JoystickButton wrist = new JoystickButton(controller, XboxController.Button.kRightBumper.value);
    wrist.onTrue(new WristCommand(mArm).withTimeout(0.1));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return mChooser.getSelected();
  }
}
