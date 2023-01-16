// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /* Creates a new Drivetrain. 
   * @param rightMotorID
   * @param leftMotorID
   */

  CANSparkMax frontLeft;
  // CANSparkMax backLeft;

  CANSparkMax frontRight;
  // CANSparkMax backRight;

  public Drivetrain() {
    frontLeft = new CANSparkMax(Constants.fLID, MotorType.kBrushless);
    // backLeft = new CANSparkMax(Constants.bLID, MotorType.kBrushed);

    frontRight = new CANSparkMax(Constants.fRID, MotorType.kBrushless);
    // backRight = new CANSparkMax(Constants.bRID, MotorType.kBrushed);

    frontLeft.setInverted(true);
    // backLeft.setInverted(true);

    frontRight.setInverted(false);
    // backRight.setInverted(false);

    frontLeft.setIdleMode(IdleMode.kBrake);
    // backLeft.setIdleMode(IdleMode.kBrake);

    frontRight.setIdleMode(IdleMode.kBrake);
    // backRight.setIdleMode(IdleMode.kBrake);
  }

  public void TeleopDrive(double forwardSpeed, double turnSpeed) {

    frontLeft.set(forwardSpeed - turnSpeed);
    // backLeft.set(forwardSpeed - turnSpeed);
    frontRight.set(forwardSpeed + turnSpeed);
    // backRight.set(forwardSpeed + turnSpeed);



  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
