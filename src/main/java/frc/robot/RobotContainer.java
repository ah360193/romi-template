// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.DriveForward;
import frc.robot.commands.TurnTime;
import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final RomiDrivetrain m_romiDrivetrain = new RomiDrivetrain();

  private final DriveForward driveForward = new DriveForward(.5,5000,m_romiDrivetrain);

  private final DriveDistance driveDistance = new DriveDistance(.5, m_romiDrivetrain, 20);

  private final Joystick controller = new Joystick(0);

  private final SendableChooser<Command> chooser = new SendableChooser<>();

  private final TurnTime turnTime = new TurnTime (0.5, 3000, m_romiDrivetrain);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    chooser.setDefaultOption("Drive Distance", driveDistance);
    chooser.addOption("Drive Forward", driveForward);
    chooser.addOption("Turn robot for select amount of Time", turnTime);
    SmartDashboard.putData(chooser);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  m_romiDrivetrain.setDefaultCommand(new ArcadeDrive(m_romiDrivetrain,
                                    () -> controller.getRawAxis(1),
                                    () -> controller.getRawAxis(4)));

  
  } 

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return chooser.getSelected();
  }
}
