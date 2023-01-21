// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Drivetrain extends SubsystemBase {
  /* Creates a new Drivetrain. 
   * @param rightMotorID
   * @param leftMotorID
   */

  CANSparkMax frontLeft;
  // CANSparkMax backLeft;

  CANSparkMax frontRight;

  public ADXRS450_Gyro gyro;

  public PIDController turnPID;

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

    gyro = new ADXRS450_Gyro();
    turnPID = new PIDController(Constants.turnkP, Constants.turnkI, Constants.turnkD);
    turnPID.setIntegratorRange(-Constants.pidMaxPercent, Constants.pidMaxPercent);
    // turnPID.enableContinuousInput(-360, 360);
  }

  public void TeleopDrive(double forwardSpeed, double turnSpeed) {

    frontLeft.set(forwardSpeed - turnSpeed);
    // backLeft.set(forwardSpeed - turnSpeed);
    frontRight.set(forwardSpeed + turnSpeed);
    // backRight.set(forwardSpeed + turnSpeed);

  }

  public void ManualDrive(double leftSpeed, double rightSpeed) {
    frontLeft.set(leftSpeed);
    frontRight.set(rightSpeed);
  }

  public void Align(){
    double gyroReadout = Robot.driveTrain.gyro.getAngle();

      if (gyroReadout>5){
        Robot.driveTrain.ManualDrive(-0.1 , 0.1);
      }

      else if (gyroReadout<-5) {
        Robot.driveTrain.ManualDrive(0.1 , -0.1);
      }

      else {
        Robot.driveTrain.ManualDrive(0, 0);
      }
  }

  public void pidAlign() {
    // if (turnPID.atSetpoint()) {
    //   return;
    // }

    double gyroReadout = Robot.driveTrain.gyro.getAngle() % 360;
    double speed = MathUtil.clamp(turnPID.calculate(gyroReadout, Constants.turnSetpoint), -Constants.pidMaxPercent, Constants.pidMaxPercent);
    speed = speed / 100;
    Robot.driveTrain.TeleopDrive(0, speed);
    SmartDashboard.putNumber("Speed",speed);

      // if (!turnPID.atSetpoint()){
      //   // Robot.driveTrain.ManualDrive(-0.1 , 0.1);
      //   Robot.driveTrain.TeleopDrive(0, turnPID.calculate(gyroReadout, Constants.turnSetpoint));
      // }

      // else if (gyroReadout<-5) {
      //   Robot.driveTrain.ManualDrive(0.1 , -0.1);
      // }

      // else {
      //   Robot.driveTrain.ManualDrive(0, 0);
      // }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putData(turnPID);
  
    RobotContainer container = Robot.m_robotContainer;
    if(container.driverL.getRawButton(1) == true && container.driverL.getRawButton(2) == false){
      Robot.driveTrain.pidAlign();
    }
    else if(container.driverL.getRawButton(2) == true && container.driverL.getRawButton(1) == false){
      Robot.driveTrain.Align();

    }
    else{
      Robot.driveTrain.ManualDrive(0, 0);
    }
  }
}
