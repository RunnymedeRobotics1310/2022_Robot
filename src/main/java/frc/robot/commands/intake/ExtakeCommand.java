package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ExtakeCommand extends CommandBase{

    private final IntakeSubsystem intakeSubsystem;
    private final ShooterSubsystem shooterSubsystem;

    private long extakeStartTime;

    public ExtakeCommand(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        this.intakeSubsystem = intakeSubsystem;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(shooterSubsystem, intakeSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

        intakeSubsystem.setHoodPiston(true);
        shooterSubsystem.setKickerMotorSpeed(-0.5);
        
    }

    // Called every time the scheduler runs while the command is scheduled.
    // shooter will start and then kicker should start 0.5 seconds after
    // button #2 = B on controller
    @Override
    public void execute() {
        if (!shooterSubsystem.getBallSensor()){
            extakeStartTime = System.currentTimeMillis();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

}
