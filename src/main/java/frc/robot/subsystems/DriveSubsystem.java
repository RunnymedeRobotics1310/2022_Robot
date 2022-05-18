package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

    // The motors on the left side of the drive.
    private final CANSparkMax leftPrimaryMotor =
            new CANSparkMax(DriveConstants.LEFT_MOTOR_PORT, MotorType.kBrushless);
    private final CANSparkMax leftFollowerMotor =
            new CANSparkMax(DriveConstants.LEFT_MOTOR_PORT+1, MotorType.kBrushless);

    // The motors on the right side of the drive.
    private final CANSparkMax rightPrimaryMotor =
            new CANSparkMax(DriveConstants.RIGHT_MOTOR_PORT, MotorType.kBrushless);
    private final CANSparkMax rightFollowerMotor =
            new CANSparkMax(DriveConstants.RIGHT_MOTOR_PORT+1, MotorType.kBrushless);

    /** Creates a new DriveSubsystem. */
    public DriveSubsystem() {
        // We need to invert one side of the drivetrain so that positive voltages
        // result in both sides moving forward. Depending on how your robot's
        // gearbox is constructed, you might have to invert the left side instead.
        leftPrimaryMotor .setInverted(DriveConstants.LEFT_MOTOR_REVERSED);
        leftFollowerMotor.setInverted(DriveConstants.LEFT_MOTOR_REVERSED);

        rightPrimaryMotor .setInverted(DriveConstants.RIGHT_MOTOR_REVERSED);
        rightFollowerMotor.setInverted(DriveConstants.RIGHT_MOTOR_REVERSED);


    }

    /**
     * Gets the average distance of the two encoders.
     *
     * @return the average of the two encoder readings
     */
    public double getAverageEncoderDistance() {
        return (leftPrimaryMotor.getEncoder().getPosition() + rightPrimaryMotor.getEncoder().getPosition()) / 2;
    }

    /**
     * Gets the left drive encoder.
     *
     * @return the left drive encoder
     */
    public double getLeftEncoder() {
        return leftPrimaryMotor.getEncoder().getPosition();
    }

    /**
     * Gets the right drive encoder.
     *
     * @return the right drive encoder
     */
    public double getRightEncoder() {
        return rightPrimaryMotor.getEncoder().getPosition();
    }

    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
        leftPrimaryMotor.getEncoder().getPosition();
        rightPrimaryMotor.getEncoder().getPosition();
    }

    public void setMotorSpeeds(double leftSpeed, double rightSpeed) {

        leftPrimaryMotor.set(leftSpeed);
        leftFollowerMotor.set(leftSpeed);

        rightPrimaryMotor.set(rightSpeed);
        rightFollowerMotor.set(rightSpeed);
    }

    @Override
    public void periodic() {

        SmartDashboard.putNumber("Right Motor", rightPrimaryMotor.get());
        SmartDashboard.putNumber("Left  Motor", leftPrimaryMotor.get());
    }
}
