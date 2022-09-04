package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;


public class DefaultClimbCommand extends CommandBase {

    private final ClimbSubsystem climbSubsystem;
    private final XboxController driverController;

	public DefaultClimbCommand(XboxController driverController, ClimbSubsystem climbSubsystem) {
        this.driverController = driverController;
        this.climbSubsystem = climbSubsystem;

        addRequirements(climbSubsystem);
    }

    @Override
    public void execute() {

        //Tells arms to go down
        if(driverController.getRawAxis(2) > 0.3){
            climbSubsystem.setClimbMotorSpeed(-(driverController.getRawAxis(2) - 0.3));
        } else if (driverController.getRawAxis(3) > 0.3) {
            climbSubsystem.setClimbMotorSpeed(0.6);
        } else {
            climbSubsystem.setClimbMotorSpeed(0);
        } 

    }
}
