// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DanceAuto extends SequentialCommandGroup {
  /** Creates a new DanceAuto. */
  public DanceAuto() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      //to the left
      new ManualDrive(-Constants.longMove,Constants.longMove),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(Constants.longMove,Constants.longMove),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(Constants.longMove, -Constants.longMove),
      new WaitCommand(Constants.waitCommand),
      //take it back now y'all
      new ManualDrive(-Constants.longMove,-Constants.longMove),
      new WaitCommand(Constants.waitCommand),
      //hop
      new ManualDrive(Constants.hop,Constants.hop),
      new WaitCommand(Constants.waitCommand),
      //right foot stomp
      new ManualDrive(Constants.kick,-Constants.kick),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(-Constants.kick,Constants.kick),
      new WaitCommand(Constants.waitCommand),
      //left foot stomp
      new ManualDrive(-Constants.kick,Constants.kick),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(Constants.kick,-Constants.kick),
      new WaitCommand(Constants.waitCommand),
      //chacha
      new ManualDrive(Constants.kick,-Constants.kick),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(-Constants.kick * 2,Constants.kick * 2),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(Constants.kick * 2,-Constants.kick * 2),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(-Constants.kick * 2,Constants.kick * 2),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(Constants.kick * 2,-Constants.kick * 2),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(-Constants.kick * 2,Constants.kick * 2),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(Constants.kick * 2,-Constants.kick * 2),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(-Constants.kick,Constants.kick),
      //chacha done
      //turn around
      new ManualDrive(-Constants.longMove,Constants.longMove),
      new WaitCommand(Constants.waitCommand),

      //do all the stuff again
      //to the left
      new ManualDrive(-Constants.longMove,Constants.longMove),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(Constants.longMove,Constants.longMove),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(Constants.longMove, -Constants.longMove),
      new WaitCommand(Constants.waitCommand),
      //take it back now y'all
      new ManualDrive(-Constants.longMove,-Constants.longMove),
      new WaitCommand(Constants.waitCommand),
      //hop
      new ManualDrive(Constants.hop,Constants.hop),
      new WaitCommand(Constants.waitCommand),
      //right foot stomp
      new ManualDrive(Constants.kick,-Constants.kick),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(-Constants.kick,Constants.kick),
      new WaitCommand(Constants.waitCommand),
      //left foot stomp
      new ManualDrive(-Constants.kick,Constants.kick),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(Constants.kick,-Constants.kick),
      new WaitCommand(Constants.waitCommand),
      //chacha
      new ManualDrive(Constants.kick,-Constants.kick),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(-Constants.kick * 2,Constants.kick * 2),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(Constants.kick * 2,-Constants.kick * 2),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(-Constants.kick * 2,Constants.kick * 2),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(Constants.kick * 2,-Constants.kick * 2),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(-Constants.kick * 2,Constants.kick * 2),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(Constants.kick * 2,-Constants.kick * 2),
      new WaitCommand(Constants.waitCommand),
      new ManualDrive(-Constants.kick,Constants.kick)
      
    );
  }
}
