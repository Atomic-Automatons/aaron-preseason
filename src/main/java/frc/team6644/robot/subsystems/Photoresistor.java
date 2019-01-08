package frc.frc6644.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;
import frc.frc6644.robot.RobotMap;

public class Photoresistor extends Subsystem {
	private instance Photoresistor = null;
	public static Photoresistor getInstance() {
		if(instance == null){
			instance = new Gyro();
		}
		return instance;
	}
	PhotoResistor1 = AnalogInput(1);
	PhotoResistor2 = AnalogInput(2);
	PhotoResistor3 = AnalogInput(3);
	
	public static int PRvalue1() {
		return PhotoResistor1.getValue();
	}
	public static int PRvalue2() {
		return PhotoResistor2.getValue();
	}
	public static int PRvalue3() {
		return PhotoResistor3.getValue();
	}
}
