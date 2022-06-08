package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class MoveBackCommand extends CommandBase{

    private final DriveSubsystem driveSubsystem;
    private long shotStartTime = 0;

    public MoveBackCommand(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(driveSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

        // Start the shooter motor and capture the start time
        shotStartTime = System.currentTimeMillis();
        driveSubsystem.setMotorSpeeds(-0.5, -0.5);
    }

    // Called every time the scheduler runs while the command is scheduled.
    // shooter will start and then kicker should start 0.5 seconds after
    // button #2 = B on controller
    @Override
    public void execute() {

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        driveSubsystem.setMotorSpeeds(0, 0);

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {

        // Stop moving after 2 seconds
        if (System.currentTimeMillis() - shotStartTime > 2000) {
            return true;
        }

        return false;
    }

}
