package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import monologue.Logged;

public class AirCannon extends SubsystemBase implements Logged{

    private final PneumaticHub pneumaticHub;
    private final Solenoid solenoid;

    public AirCannon() {
        pneumaticHub = new PneumaticHub();
        solenoid = pneumaticHub.makeSolenoid(0);
        solenoid.setPulseDuration(1);
    }

    @Override
    public void periodic() {
        
    }

    public Command fire() {
        return new InstantCommand(
            () -> {
                if (getPressure() > 12)
                {
                    solenoid.startPulse();
                }
            }
        );
    }

    public double getPressure() {
        return pneumaticHub.getPressure(0);
    }
}