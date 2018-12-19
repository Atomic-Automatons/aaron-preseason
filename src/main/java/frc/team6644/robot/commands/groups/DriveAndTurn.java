package frc.frc6644.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.frc6644.robot.subsystems.DriveSystem;
import frc.frc6644.robot.subsystems.Gyro;
import frc.frc6644.robot.commands.*;

public class DriveAndTurn extends CommandGroup {
    public DriveAndTurn() {
		addSequential(new Straight(24));
		addSequential(new Turn(90));
	}
}