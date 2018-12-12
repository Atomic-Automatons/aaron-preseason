package frc.frc6644.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.frc6644.robot.subsystems.DriveSystem;
import frc.frc6644.robot.subsystems.Launcher;

public class Exhale extends Command {
  public Exhale() {
    super();
    requires(Launcher.getInstance());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void initialize() {
    Launcher.getInstance().setSpeed(1);
  }

  @Override
  protected void end() {
    Launcher.getInstance().setSpeed(0);
  }
}