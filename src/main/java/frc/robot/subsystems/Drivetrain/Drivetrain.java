package frc.robot.subsystems.Drivetrain;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Constants;
import monologue.Annotations.Log;
import monologue.Logged;
import monologue.Monologue;

public class Drivetrain extends SubsystemBase implements Logged{
    
    private final CANSparkMax leftLeader, leftFollower, rightLeader, rightFollower;
    private final RelativeEncoder leftEncoder, rightEncoder;

    private final DifferentialDrive differentialDrive;

    public Drivetrain() {

        leftLeader = new CANSparkMax(Constants.Drivetrain.LeftLeaderID, MotorType.kBrushless);
        leftLeader.restoreFactoryDefaults();
        leftLeader.setIdleMode(IdleMode.kBrake);
        leftLeader.setInverted(false);
        
        leftFollower = new CANSparkMax(Constants.Drivetrain.LeftFollowerID, MotorType.kBrushless);
        leftFollower.restoreFactoryDefaults();
        leftFollower.setIdleMode(IdleMode.kBrake);
        leftFollower.follow(leftLeader);

        rightLeader = new CANSparkMax(Constants.Drivetrain.RightLeaderID, MotorType.kBrushless);
        rightLeader.restoreFactoryDefaults();
        rightLeader.setIdleMode(IdleMode.kBrake);
        rightLeader.setInverted(false);
    
        rightFollower = new CANSparkMax(Constants.Drivetrain.RightFollowerID, MotorType.kBrushless);
        rightFollower.restoreFactoryDefaults();
        rightFollower.setIdleMode(IdleMode.kBrake);
        rightFollower.follow(leftLeader);

        leftEncoder = leftLeader.getEncoder();
        leftEncoder.setPosition(0);

        rightEncoder = rightLeader.getEncoder();
        rightEncoder.setPosition(0);

        differentialDrive = new DifferentialDrive(leftLeader, rightLeader);
    }

    @Override
    public void periodic() {
        log("Right Encoder", rightEncoder.getPosition());
        log("Left Encoder", leftEncoder.getPosition());
    }

    public void arcadeDrive(double forwardSpeed, double turnSpeed) {
        log("Target forward speed", forwardSpeed);
        log("Target turn speed", turnSpeed);
        differentialDrive.arcadeDrive(forwardSpeed, turnSpeed);
    }

    @Log
    public double getLeftDistanceMeters() {
        return leftEncoder.getPosition();
    }

    @Log
    public double getRightDistanceMeters() {
        return rightEncoder.getPosition();
    }

    public double getAverageMeters() {
        return (getLeftDistanceMeters() + getRightDistanceMeters()) / 2;
    }

    public void setLeftEncoderPosition(double position) {
        leftEncoder.setPosition(position);
    }

    public void setRightEncoderPosition(double position) {
        rightEncoder.setPosition(position);
    }

    public void resetEncoders() {
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }
}
