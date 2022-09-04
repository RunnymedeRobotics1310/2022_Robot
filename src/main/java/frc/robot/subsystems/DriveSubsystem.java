package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

    // The motors on the left side of the drive.
    private final CANSparkMax leftPrimaryMotor = new CANSparkMax(DriveConstants.LEFT_MOTOR_PORT, MotorType.kBrushless);
    private final CANSparkMax leftFollowerMotor = new CANSparkMax(DriveConstants.LEFT_MOTOR_PORT+1, MotorType.kBrushless);

    // The motors on the right side of the drive.
    private final CANSparkMax rightPrimaryMotor = new CANSparkMax(DriveConstants.RIGHT_MOTOR_PORT, MotorType.kBrushless);
    private final CANSparkMax rightFollowerMotor = new CANSparkMax(DriveConstants.RIGHT_MOTOR_PORT+1, MotorType.kBrushless);

    // The encoders for left + right motor
    private final RelativeEncoder rightEncoder = rightPrimaryMotor.getEncoder();
    private final RelativeEncoder leftEncoder = leftPrimaryMotor.getEncoder();

    public final float COUNTS_TO_INCHES = 1.333f;

    /** Creates a new DriveSubsystem. */
    public DriveSubsystem() {
        // We need to invert one side of the drivetrain so that positive voltages
        // result in both sides moving forward. Depending on how your robot's
        // gearbox is constructed, you might have to invert the left side instead.
        leftPrimaryMotor.setInverted(DriveConstants.LEFT_MOTOR_REVERSED);
        leftPrimaryMotor.setIdleMode(IdleMode.kBrake);
        leftFollowerMotor.setInverted(DriveConstants.LEFT_MOTOR_REVERSED);
        leftFollowerMotor.setIdleMode(IdleMode.kBrake);
        rightPrimaryMotor .setInverted(DriveConstants.RIGHT_MOTOR_REVERSED);
        rightPrimaryMotor.setIdleMode(IdleMode.kBrake);
        rightFollowerMotor.setInverted(DriveConstants.RIGHT_MOTOR_REVERSED);
        rightFollowerMotor.setIdleMode(IdleMode.kBrake);

        // Setting both encoders to 0 for my sanity
        resetEncoders();
    }

    /**
     * Gets the average distance of the two encoders.
     *
     * @return the average of the two encoder readings
     */
    public double getAverageEncoderDistance() {
        return (getLeftEncoder() + getRightEncoder()) / 2;
    }

    /**
     * Gets the left drive encoder.
     *
     * @return the left drive encoder
     */
    public double getLeftEncoder() {
        return leftEncoder.getPosition();
    }

    /**
     * Gets the right drive encoder.
     *
     * @return the right drive encoder
     */
    public double getRightEncoder() {
        return rightEncoder.getPosition();
    }

    /**  Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
        rightEncoder.setPosition(0);
        leftEncoder.setPosition(0);
    }

    /**
     * Set the left and right speed of the primary and follower motors
     * @param leftSpeed
     * @param rightSpeed
     */
    public void setMotorSpeeds(double leftSpeed, double rightSpeed) {
        if (Math.abs(leftSpeed) < 0.05) {
			leftSpeed = 0;
		}

		if (Math.abs(rightSpeed) < 0.05) {
			rightSpeed = 0;
		}

        leftPrimaryMotor.set(leftSpeed);
        leftFollowerMotor.set(leftSpeed);

        rightPrimaryMotor.set(rightSpeed);
        rightFollowerMotor.set(rightSpeed);
    }

    public void setLeftMotorSpeed(double speed) {
        leftPrimaryMotor.set(speed);
        leftFollowerMotor.set(speed);
    }

    public void setRightMotorSpeed(double speed) {
        rightPrimaryMotor.set(speed);
        rightFollowerMotor.set(speed);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Right Motor", rightPrimaryMotor.get());
        SmartDashboard.putNumber("Left  Motor", leftPrimaryMotor.get());

        SmartDashboard.putNumber("Right Encoder", getRightEncoder());
        SmartDashboard.putNumber("Left Encoder", getLeftEncoder());

        SmartDashboard.putNumber("Distance", getAverageEncoderDistance() * COUNTS_TO_INCHES);
    }
}
