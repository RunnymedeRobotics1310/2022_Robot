package frc.robot.commands.carousel;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CarouselSubsystem;

public class DefaultCarouselCommand extends CommandBase {

	private final CarouselSubsystem carouselSubsystem;
	private final Joystick driverController;
	private boolean carouselOn = false;
	private boolean previousState = false;

	/**
	 * Creates a new ExampleCommand.
	 *
	 * @param driveSubsystem The subsystem used by this command.
	 */
	public DefaultCarouselCommand(Joystick driverController, CarouselSubsystem carouselSubsystem) {

		this.driverController = driverController;
		this.carouselSubsystem = carouselSubsystem;

		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(carouselSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		if(driverController.getRawButton(2) && !previousState){
			previousState = true;
			carouselOn = !carouselOn;
		}
		else if(!driverController.getRawButton(2)){
			previousState = false;
		}

		if(carouselOn) {
			carouselSubsystem.setMotorSpeed(0.3);
		}
		else{
			carouselSubsystem.setMotorSpeed(0);
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
