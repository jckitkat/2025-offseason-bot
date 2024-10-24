// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.FillToPressure;
import frc.robot.subsystems.AirCannon;
import frc.robot.subsystems.Drivetrain.Drivetrain;
import frc.robot.util.Constants;
import monologue.Logged;
import monologue.Monologue;
import monologue.Annotations.Log;

public class RobotContainer implements Logged{
  
  Drivetrain drivetrain = new Drivetrain();
  AirCannon airCannon = new AirCannon();

  PS5Controller controller = new PS5Controller(0);
  
  public RobotContainer() {
    configureBindings();

    drivetrain.setDefaultCommand(new ArcadeDrive(
      () -> controller.getRawAxis(1),
      () -> controller.getRawAxis(2),
      drivetrain
    ));

    Monologue.setupMonologue(this, "Robot", false, true);
  }

  private void configureBindings() {
    Trigger pulseFire = new Trigger(() -> controller.getRawButton(8));
    Trigger fillTrigger = new Trigger(() -> controller.getRawButton(7));

    // fillTrigger.onTrue(new FillToPressure(SmartDashboard.getNumber("desired pressure", 0), airCannon).onlyWhile(() -> (SmartDashboard.getBoolean("At Desired Pressure", true) == false)));

    // fillTrigger.onFalse(new InstantCommand(() -> {
    //   if (airCannon.getCurrentCommand() != null) {
    //     airCannon.getCurrentCommand().end(true);
    //   } else {
    //     airCannon.fillOff();
    //   }
    // }));

    fillTrigger.onTrue(new InstantCommand(() -> {airCannon.fillOn();}));
    fillTrigger.onFalse(new InstantCommand(() -> {airCannon.fillOff();}));

    pulseFire.onTrue(new InstantCommand(() -> {
      airCannon.pulseFire();
    }));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }

  public void loggingPeriodic() {
    Monologue.updateAll();
  }
}
