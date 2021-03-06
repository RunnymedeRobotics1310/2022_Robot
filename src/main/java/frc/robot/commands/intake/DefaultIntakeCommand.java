package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class DefaultIntakeCommand extends CommandBase {

	private final IntakeSubsystem intakeSubsystem;
	private final Joystick driverController;

	/**
	 * Creates a new ExampleCommand.
	 *
	 * @param driveSubsystem The subsystem used by this command.
	 */
	public DefaultIntakeCommand(Joystick driverController, IntakeSubsystem intakeSubsystem) {

		this.driverController = driverController;
		this.intakeSubsystem = intakeSubsystem;

		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(intakeSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		//TODO
		if (driverController.getRawButton(1) == true) {
			intakeSubsystem.setMotorSpeed(0.5, 0.5);
		}
		else {
			intakeSubsystem.setMotorSpeed(0, 0);
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
