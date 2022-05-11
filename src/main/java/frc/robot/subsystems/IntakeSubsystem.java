package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {

    // The motors on the top part of the intake thing.
    private final VictorSPX intakeTopMotor =
            new VictorSPX(IntakeConstants.INTAKE_TOP_MOTOR_ADDRESS);

    //The motors on the bottom part of the intake thing.
    private final VictorSPX intakeBottomMotor =
            new VictorSPX(IntakeConstants.INTAKE_BOTTOM_MOTOR_ADDRESS);


    /** Creates a new IntakeSubsystem. */
    public IntakeSubsystem() {
        // We need to invert one side of the drivetrain so that positive voltages
        // result in both sides moving forward. Depending on how your robot's
        // gearbox is constructed, you might have to invert the left side instead.
        //for carousel, this is unknown at the moment
        intakeTopMotor.setInverted(IntakeConstants.INTAKE_TOP_MOTOR_REVERSED);//do we need a carousel motor reversed constant?
        intakeBottomMotor.setInverted(IntakeConstants.INTAKE_BOTTOM_MOTOR_REVERSED);

    }

    /**
     * Gets the average distance of the two encoders.
     *
     * @return the average of the two encoder readings
     */
    public double getAverageEncoderDistance() {
        return 0;
    }

    /**
     * Gets the left drive encoder.
     *
     * @return the left drive encoder
     */
    public double getLeftEncoder() {
        return 0;
    }

    /**
     * Gets the right drive encoder.
     *
     * @return the right drive encoder
     */
    public double getRightEncoder() {
        return 0;
    }

    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
    }

    public void setMotorSpeed(double topSpeed, double bottomSpeed) {
        intakeTopMotor.set(VictorSPXControlMode.PercentOutput, topSpeed);
        intakeBottomMotor.set(VictorSPXControlMode.PercentOutput, bottomSpeed);
    }

    @Override
    public void periodic() {

        //        SmartDashboard.putNumber("Right Motor", rightMotors.get());
        //        SmartDashboard.putNumber("Left  Motor", leftMotors.get());
    }
}
