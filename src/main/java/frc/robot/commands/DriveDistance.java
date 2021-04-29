// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RomiDrivetrain;

/** Add your docs here. */
public class DriveDistance extends CommandBase {
    private double speed;
    private RomiDrivetrain drivetrain;
    private double distanceInch;

    public DriveDistance(double speed, RomiDrivetrain drivetrain, 
                        double distanceInch){

        this.speed = speed;
        this.drivetrain = drivetrain;
        this.distanceInch = distanceInch;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize(){
        this.drivetrain.arcadeDrive(0,0);
        drivetrain.resetEncoders();
    }

    @Override
    public void execute (){
        this.drivetrain.arcadeDrive(this.speed,0);
    }

    @Override
    public boolean isFinished() {
        return(this.drivetrain.getLeftDistanceInch() +
                this.drivetrain.getRightDistanceInch())/2 >= this.distanceInch;
    }
    @Override
    public void end(boolean interrupted) {
        this.drivetrain.arcadeDrive(0,0);

}
}
