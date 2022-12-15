// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.OiConstants;
import frc.robot.commands.auto.AutonomousCommand;
import frc.robot.commands.climb.DefaultClimbCommand;
import frc.robot.commands.drive.DefaultDriveCommand;
import frc.robot.commands.intake.DefaultIntakeCommand;
import frc.robot.commands.intake.EndIntakeCommand;
import frc.robot.commands.intake.StartIntakeCommand;
import frc.robot.commands.intake.ExtakeCommand;
import frc.robot.commands.shooter.DefaultShooterCommand;
import frc.robot.commands.shooter.ShootCommand;
import frc.robot.commands.shooter.ShootLowCommand;
import frc.robot.commands.shooter.TestShootCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    // The robot's subsystems and commands are defined here...
    private final DriveSubsystem driveSubsystem       = new DriveSubsystem();
    private final IntakeSubsystem intakeSubsystem     = new IntakeSubsystem();
    private final ShooterSubsystem shooterSubsystem   = new ShooterSubsystem();
    private final ClimbSubsystem climbSubsystem       = new ClimbSubsystem();
    
    private static final String TARGET_DISTANCE_ID = "Target Distance (cm)";

    // A chooser for autonomous commands
    SendableChooser<String> autoChooser = new SendableChooser<>();

    // The driver's controller
    private final XboxController driverController = new XboxController(OiConstants.DRIVER_CONTROLLER_PORT);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {

        // Initialize all Subsystem default commands.
        driveSubsystem   .setDefaultCommand(new DefaultDriveCommand   (driverController, driveSubsystem));
        intakeSubsystem  .setDefaultCommand(new DefaultIntakeCommand  (driverController, intakeSubsystem));
        shooterSubsystem .setDefaultCommand(new DefaultShooterCommand (driverController, shooterSubsystem));
        climbSubsystem   .setDefaultCommand(new DefaultClimbCommand (driverController, climbSubsystem));


        // Initialize the autonomous chooser
        autoChooser.setDefaultOption(AutoConstants.AUTO_PATTERN_DO_NOTHING, AutoConstants.AUTO_PATTERN_DO_NOTHING);
        SmartDashboard.putData(autoChooser);
        autoChooser.addOption(AutoConstants.AUTO_PATTERN_SHOOT, AutoConstants.AUTO_PATTERN_SHOOT);
        autoChooser.addOption(AutoConstants.AUTO_PATTERN_MOVE, AutoConstants.AUTO_PATTERN_MOVE);
        autoChooser.addOption(AutoConstants.AUTO_PATTERN_SHOOT_AND_MOVE, AutoConstants.AUTO_PATTERN_SHOOT_AND_MOVE);


        // Initialize a Target Distance value
        // Retrieve the value before putting it on the SmartDashboard in order to preserve
        // any previous value that was entered.  Do not reset the target distance when
        // deploying new code.
        double targetDistance = SmartDashboard.getNumber(TARGET_DISTANCE_ID, 100);
        SmartDashboard.putNumber(TARGET_DISTANCE_ID, targetDistance);
        
        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {

        // Button map for Driver Stick
        Button shootButton = new JoystickButton(driverController, XboxController.Button.kY.value);
        Button shootLowButton = new JoystickButton(driverController, XboxController.Button.kX.value);
        Button intakeStartButton = new JoystickButton(driverController, XboxController.Button.kA.value);
        Button intakeStopButton = new JoystickButton(driverController, XboxController.Button.kB.value);
        Button extakeButton = new JoystickButton(driverController, XboxController.Button.kLeftBumper.value);

        // Button binding
        shootButton.whenPressed(new ShootCommand(shooterSubsystem, intakeSubsystem));
        shootLowButton.whenPressed(new ShootLowCommand(shooterSubsystem, intakeSubsystem));
        intakeStartButton.whenPressed(new StartIntakeCommand(shooterSubsystem, intakeSubsystem));
        intakeStopButton.whenPressed(new EndIntakeCommand(shooterSubsystem, intakeSubsystem));
        extakeButton.whenPressed(new ExtakeCommand(shooterSubsystem, intakeSubsystem));

        new Trigger(() -> driverController.getPOV() >= 0)
        .whenActive(new TestShootCommand(driverController, shooterSubsystem, intakeSubsystem));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // no auto
        return new AutonomousCommand(
                driveSubsystem,
                intakeSubsystem,
                shooterSubsystem,
                autoChooser);
    }
    
    public static double getTargetDistance() {
    	return SmartDashboard.getNumber(TARGET_DISTANCE_ID, 100);
    }
}
