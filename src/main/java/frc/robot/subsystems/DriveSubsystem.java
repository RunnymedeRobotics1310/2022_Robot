package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

    // The motors on the left side of the drive.
    private final TalonSRX leftPrimaryMotor =
            new TalonSRX(DriveConstants.LEFT_MOTOR_PORT);
    private final TalonSRX leftFollowerMotor =
            new TalonSRX(DriveConstants.LEFT_MOTOR_PORT+1);

    // The motors on the right side of the drive.
    private final TalonSRX rightPrimaryMotor =
            new TalonSRX(DriveConstants.RIGHT_MOTOR_PORT);
    private final TalonSRX rightFollowerMotor =
            new TalonSRX(DriveConstants.RIGHT_MOTOR_PORT);

    /** Creates a new DriveSubsystem. */
    public DriveSubsystem() {
        // We need to invert one side of the drivetrain so that positive voltages
        // result in both sides moving forward. Depending on how your robot's
        // gearbox is constructed, you might have to invert the left side instead.
        leftPrimaryMotor .setInverted(DriveConstants.LEFT_MOTOR_REVERSED);
        leftFollowerMotor.setInverted(DriveConstants.LEFT_MOTOR_REVERSED);
        
        rightPrimaryMotor .setInverted(DriveConstants.RIGHT_MOTOR_REVERSED);
        rightFollowerMotor.setInverted(DriveConstants.RIGHT_MOTOR_REVERSED);

        leftPrimaryMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0,  0);
        leftPrimaryMotor.setSelectedSensorPosition(0, 0, 0);

        rightPrimaryMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0,  0);
        rightPrimaryMotor.setSelectedSensorPosition(0, 0, 0);

    }

    /**
     * Gets the average distance of the two encoders.
     *
     * @return the average of the two encoder readings
     */
    public double getAverageEncoderDistance() {
        return (leftPrimaryMotor.getSelectedSensorPosition() + rightPrimaryMotor.getSelectedSensorPosition()) / 2;
    }

    /**
     * Gets the left drive encoder.
     *
     * @return the left drive encoder
     */
    public double getLeftEncoder() {
        return leftPrimaryMotor.getSelectedSensorPosition();
    }

    /**
     * Gets the right drive encoder.
     *
     * @return the right drive encoder
     */
    public double getRightEncoder() {
        return rightPrimaryMotor.getSelectedSensorPosition();
    }

    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
        leftPrimaryMotor.setSelectedSensorPosition(0);
        rightPrimaryMotor.setSelectedSensorPosition(0);
    }

    public void setMotorSpeeds(double leftSpeed, double rightSpeed) {
    	
        leftPrimaryMotor.set(ControlMode.PercentOutput, leftSpeed);
        leftFollowerMotor.set(ControlMode.PercentOutput, leftSpeed);
        
        rightPrimaryMotor.set(ControlMode.PercentOutput, rightSpeed);
        rightFollowerMotor.set(ControlMode.PercentOutput, rightSpeed);
    }

    @Override
    public void periodic() {

        SmartDashboard.putNumber("Right Motor", rightPrimaryMotor.getMotorOutputPercent());
        SmartDashboard.putNumber("Left  Motor", leftPrimaryMotor.getMotorOutputPercent());
    }
}
