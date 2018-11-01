/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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

  private DifferentialDrive drive;
  private DoubleSolenoid gearShift;
  public DriveSystem(){
    PWMVictorSPX backLeft = new PWMVictorSPX(RobotMap.backLeftMotor);
    PWMVictorSPX frontLeft = new PWMVictorSPX(RobotMap.frontLeftMotor);
    PWMVictorSPX backRight = new PWMVictorSPX(RobotMap.backRightMotor);
    PWMVictorSPX frontRight = new PWMVictorSPX(RobotMap.frontRightMotor);
    SpeedController right = new SpeedControllerGroup(backRight, frontRight);
    SpeedController left = new SpeedControllerGroup(backLeft, frontLeft);
    
    left.setInverted(true);
    right.setInverted(true);
   
    drive = new DifferentialDrive(left, right);

    gearShift = new DoubleSolenoid(0, 1);
    shiftUp();
  }

  public void arcadeDrive(double x, double y){
    drive.arcadeDrive(x, y);
  }

  public void stop(){
    drive.arcadeDrive(0, 0);
  }

  public void shiftUp(){
    gearShift.set(Value.kReverse); //Changed in EST
  }

  public void shiftDown(){
    gearShift.set(Value.kForward); //Changed in EST
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
