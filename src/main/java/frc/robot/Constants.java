// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class DriveMotors {
        public static final int LEFT1 = 1;
        public static final int LEFT2 = 3;
        public static final int LEFT3 = 5;
        public static final int RIGHT1 = 6;
        public static final int RIGHT2 = 4;
        public static final int RIGHT3 = 2;
    }

    public static final class ArmSparks {
        public static final int LEFT = 6; //TODO replace with actual values
        public static final int RIGHT = 5;
    }

    public static final class PCMDevices {
        public static final int HAND_FORWARD = 7;
        public static final int HAND_BACKWARD = 6;
        public static final int GEAR_SHIFT_FORWARD = 0;
        public static final int GEAR_SHIFT_BACKWARD = 1;
        public static final int WRIST_FORWARD = 4;
        public static final int WRIST_BACKWARD = 5;
    }

    public static final class ArmLimits {
        public static final double TOP_LIMIT = 70.0; //TODO set to correct values
        public static final double BOTTOM_LIMIT = 0.0;
    }

    public static final int ENCOER_PORT = 0;

    public static final int CONTROLLER = 0;


}
