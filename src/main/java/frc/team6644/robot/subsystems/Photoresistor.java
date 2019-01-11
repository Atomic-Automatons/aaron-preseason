package frc.frc6644.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;
import frc.frc6644.robot.RobotMap;

public class Photoresistor extends Subsystem {
	private static Photoresistor instance = null;
	public static Photoresistor getInstance() {
		if(instance == null){
			instance = new Photoresistor();
		}
		return instance;
	}
	
	AnalogInput photoResistor1 = new AnalogInput(1);
	AnalogInput photoResistor2 = new AnalogInput(2);
	AnalogInput photoResistor3 = new AnalogInput(3);
	
	public int getLeft() {
		return photoResistor1.getValue();
	}
	public int get2() {
		return photoResistor2.getValue();
	}
	public int get3() {
		return photoResistor3.getValue();
	}
	
	public void initDefaultCommand(){
		
	}
}
