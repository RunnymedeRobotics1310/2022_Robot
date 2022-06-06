package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {  
    //Motors on both sides of shooter
    private final CANSparkMax shooterMotor   = new CANSparkMax(ShooterConstants.SHOOTER_MOTOR_ADDRESS, MotorType.kBrushless);
    private final CANSparkMax kickerMotor    = new CANSparkMax(ShooterConstants.KICKER_MOTOR_ADDRESS, MotorType.kBrushless);


    public ShooterSubsystem(){
        //Initialize rotation direction
        shooterMotor .setInverted(ShooterConstants.SHOOTER_MOTOR_REVERSED);
        shooterMotor .setIdleMode(IdleMode.kBrake);
        kickerMotor  .setInverted(ShooterConstants.KICKER_MOTOR_REVERSED);
        kickerMotor  .setIdleMode(IdleMode.kBrake);


    }



    public double getShooterEncoder() {
        return shooterMotor.getEncoder().getPosition();
    }

    public double getKickerEncoder() {
        return kickerMotor.getEncoder().getPosition();
    }


    public void resetEncoders() {
        shooterMotor.getEncoder().getPosition();
        kickerMotor.getEncoder().getPosition();
    }

    public void setShooterMotorSpeed(double speed) {

      shooterMotor.set(speed);

    }

    public void setKickerMotorSpeed(double speed) {

        kickerMotor.set(speed);
  
      }

    @Override
    public void periodic() {

        SmartDashboard.putNumber("Shooter Motor", shooterMotor.get());
        SmartDashboard.putNumber("Kicker Motor", kickerMotor.get());

    }
}
