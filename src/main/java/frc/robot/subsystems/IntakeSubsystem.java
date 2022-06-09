package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {

    // The motors on the top part of the intake thing.
    private final VictorSPX intakeTopMotor =
            new VictorSPX(IntakeConstants.INTAKE_TOP_MOTOR_ADDRESS);

    //The motors on the bottom part of the intake thing.
    private final VictorSPX intakeBottomMotor =
            new VictorSPX(IntakeConstants.INTAKE_BOTTOM_MOTOR_ADDRESS);

    private double rollerMotorSetpoint = 0;

    // The motors on the carousel.
    private final CANSparkMax carouselMotor = new CANSparkMax(IntakeConstants.CAROUSEL_MOTOR_ADDRESS, MotorType.kBrushless);

    private double carouselMotorSetpoint = 0;
    
    /** Creates a new IntakeSubsystem. */
    public IntakeSubsystem() {

    	intakeTopMotor.setInverted(IntakeConstants.INTAKE_TOP_MOTOR_REVERSED);//do we need a carousel motor reversed constant?
        intakeBottomMotor.setInverted(IntakeConstants.INTAKE_BOTTOM_MOTOR_REVERSED);
        
        carouselMotor.setInverted(IntakeConstants.CAROUSEL_MOTOR_REVERSED);//do we need a carousel motor reversed constant?
    }

    public void setRollerMotorSpeed(double speed) {
    	rollerMotorSetpoint = speed;
        intakeTopMotor.set(VictorSPXControlMode.PercentOutput, speed);
        intakeBottomMotor.set(VictorSPXControlMode.PercentOutput, speed);
    }

    public void setCarouselMotorSpeed(double speed) {
    	carouselMotorSetpoint = speed;
        carouselMotor.set(speed);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Intake Roller Motor",  rollerMotorSetpoint);
        SmartDashboard.putNumber("Intake Carousel Motor",  carouselMotorSetpoint);
    }
}
