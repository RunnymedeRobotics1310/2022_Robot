package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCommand extends CommandBase{

    private final ShooterSubsystem shooterSubsystem;
    private final IntakeSubsystem intakeSubsystem;
    private long shotStartTime = 0;

    public ShootCommand(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        this.intakeSubsystem = intakeSubsystem;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(shooterSubsystem, intakeSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

        // Start the shooter motor and capture the start time
        shooterSubsystem.setShooterMotorSpeed(0.3);
        shotStartTime = System.currentTimeMillis();
    }

    // Called every time the scheduler runs while the command is scheduled.
    // shooter will start and then kicker should start 0.5 seconds after
    // button #2 = B on controller
    @Override
    public void execute() {

        if (System.currentTimeMillis() - shotStartTime > 1000) {
            shooterSubsystem.setKickerMotorSpeed(0.5);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {

        // Stop the shooter after 2 seconds
        if (System.currentTimeMillis() - shotStartTime > 2000) {
            return true;
        }

        return false;
    }

}
