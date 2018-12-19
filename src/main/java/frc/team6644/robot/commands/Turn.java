package frc.frc6644.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.frc6644.robot.subsystems.DriveSystem;
import frc.frc6644.robot.subsystems.Gyro;

public class Turn extends Command {
	private double measuredAngle;
	private double targetAngle;
	private static int time = 0;
	
    public Turn(double angle) {
		this.targetAngle = angle;
	}

    @Override
    protected boolean isFinished() {
        return Math.abs(measuredAngle - targetAngle) < 1;
    }

    @Override
    protected void initialize() {
		Gyro.getInstance().reset();
		DriveSystem.getInstance().startAuto();
    }

    @Override
    protected void execute() {
		measuredAngle = Gyro.getInstance().getDegrees();
		DriveSystem.getInstance().arcadeDrive(0, 0.55 * Math.signum(targetAngle - measuredAngle));
    }

    @Override
    protected void end() {
		DriveSystem.getInstance().stop();
	}
}