// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Indexer extends SubsystemBase {
  /** Creates a new Indexer. */
  CANSparkMax carouselSpinner;
  CANSparkMax feederWheel2;

  public static RelativeEncoder carouselEncoder;

  public boolean runningFeedCommand;
  public boolean runningIntakeCommand;

  public Indexer() {
    carouselSpinner = new CANSparkMax(Constants.carouselSpinnerID, MotorType.kBrushless);
    feederWheel2 = new CANSparkMax(48, MotorType.kBrushless);

    carouselSpinner.setIdleMode(IdleMode.kCoast);

    feederWheel2.set(0);
    carouselSpinner.set(0);
  
    feederWheel2.setInverted(true);

    carouselEncoder = carouselSpinner.getEncoder();

    runningFeedCommand = false;
    runningIntakeCommand = false;
  }
  public void spinCarousel(double speed) {
    carouselSpinner.set(speed);
  }
  public void spinFeeder(double speed) {
    feederWheel2.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (Robot.currentState == "Autonomous") {
      return;
    }
    if (runningIntakeCommand == true) {
      spinFeeder(-Constants.feederWheelSpeed/8);
    } else {
      if (Robot.m_robotContainer.operatorR.getRawButton(1)) {
        spinFeeder(Constants.feederWheelSpeed);
        spinCarousel(-Constants.carouselSpinnerSpeed*.7);
      } else if (Robot.m_robotContainer.operatorR.getRawButton(2)) {
        spinFeeder(-Constants.feederWheelSpeed/3);
        spinCarousel(-Constants.carouselSpinnerSpeed);
      } else {
        spinFeeder(0);
        spinCarousel(0);
      }
    }
  }
}
