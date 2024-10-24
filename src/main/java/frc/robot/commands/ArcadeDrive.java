package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain.Drivetrain;

public class ArcadeDrive extends Command{
    
    private final Drivetrain drivetrain;
    private final Supplier<Double> forwardSpeed, turnSpeed;

    public ArcadeDrive(Supplier<Double> forwardSpeed, Supplier<Double> turnSpeed, Drivetrain drivetrain) {
        this.forwardSpeed = forwardSpeed;
        this.turnSpeed = turnSpeed;
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        drivetrain.arcadeDrive(forwardSpeed.get() * 0.5, turnSpeed.get() * 0.8);
    }

}
