// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  public final CANSparkMax shooter1, shooter2;

  public final RelativeEncoder shooter1Encoder;
  public final RelativeEncoder shooter2Encoder;

  public static double[] pastY = {};
  public static double[] pastX = {};
  
  public static double shooterHeight = .3;
  public static double goalHeight = 2.64;

  public Shooter() {
    shooter1 = new CANSparkMax(Constants.shooterMtrs[0], MotorType.kBrushless);
    shooter2 = new CANSparkMax(Constants.shooterMtrs[1], MotorType.kBrushless);

    shooter1Encoder = shooter1.getEncoder();
    shooter2Encoder = shooter2.getEncoder();

    shooter1.setInverted(false);
    shooter2.setInverted(true);

  }

  public void ShootCommand(double speed) {
    double adjust = ((-Robot.m_robotContainer.operatorR.getThrottle()) + 1)/10;
    SmartDashboard.putNumber("Adjustment", adjust);
    if (Math.abs(Robot.m_robotContainer.operatorR.getY()) <= Constants.spinUpThreshold) {
      adjust = 0;
    }
    speed += adjust;
    shooter1.set(speed);
    shooter2.set(speed);
  }

  public double[] findRPMs(double dist) {
    double[] rpms = {};
    int interval = 20;
    for(int a = 50*interval; a<80*interval; a++) {
      a /= interval;
      for(int v = 6*interval; v<13*interval; v++) {
        v /=  interval;

        double vx = v*Math.cos(Math.toRadians(a));
        double vy = v*Math.sin(Math.toRadians(a));
        double yA = shooterHeight+(vy*(-vy/9.81))+(.5*9.81*Math.pow((-vy/9.8), 2));
        double score = (shooterHeight-4.9*Math.pow((dist/vx), 2))+(Math.tan(Math.toRadians(a))*dist);

        double verticalClearance = yA-goalHeight;
        double horizontalClearance = vx*(-vy/9.81);
      }
    }
    return rpms;
  }
  private double angleFinder(double y) {
    double nativeAngle = 20; // Mounted angle of the limelight
    double correction = -3; // Constant error
    double h1 = 9; // Limelight height
    double h2 = 61.5; // Target height
    return (h2-h1) / Math.tan(Math.toRadians(nativeAngle+correction+y));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (Robot.currentState == "Autonomous") {
      return;
    }
    double desiredShootRPM = 4500;
    double desiredDumpRPM = 3000;

    double theadjustment = 10000;
    double threshold = 100;

    double curRPM = (Math.abs(shooter1Encoder.getVelocity())+Math.abs(shooter2Encoder.getVelocity()))/2;

    if (Robot.m_robotContainer.operatorR.getY() <= -Constants.spinUpThreshold) {
      Robot.shooter.ShootCommand(Constants.shootSpeed);
    } else if (Robot.m_robotContainer.operatorR.getY() >= Constants.spinUpThreshold) {
      Robot.shooter.ShootCommand(Constants.dumpSpeed);
    } else {
      Robot.shooter.ShootCommand(0);
    }
  }
}
