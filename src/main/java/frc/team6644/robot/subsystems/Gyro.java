/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.frc6644.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import frc.frc6644.robot.RobotMap;

public class Gyro extends Subsystem {
  static Gyro instance = null;
  public static Gyro getInstance() {
    if(instance == null){
      instance = new Gyro();
    }
    return instance;
  }
  
  private static ADXRS450_Gyro gyro;

  private Gyro(){
    gyro = new ADXRS450_Gyro();
  }

  public double getDegrees(){
    return gyro.getAngle() % 360;
  }

  public void reset(){
	  gyro.reset();
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
