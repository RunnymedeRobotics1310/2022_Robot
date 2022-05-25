package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CarouselConstants;

public class CarouselSubsystem extends SubsystemBase {

    // The motors on the left side of the drive.
    private final CANSparkMax carouselMotor = new CANSparkMax(CarouselConstants.CAROUSEL_MOTOR_ADDRESS, MotorType.kBrushless);
    private final VictorSPX carouselKickerMotor = new VictorSPX(45);
    /** Creates a new CarouselSubsystem. */
    public CarouselSubsystem() {
        // We need to invert one side of the drivetrain so that positive voltages
        // result in both sides moving forward. Depending on how your robot's
        // gearbox is constructed, you might have to invert the left side instead.
        //for carousel, this is unknown at the moment
        carouselMotor.setInverted(CarouselConstants.CAROUSEL_MOTOR_REVERSED);//do we need a carousel motor reversed constant?
        carouselKickerMotor.setInverted(CarouselConstants.CAROUSEL_KICKER_MOTOR_REVERSED);
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

    public void setMotorSpeed(double speed){
        carouselMotor.set(speed);
        carouselKickerMotor.set(VictorSPXControlMode.PercentOutput, speed);
    }

    @Override
    public void periodic() {

        //        SmartDashboard.putNumber("Right Motor", rightMotors.get());
        //        SmartDashboard.putNumber("Left  Motor", leftMotors.get());
    }
}
