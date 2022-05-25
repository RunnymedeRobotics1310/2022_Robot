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

    public ShooterSubsystem(){
        //Flips the motors 
        leftShooterMotor .setInverted(ShooterConstants.SHOOTER_LEFT_MOTOR_REVERSED);
        rightShooterMotor .setInverted(ShooterConstants.SHOOTER_RIGHT_MOTOR_REVERSED);
    
    }

    public double getAverageEncoderDistance() {
        return (leftShooterMotor.getEncoder().getPosition() + rightShooterMotor.getEncoder().getPosition()) / 2;
    }

    public double getLeftEncoder() {
        return leftShooterMotor.getEncoder().getPosition();
    }

    public double getRightEncoder() {
        return rightShooterMotor.getEncoder().getPosition();
    }

    public void resetEncoders() {
        leftShooterMotor.getEncoder().getPosition();
        rightShooterMotor.getEncoder().getPosition();
    }

    public void setMotorSpeeds(double speed) {

      leftShooterMotor.set(speed);

       rightShooterMotor.set(speed);
    }

    @Override
    public void periodic() {

        SmartDashboard.putNumber("Right Motor", rightShooterMotor.get());
        SmartDashboard.putNumber("Left  Motor", leftShooterMotor.get());
    }
}
