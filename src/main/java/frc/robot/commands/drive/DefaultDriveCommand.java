package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DefaultDriveCommand extends CommandBase {

	private final DriveSubsystem driveSubsystem;
	private final Joystick driverController;

	/**
	 * Creates a new ExampleCommand.
	 *
	 * @param driveSubsystem The subsystem used by this command.
	 */
	public DefaultDriveCommand(Joystick driverController, DriveSubsystem driveSubsystem) {

		this.driverController = driverController;
		this.driveSubsystem = driveSubsystem;

		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(driveSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {

		// What else to put here ladies and gentlemen?

		double leftY = -driverController.getRawAxis(1);
		double rightY = -driverController.getRawAxis(5);
		double leftT = driverController.getRawAxis(2);
		double rightT = driverController.getRawAxis(3);
		boolean boost = false;

		if (driverController.getRawButton(5) || driverController.getRawButton(6)){
			boost = true;
		}
		
		if (leftT >0) {
			driveSubsystem.setMotorSpeeds(-leftT, leftT);
		}
		else if (rightT >0) {
			driveSubsystem.setMotorSpeeds(rightT, -rightT);
		}

		else if (!boost) {
			//Not sure if this is a good speed!
			driveSubsystem.setMotorSpeeds(leftY/2, rightY/2);
		} else {
			driveSubsystem.setMotorSpeeds(leftY, rightY);
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
