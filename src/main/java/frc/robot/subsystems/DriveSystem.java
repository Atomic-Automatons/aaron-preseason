/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class DriveSystem extends Subsystem {

  static private DriveSystem instance = null;
  static public DriveSystem getInstance(){
    if(instance == null){
      instance = new DriveSystem();
    }
    return instance;
  }

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private DifferentialDrive drive;
  public DriveSystem(){
    PWMVictorSPX left = new PWMVictorSPX(RobotMap.leftMotor);
    PWMVictorSPX right = new PWMVictorSPX(RobotMap.rightMotor);
    drive = new DifferentialDrive(left, right);
  }

  public void arcadeDrive(double x, double y){
    drive.arcadeDrive(x, y);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
