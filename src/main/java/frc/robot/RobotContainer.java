// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.OiConstants;
import frc.robot.commands.carousel.DefaultCarouselCommand;
import frc.robot.commands.drive.DefaultDriveCommand;
import frc.robot.commands.intake.DefaultIntakeCommand;
import frc.robot.commands.shooter.DefaultShooterCommand;
import frc.robot.subsystems.CarouselSubsystem;
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
    private final DriveSubsystem driveSubsystem = new DriveSubsystem();
    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    private final CarouselSubsystem carouselSubsystem = new CarouselSubsystem();
    private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
    // Instant Command is a placeholder command that does nothing
    private final Command autonomousCommand = new InstantCommand();

    // The driver's controller
    private final Joystick driverController = new Joystick(OiConstants.DRIVER_CONTROLLER_PORT);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {

        // Initialize all Subsystem default commands.
        driveSubsystem.setDefaultCommand(new DefaultDriveCommand(driverController, driveSubsystem));
        intakeSubsystem.setDefaultCommand(new DefaultIntakeCommand(driverController, intakeSubsystem));
        carouselSubsystem.setDefaultCommand(new DefaultCarouselCommand(driverController, carouselSubsystem));
        shooterSubsystem.setDefaultCommand(new DefaultShooterCommand(driverController, shooterSubsystem));
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
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // no auto
        return autonomousCommand;
    }
}
