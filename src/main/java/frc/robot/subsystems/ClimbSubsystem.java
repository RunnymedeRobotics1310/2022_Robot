package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;

public class ClimbSubsystem extends SubsystemBase {

    private static final boolean AT_LIMIT = false;

    //Sensors for arm limits
    private final DigitalInput leftTopLimit = new DigitalInput(ClimbConstants.LEFT_TOP_LIMIT_SENSOR_ADDRESS);
    private final DigitalInput leftBottomLimit = new DigitalInput(ClimbConstants.LEFT_BOTTOM_LIMIT_SENSOR_ADDRESS);
    private final DigitalInput rightTopLimit = new DigitalInput(ClimbConstants.RIGHT_TOP_LIMIT_SENSOR_ADDRESS);
    private final DigitalInput rightBottomLimit = new DigitalInput(ClimbConstants.RIGHT_BOTTOM_LIMIT_SENSOR_ADDRESS);

    //Motors on either side of bot
    private final TalonSRX leftMotor =  new TalonSRX(ClimbConstants.LEFT_CLIMB_MOTOR_ADDRESS);
    private final TalonSRX rightMotor =  new TalonSRX(ClimbConstants.RIGHT_CLIMB_MOTOR_ADDRESS);

    private double climbSpeed = 0;

    public ClimbSubsystem() {
        leftMotor.setInverted(ClimbConstants.LEFT_CLIMB_MOTOR_REVERSED);
        rightMotor.setInverted(ClimbConstants.RIGHT_CLIMB_MOTOR_REVERSED);
    }

    public void setClimbMotorSpeed(double speed) {

        climbSpeed = speed;

        // Safety code - do not allow the motors to go past the limit
        if (speed < 0 && leftBottomLimit.get() != AT_LIMIT) {
            leftMotor.set(ControlMode.PercentOutput,speed);
        }
        else if (speed > 0 && leftTopLimit.get() != AT_LIMIT) {
            leftMotor.set(ControlMode.PercentOutput,speed);
        }
        else {
            // Stop the motors
            leftMotor.set(ControlMode.PercentOutput, 0);
        }

        if (speed < 0 && rightBottomLimit.get() != AT_LIMIT) {
            rightMotor.set(ControlMode.PercentOutput,speed);
        }
        else if (speed > 0 && rightTopLimit.get() != AT_LIMIT) {
            rightMotor.set(ControlMode.PercentOutput,speed);
        }
        else {
            // Stop the motors
            rightMotor.set(ControlMode.PercentOutput, 0);
        }

  
    }

    @Override
    public void periodic() {

        SmartDashboard.putBoolean("Climb Top-L Limit", leftTopLimit.get());
        SmartDashboard.putBoolean("Climb Bot-L Limit", leftBottomLimit.get());
        SmartDashboard.putBoolean("Climb Top-R Limit", rightTopLimit.get());
        SmartDashboard.putBoolean("Climb Bot-R Limit", rightBottomLimit.get());
        SmartDashboard.putNumber("Climb Speed", climbSpeed);
    }
    
}
