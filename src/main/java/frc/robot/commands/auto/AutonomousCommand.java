package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.CarouselSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;


public class AutonomousCommand extends SequentialCommandGroup {

    public AutonomousCommand(
            DriveSubsystem    driveSubsystem,
            IntakeSubsystem   intakeSubsystem,
            CarouselSubsystem carouselSubsystem,
            ShooterSubsystem  shooterSubsystem,
            SendableChooser<String> autoChooser) {

        String selectedAuto = autoChooser.getSelected();

        // The selector should always return one of the auto
        // patterns, if not, then do nothing
        if (selectedAuto == null) {
            System.out.println("*** ERROR - NULL auto selected ***");
            addCommands (new InstantCommand());
            return;
        }

        // Placeholder for auto commands
        switch (autoChooser.getSelected()) {

        case AutoConstants.AUTO_PATTERN_DO_NOTHING:
            // Do nothing
            addCommands (
                    new InstantCommand()
                    );
            break;

        default:
            // How did we get here?
            System.out.println("Auto selection(" + selectedAuto + ") was not programmed!");
            addCommands (new InstantCommand());
        }
    }

}
