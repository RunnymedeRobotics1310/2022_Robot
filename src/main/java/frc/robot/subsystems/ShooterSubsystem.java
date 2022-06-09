package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {  
    //Motors on both sides of shooter
    private final CANSparkMax leftShooterMotor = new CANSparkMax(ShooterConstants.SHOOTER_LEFT_MOTOR_ADDRESS, MotorType.kBrushless);
    private final CANSparkMax rightShooterMotor = new CANSparkMax(ShooterConstants.SHOOTER_RIGHT_MOTOR_ADDRESS, MotorType.kBrushless);

    private final VictorSPX kickerMotor = new VictorSPX(ShooterConstants.KICKER_MOTOR_ADDRESS);

    private final DigitalInput ballSensor = new DigitalInput(ShooterConstants.BALL_SENSOR_ADDRESS);

    private double setpoint = 0;
    
    private double shooterMotorSetpoint = 0;
    private double kickerMotorSetpoint = 0;
 
    public ShooterSubsystem(){
        //Flips the motors 
        leftShooterMotor .setInverted(ShooterConstants.SHOOTER_LEFT_MOTOR_REVERSED);
        rightShooterMotor .setInverted(ShooterConstants.SHOOTER_RIGHT_MOTOR_REVERSED);
        
        kickerMotor  .setInverted(ShooterConstants.KICKER_MOTOR_REVERSED);
        kickerMotor  .setNeutralMode(NeutralMode.Brake);

    
    }

    public void setShooterMotorSpeed(double speed) {

    	shooterMotorSetpoint = speed;
    	leftShooterMotor.set(shooterMotorSetpoint);
    	rightShooterMotor.set(shooterMotorSetpoint);
    }

    public boolean getBallSensor(){
        return !ballSensor.get();
    }

    public double getShoterEncoderVelocity() {
    	return leftShooterMotor.getEncoder().getVelocity();
    }
    
    public double getShooterSetpoint() {
    	return shooterMotorSetpoint;
    }
    
    public double getShooterEncoderVelocity() {
    	return leftShooterMotor.getEncoder().getVelocity();
    }
    
    public void setKickerMotorSpeed(double speed) {
        kickerMotorSetpoint = speed;
        kickerMotor.set(ControlMode.PercentOutput, speed);
    }

    public void stop() {
        setShooterMotorSpeed(0);
        setKickerMotorSpeed(0);
    }

    @Override
    public void periodic() {

    	SmartDashboard.putNumber("Shooter Motor Setpoint", shooterMotorSetpoint);
        SmartDashboard.putBoolean("Ball Sensor", getBallSensor());
        SmartDashboard.putNumber("Shooter Encoder (rpm)", getShooterEncoderVelocity());
        SmartDashboard.putNumber("Kicker Motor Setpoint", kickerMotorSetpoint);

    }
}
