
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Launcher extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    static private Launcher instance = null;

    static public Launcher getInstance() {
        if (instance == null) {
            instance = new Launcher();
        }
        return instance;
    }

    private Spark left;
    private Spark right;

    public Launcher() {
        left = new Spark(RobotMap.leftLauncherMotor);
        right = new Spark(RobotMap.rightLauncherMotor);
        right.setInverted(true);
    }

    public void setSpeed(double speed) {
        left.set(speed);
        right.set(speed);

    }

    public void stop() {
        left.set(0);
        right.set(0);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}
