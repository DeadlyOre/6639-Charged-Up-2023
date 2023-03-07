// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DriveUntil;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/** Add your docs here. */
public class AutoRoutines {

    private final DriveSubsystem drive;
    private final Command command;

    public AutoRoutines(DriveSubsystem drive) {
        this.drive = drive;
        command = new DriveUntil(drive, 1);
    }

    public void SimpleAuto() {
        command.withTimeout(1);
    }

}
