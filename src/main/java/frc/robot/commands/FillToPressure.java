package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AirCannon;

public class FillToPressure extends Command{
    
    public final AirCannon airCannon;
    public final double fillPressure;

    public FillToPressure(double fillPressure, AirCannon airCannon) {
        this.fillPressure = fillPressure;
        this.airCannon = airCannon;
        addRequirements(airCannon);
    }

    @Override
    public void execute() {
        if ((airCannon.getTankPressure() < fillPressure)) {
            airCannon.fillOn();
        }
    }

    @Override
    public void end(boolean interrupted) {
        airCannon.fillOff();
    }

}
