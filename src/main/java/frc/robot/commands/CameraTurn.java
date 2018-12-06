package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.subsystems.DriveSystem;

public class CameraTurn extends Command {
    double speed = .32;

    public CameraTurn() {
        super();
        requires(DriveSystem.getInstance());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if (SmartDashboard.getString("Yellow Ball", "getString(YellowBall)").equals("left")) {
            DriveSystem.getInstance().arcadeDrive(-speed, speed);
        } else if (SmartDashboard.getString("Yellow Ball", "getString(YellowBall)").equals("right")) {
            DriveSystem.getInstance().arcadeDrive(speed, -speed);
        } else {
            DriveSystem.getInstance().arcadeDrive(0, 0);
        }
    }

    @Override
    protected void end() {
    }
}