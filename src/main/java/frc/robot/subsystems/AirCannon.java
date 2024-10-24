package frc.robot.subsystems;

import javax.swing.Box.Filler;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import monologue.LogLevel;
import monologue.Logged;

public class AirCannon extends SubsystemBase implements Logged{

    private final PneumaticHub pneumaticHub;
    private final Solenoid shotSolenoid;
    private final Solenoid fillSolenoid;

    private boolean desiredFillState;

    public class solenoid {
        public static final boolean open = true;
        public static final boolean closed = false;
    }

    public AirCannon() {
        pneumaticHub = new PneumaticHub();
        shotSolenoid = pneumaticHub.makeSolenoid(2);
        shotSolenoid.setPulseDuration(2);
        fillSolenoid = pneumaticHub.makeSolenoid(0);
        fillSolenoid.setPulseDuration(.25);
        SmartDashboard.putNumber("desired pressure", 0);
    }

    @Override
    public void periodic() {
        log("Shot Tank PSI", getTankPressure(), LogLevel.OVERRIDE_FILE_ONLY);
        log("input pressure", SmartDashboard.getNumber("desired pressure", 0), LogLevel.OVERRIDE_FILE_ONLY);
        log("Fill Valve Open", fillSolenoid.get());
        log("Fill Desired State", desiredFillState);
        SmartDashboard.putBoolean("At Desired Pressure", atDesiredPressure());

        if (getTankPressure() >= SmartDashboard.getNumber("desired pressure", 0)) {
            desiredFillState = solenoid.closed;
        }

        while (fillSolenoid.get() != desiredFillState) {
            fillSolenoid.toggle();
        }
    }

    public double getTankPressure() {
        return pneumaticHub.getPressure(0);
    }

    public boolean atDesiredPressure() {
        return (getTankPressure() >= SmartDashboard.getNumber("desired pressure", 0));
    }

    // public Command fire() {
    //     return new InstantCommand(
    //         () -> {
    //             if (getPressure() > 12)
    //             {
    //                 solenoid.startPulse();
    //             }
    //         }
    //     );
    // }

    public void pulseFill() {
        fillSolenoid.startPulse();
    }

    public void fillOff() {
        desiredFillState = solenoid.closed;
    }

    public void fillOn() {
        desiredFillState = solenoid.open;
    }

    public void fillToggle() {
        fillSolenoid.toggle();
    }

    public void pulseFire() {
        shotSolenoid.startPulse();
    }

}