package frc.frc6644.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

import frc.frc6644.robot.subsystems.DriveSystem;

public class ShiftDown extends InstantCommand {
  public ShiftDown() {
    super();
  }
  
  @Override
  protected void initialize() {
    DriveSystem.getInstance().shiftDown();
  }
}
