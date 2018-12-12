package frc.frc6644.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.frc6644.robot.subsystems.Encoders;

public class UpdateSmartDashboard extends Command {
  public UpdateSmartDashboard() {}

  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    SmartDashboard.putNumber("Encoders.left", Encoders.getInstance().getLeftDistance());
    SmartDashboard.putNumber("Encoders.right", Encoders.getInstance().getRightDistance());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {}

  @Override
  protected void interrupted() {}
}
