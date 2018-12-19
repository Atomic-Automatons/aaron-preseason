package frc.frc6644.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import frc.frc6644.robot.RobotMap;

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
		
		right.setReverseDirection(true);

		double dpp = 3 * (2 * 3 * Math.PI) / 1000; 
		left.setDistancePerPulse(dpp);
		right.setDistancePerPulse(dpp);
	}
	
	public void reset(){
		left.reset();
		right.reset();
	}
	
	public double getLeftDistance(){
		return left.getDistance();
	}

	public double getRightDistance(){
		return right.getDistance();
	}
	
	public double getDistance(){
		return (left.getDistance() + right.getDistance()) / 2;
	}
  
	@Override
	public void initDefaultCommand() {}
}
