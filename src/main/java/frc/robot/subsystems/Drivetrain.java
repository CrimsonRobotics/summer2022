// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /* Creates a new Drivetrain. 
   * @param rightMotorID
   * @param leftMotorID
   */

  CANSparkMax leftMotor;
  CANSparkMax rightMotor;
  DifferentialDrive diffDrive;
  public Drivetrain() {
    leftMotor = new CANSparkMax(Constants.leftMotorID, MotorType.kBrushless);
    rightMotor = new CANSparkMax(Constants.rightMotorID, MotorType.kBrushless);

    diffDrive = new DifferentialDrive(leftMotor, rightMotor);
  }

  public void TeleopDrive(double xSpeed, double ySpeed) {
    diffDrive.arcadeDrive(xSpeed, ySpeed);
  }

  public void MotorOverride(double leftSpeed, double rightSpeed){
    leftMotor.set(leftSpeed);
    rightMotor.set(rightSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
