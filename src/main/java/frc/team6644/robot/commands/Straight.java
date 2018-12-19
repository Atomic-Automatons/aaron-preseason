package frc.frc6644.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.frc6644.robot.subsystems.DriveSystem;
import frc.frc6644.robot.subsystems.Gyro;
import frc.frc6644.robot.subsystems.Encoders;

public class Straight extends Command {
	private double target;
	private double measured;
	private double time;
	public Straight(double target) {
		this.target = target;
	}

    @Override
    protected boolean isFinished() {
        return target - measured < 0.1;
    }

    @Override
    protected void initialize() {
		Encoders.getInstance().reset();
		Gyro.getInstance().reset();
		DriveSystem.getInstance().startAuto();
    }

    @Override
    protected void execute() {
		measured = Encoders.getInstance().getDistance();
		
		time++;
		time %= 100;
		if(time == 1){
			System.out.println("Target: " + target);
			System.out.println("Measured: " + measured);
			System.out.println("Left: " + Encoders.getInstance().getLeftDistance());
			System.out.println("Right: " + Encoders.getInstance().getRightDistance());
		}
		
		DriveSystem.getInstance().arcadeDrive(0.4, -1 * 0.2 * Gyro.getInstance().getDegrees());
    }

    @Override
    protected void end() {
		DriveSystem.getInstance().stop();
		System.out.println("Target: " + target);
		System.out.println("Measured: " + measured);
		System.out.println("Left: " + Encoders.getInstance().getLeftDistance());
		System.out.println("Right: " + Encoders.getInstance().getRightDistance());
	}
}