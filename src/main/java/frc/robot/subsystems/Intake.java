// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  public DoubleSolenoid intakeSolenoid;
  CANSparkMax intakeMotor;
  double intakeSpeed = 0.5;

  public Intake() {
    intakeMotor = new CANSparkMax(Constants.intakeMotorID, MotorType.kBrushless);
    intakeSolenoid = new DoubleSolenoid(
      Constants.PCM, 
      PneumaticsModuleType.CTREPCM, 
      Constants.intakeSolenodIDS[0], Constants.intakeSolenodIDS[1]);
    
    intakeMotor.setInverted(true);
  }
  public void IntakeArmState(boolean state) {
    // System.out.println("Changing piston state");
    if (state == true) {
      intakeSolenoid.set(Value.kForward);
    } else {
      intakeSolenoid.set(Value.kReverse);
    }
  }
  public void IntakeMotorState(Joystick operatorL) {
      
    }
  public void IntakeMotorSpeed(double speed) {
    intakeMotor.set(speed);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    RobotContainer container = Robot.m_robotContainer;
    if(container.operatorL.getRawButton(2) == true) {

      IntakeArmState(true); // Regardless of what happens in the following, carousel will be spinning and intake will be down
      Robot.indexer.runningIntakeCommand = true;
      Robot.indexer.spinCarousel(Constants.carouselSpinnerSpeed);

      if(container.operatorL.getRawButton(1) == true) { // Button 1 reverses intake
        IntakeMotorSpeed(-Constants.intakeMotorSpeed); 
      } else if (container.operatorL.getRawButton(3) == true) { // Button 3 stops intake
        IntakeMotorSpeed(0);
      } else { // If no additional buttons are being pressed, regular intake
        IntakeMotorSpeed(Constants.intakeMotorSpeed);
      }
    } else {
      if (container.operatorR.getRawButton(1) == false && container.operatorR.getRawButton(2) == false) {
        Robot.indexer.spinCarousel(0);
      }
      IntakeArmState(false);
      IntakeMotorSpeed(0);
      Robot.indexer.runningIntakeCommand = false;
    }
  }
}