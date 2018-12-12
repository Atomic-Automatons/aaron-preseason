package frc.frc6644.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.frc6644.robot.subsystems.DriveSystem;
import frc.frc6644.robot.subsystems.Gyro;

public class Turn extends Command {
	private double measuredAngle;
	private double targetAngle;
	private double error;
	private double maxSpeed = 0.5;
	
    public Turn(double angle) {
        requires(DriveSystem.getInstance());
		this.targetAngle = angle;
	}

    @Override
    protected boolean isFinished() {
        return Math.abs(error) < 0.1;
    }

    @Override
    protected void initialize() {
		Gyro.getInstance().reset();
    }

    @Override
    protected void execute() {
		measuredAngle = Gyro.getInstance().getDegrees();
		error = (targetAngle - measuredAngle) / measuredAngle;
		System.out.println(error % maxSpeed);
		DriveSystem.getInstance().arcadeDrive(0, error % maxSpeed);
    }

    @Override
    protected void end() {
		DriveSystem.getInstance().stop();
    }
}