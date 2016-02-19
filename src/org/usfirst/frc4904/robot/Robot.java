package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.autonomous.commands.CrossLowbar;
import org.usfirst.frc4904.robot.humaninterface.drivers.Nathan;
import org.usfirst.frc4904.robot.humaninterface.drivers.NathanGain;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.standard.CommandRobotBase;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends CommandRobotBase {
	// Even static objects need initializers
	RobotMap map = new RobotMap();
	Command autonomousCommand;
	SendableChooser chooser;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		super.robotInit(null);
		System.out.println("CommandRobotBase init complete");
		// Configure autonomous command chooser
		autoChooser.addDefault(new ChassisIdle(RobotMap.Component.chassis));
		autoChooser.addObject(new CrossLowbar(RobotMap.Component.chassis, false));
		// Configure driver command chooser
		driverChooser.addDefault(new NathanGain());
		driverChooser.addObject(new Nathan());
		// Configure operator command chooser
		operatorChooser.addDefault(new DefaultOperator());
		// Display choosers on SmartDashboard
		displayChoosers();
		SmartDashboard.putData(Scheduler.getInstance());
		LogKitten.setDefaultPrintLevel(LogKitten.LEVEL_WARN);
		LogKitten.setDefaultDSLevel(LogKitten.LEVEL_WARN);
	}
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void autonomousInit() {
		// schedule the autonomous command (example)
		autonomousCommand = autoChooser.getSelected();
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}
	
	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void teleopInit() {
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		driverChooser.getSelected().bindCommands();
		operatorChooser.getSelected().bindCommands();
		teleopCommand = new ChassisMove(RobotMap.Component.chassis, driverChooser.getSelected(), RobotMap.Constant.HumanInput.X_SPEED_SCALE, RobotMap.Constant.HumanInput.Y_SPEED_SCALE, RobotMap.Constant.HumanInput.TURN_SPEED_SCALE);
		teleopCommand.start();
	}
	
	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		super.disabledInit();
		if (teleopCommand != null) {
			teleopCommand.cancel();
		}
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
