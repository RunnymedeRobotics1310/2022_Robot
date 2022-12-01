package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class TestShootCommand extends CommandBase{

    private final ShooterSubsystem shooterSubsystem;
    private final IntakeSubsystem intakeSubsystem;
    private final XboxController driverController;
    private long shotStartTime = 0;

    private final float SHOOTER_SPEED = 0.75f;
    private final float KICKER_SPEED = 0.75f;

    private final int EXCECUTE_TIME_SECONDS = 3;
    private final int FINISH_TIME_SECONDS = 4;

    private final int MILLISECONDS_TO_SECONDS = 1000;

    private double shooterMotorSpeed = 0;

    public TestShootCommand(XboxController driverController, ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        this.driverController = driverController;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(shooterSubsystem, intakeSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

        // Start the shooter motor and capture the start time
        shooterSubsystem.setShooterMotorSpeed(0);
        shooterSubsystem.setKickerMotorSpeed(0);
        shotStartTime = System.currentTimeMillis();
        intakeSubsystem.setRollerPiston(true);
        shooterMotorSpeed = 0;
    }

    // Called every time the scheduler runs while the command is scheduled
    @Override
    public void execute() {

        if (driverController.getPOV() == 270) {
            shooterSubsystem.setKickerMotorSpeed(0);
            shooterSubsystem.setShooterMotorSpeed(0);
        }

        if (driverController.getPOV() == 0 && shooterMotorSpeed < 1) {
            shooterMotorSpeed += 0.01;
        }

        if (driverController.getPOV() == 180 && shooterMotorSpeed > 0) {
            shooterMotorSpeed -= 0.01;
        }

        if (driverController.getStartButton()){
            shotStartTime = System.currentTimeMillis();
            shooterSubsystem.setShooterMotorSpeed(shooterMotorSpeed);
        }
        
        if (driverController.getPOV() == 90) {
            shooterSubsystem.setKickerMotorSpeed(0.3);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.stop();
        intakeSubsystem.setRollerPiston(false);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {

        //return (System.currentTimeMillis() - shotStartTime > finishTime) ? true : false;

        // Stop the shooter after a set time
        if (System.currentTimeMillis() - shotStartTime > (FINISH_TIME_SECONDS * MILLISECONDS_TO_SECONDS)) { 
            return true;
        }

        return false;
    }

}
