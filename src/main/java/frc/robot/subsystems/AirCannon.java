package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import monologue.Logged;

public class AirCannon extends SubsystemBase implements Logged{

    private final Solenoid solenoid;

    public AirCannon() {
        solenoid = new Solenoid(PneumaticsModuleType.REVPH, 0);
        solenoid.setPulseDuration(2);
    }

    @Override
    public void periodic() {
        
    }

    public Command fire() {
        return new InstantCommand(
            () -> {
                solenoid.startPulse();
            },
            this
        );
    }
}