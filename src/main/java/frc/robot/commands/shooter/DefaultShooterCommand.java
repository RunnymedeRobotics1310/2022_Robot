package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class DefaultShooterCommand extends CommandBase{
    
    private final ShooterSubsystem shooterSubsystem;
    private final Joystick driverController;

    public DefaultShooterCommand(Joystick driverController, ShooterSubsystem shooterSubsystem) {
        this.driverController = driverController;
		this.shooterSubsystem = shooterSubsystem;

		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(shooterSubsystem);
    }   

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
        double shoot =  driverController.getRawAxis(3);
        double shootSpeed = shoot;

        shooterSubsystem.setMotorSpeed(shootSpeed);
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
