package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class StartIntakeCommand extends CommandBase{

    private final IntakeSubsystem intakeSubsystem;
    private final ShooterSubsystem shooterSubsystem;

    private final float intakeStartSpeed = 0.0f; //was 0.75

    private long shotStartTime = 0;
    private final float intakeOffsetSpeed = 0.20f;
    private final int intakeSpeedTimer = 700; // Multiple of 100
    private boolean isOffsetAdded = false;

    public StartIntakeCommand(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        this.intakeSubsystem = intakeSubsystem;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(shooterSubsystem, intakeSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // Start the shooter motor and capture the start time
        // shotStartTime = System.currentTimeMillis(); 
        shooterSubsystem.setKickerMotorSpeed(0.3);
        intakeSubsystem.setHoodPiston(true);
        intakeSubsystem.setMotorSpeed(intakeStartSpeed);
        intakeSubsystem.setRollerPiston(true);   
    }

    // Called every time the scheduler runs while the command is scheduled.
    // shooter will start and then kicker should start 0.5 seconds after
    // button #2 = B on controller
    @Override
    public void execute() {
        if (shooterSubsystem.getBallSensor()){
            shooterSubsystem.setKickerMotorSpeed(0);
        }

        intakeSubsystem.setMotorSpeed(intakeStartSpeed);

        if (isOffsetAdded && 100 * ((System.currentTimeMillis() - shotStartTime) / 100) % intakeSpeedTimer == 0) {
            isOffsetAdded = false;
            intakeSubsystem.setMotorSpeed(intakeStartSpeed - intakeOffsetSpeed);
        }
        else if (!isOffsetAdded && 100 * ((System.currentTimeMillis() - shotStartTime) / 100) % intakeSpeedTimer == 0) {
            isOffsetAdded = true;
            intakeSubsystem.setMotorSpeed(intakeStartSpeed + intakeOffsetSpeed);
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
