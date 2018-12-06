package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Launcher;

public class Inhale extends Command {
    /**
     * Add your docs here.
     */
    public Inhale() {
        super();
        requires(Launcher.getInstance());
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        Launcher.getInstance().setSpeed(-1);
    }

    @Override
    protected void end() {
        Launcher.getInstance().setSpeed(0);
    }

}