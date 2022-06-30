// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  CANSparkMax motorL1;
  CANSparkMax motorL2;
  CANSparkMax motorR1;
  CANSparkMax motorR2;

  public Drivetrain() {
    motorL1 = new CANSparkMax(Constants.motorL1, MotorType.kBrushed);
    motorL2 = new CANSparkMax(Constants.motorL2, MotorType.kBrushed);
    motorR1 = new CANSparkMax(Constants.motorR1, MotorType.kBrushed);
    motorR2 = new CANSparkMax(Constants.motorR2, MotorType.kBrushed);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void TeleopDrive(double forwardSpeed, double turnSpeed) {
    motorL1.set(forwardSpeed + turnSpeed);
    motorL2.set(forwardSpeed + turnSpeed);
    motorR1.set(forwardSpeed - turnSpeed);
    motorR2.set(forwardSpeed - turnSpeed);

  }
}
