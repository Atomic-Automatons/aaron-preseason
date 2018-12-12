package frc.frc6644.robot.commands;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.frc6644.robot.subsystems.DriveSystem;
import frc.frc6644.robot.OI;

public class ControllerDrive extends Command{
    public ControllerDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(DriveSystem.getInstance());
      }
    
      // Called just before this Command runs the first time
      @Override
      protected void initialize() {
        //  OI.getType();
      }
    
      // Called repeatedly when this Command is scheduled to run
      @Override
      protected void execute() {
         // double sensitivity = (-OI.controller.getRawAxis(3) + 1);
         // DriveSystem.getInstance().arcadeDrive(OI.controller.getY(), OI.controller.getX());
        // double gas = OI.controller.getTriggerAxis(Hand.kRight);
     //    double brake = OI.controller.getTriggerAxis(Hand.kLeft);
    //     double steering = OI.controller.getX(Hand.kLeft);
     //    DriveSystem.getInstance().arcadeDrive(gas-brake, steering);
      }
    
      // Make this return true when this Command no longer needs to run execute()
      @Override
      protected boolean isFinished() {
        return false;
      }
    
      // Called once after isFinished returns true
      @Override
      protected void end() {
          DriveSystem.getInstance().stop();
      }
    
      // Called when another command which requires one or more of the same
      // subsystems is scheduled to run
      @Override
      protected void interrupted() {
      }
}