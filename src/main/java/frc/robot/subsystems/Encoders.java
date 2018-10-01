/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.RobotMap;
/**
 * Add your docs here.
 */
public class Encoders extends Subsystem {
  static Encoders instance = null;
  public static Encoders getInstance() {
    if(instance == null){
      instance = new Encoders();
    }
    return instance;
  }

  private Encoder left;
  private Encoder right;

  private Encoders(){
    left = new Encoder(RobotMap.leftEncoder[0], RobotMap.leftEncoder[1], false, Encoder.EncodingType.k4X);
    right = new Encoder(RobotMap.rightEncoder[0], RobotMap.rightEncoder[1], false, Encoder.EncodingType.k4X);

    double dpp = 0.01308996939;
    left.setDistancePerPulse(dpp);
    right.setDistancePerPulse(dpp);
  }

  public double getLeftDistance(){
    return left.getDistance();
  }

  public double getRightDistance(){
    return right.getDistance();
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
