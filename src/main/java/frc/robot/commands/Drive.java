// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class Drive extends CommandBase {
  /** Creates a new TeleopDrive. */
  public Drive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.driveTrain);
  }

  

// Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double xSpeed = -Robot.m_robotContainer.driverL.getY();
    double ySpeed = -Robot.m_robotContainer.driverR.getX();
    double driveSpeed = 0;
    double trnSpeed = 0;

    if (xSpeed>0) {
      driveSpeed = Math.abs(Math.pow(xSpeed, Constants.driveExpo));
    }
    if (xSpeed<0) {
      driveSpeed = -Math.pow(Math.abs(xSpeed), Constants.driveExpo);
    }
    if (ySpeed>0) {
      trnSpeed = Math.abs(Math.pow(ySpeed, Constants.turnExpo));
    }
    if (ySpeed<0) {
      trnSpeed = -Math.pow(Math.abs(ySpeed), Constants.turnExpo);
    }



      Robot.driveTrain.TeleopDrive(driveSpeed, trnSpeed);
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
