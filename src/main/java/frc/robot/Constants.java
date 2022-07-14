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
    //Spark Max IDs
    public static int motorL1 = 55;
    public static int motorL2 = 61;
    public static int motorR1 = 52;
    public static int motorR2 = 32;

    public final static int intakeMotorID = 58;

    public final static int carouselSpinnerID = 34;

    //Pneumatics
    public static int climberSolenoidID = 0;

    public final static int[] intakeSolenodIDS = {4,2};

    public static int PCM = 0;

    // Indexer Vars
    public final static double feederWheelSpeed = 1;
    public final static double carouselSpinnerSpeed = .35;
    public final static double carouselGearRatio = 4*7;
    public final static double fullCarouselSpin = carouselGearRatio*3;

    //Intake vars
    public static double intakeMotorSpeed = 1;

}
