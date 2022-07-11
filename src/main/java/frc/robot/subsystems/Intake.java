// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
