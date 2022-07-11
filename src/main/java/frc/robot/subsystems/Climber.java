// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.lang.invoke.ConstantBootstraps;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */

  public Solenoid climberSolenoid;
  public Climber() {

    climberSolenoid = new Solenoid(Constants.PCM, PneumaticsModuleType.CTREPCM, Constants.climberSolenoidID);
    climberSolenoid.set(false);

  }

  public void ClimberUp(Joystick operatorR, Joystick operatorL) {

    if (operatorR.getRawButton(8) && (operatorL.getRawButton(14))) {
      climberSolenoid.set(true);
    }
  }
  public void ClimberDown() {
    climberSolenoid.set(false);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (Robot.currentState == "Autonomous" || Robot.currentState == "Initializing") {
      climberSolenoid.set(false);
    }
    if (Robot.m_robotContainer.operatorR.getRawButton(8) && Robot.m_robotContainer.operatorL.getRawButton(14)) {
      Robot.currentState = "Climbing";
      climberSolenoid.set(true);
    } else if (Robot.m_robotContainer.operatorR.getRawButton(8) == false && Robot.m_robotContainer.operatorL.getRawButton(14) == false) {
      climberSolenoid.set(false);
    }
  }
}
