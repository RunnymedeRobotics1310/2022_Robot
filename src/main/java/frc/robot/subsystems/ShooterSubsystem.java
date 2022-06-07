package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {  
    //Motors on both sides of shooter
    private final CANSparkMax leftShooterMotor = new CANSparkMax(ShooterConstants.SHOOTER_LEFT_MOTOR_ADDRESS, MotorType.kBrushless);
    private final CANSparkMax rightShooterMotor = new CANSparkMax(ShooterConstants.SHOOTER_RIGHT_MOTOR_ADDRESS, MotorType.kBrushless);

    private double setpoint = 0;
    
    public ShooterSubsystem(){
        //Flips the motors 
        leftShooterMotor .setInverted(ShooterConstants.SHOOTER_LEFT_MOTOR_REVERSED);
        rightShooterMotor .setInverted(ShooterConstants.SHOOTER_RIGHT_MOTOR_REVERSED);
    
    }

    public void setMotorSpeeds(double speed) {
    	this.setpoint = speed;
    	leftShooterMotor.set(speed);
    	rightShooterMotor.set(speed);
    }

    public double getShooterSetpoint() {
    	return setpoint;
    }
    
    public double getShooterSpeed() {
    	return leftShooterMotor.getEncoder().getVelocity();
    }
    
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Motor Setpoint", getShooterSetpoint());
        SmartDashboard.putNumber("Motor Speed", getShooterSpeed());
    }
}
