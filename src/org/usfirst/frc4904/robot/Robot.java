package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.autonomous.strategies.CrossLowbarTime;
import org.usfirst.frc4904.autonomous.strategies.CrossLowbarTimeAndShoot;
import org.usfirst.frc4904.robot.humaninterface.drivers.Nathan;
import org.usfirst.frc4904.robot.humaninterface.drivers.NathanGain;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.standard.CommandRobotBase;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;

public class Robot extends CommandRobotBase {
	RobotMap map = new RobotMap();
	
	@Override
	public void initialize() {
		// Configure autonomous command chooser
		autoChooser.addObject(new ChassisIdle(RobotMap.Component.chassis));
		autoChooser.addDefault(new CrossLowbarTimeAndShoot(RobotMap.Component.chassis, RobotMap.Component.cameraIR, false));
		autoChooser.addObject(new CrossLowbarTime(RobotMap.Component.chassis, false));
		// Configure driver command chooser
		driverChooser.addDefault(new NathanGain());
		driverChooser.addObject(new Nathan());
		// Configure operator command chooser
		operatorChooser.addDefault(new DefaultOperator());
		LogKitten.setPrintMute(true);
	}
	
	@Override
	public void teleopInitialize() {
		teleopCommand = new ChassisMove(RobotMap.Component.chassis, driverChooser.getSelected(), RobotMap.Constant.HumanInput.X_SPEED_SCALE, RobotMap.Constant.HumanInput.Y_SPEED_SCALE, RobotMap.Constant.HumanInput.TURN_SPEED_SCALE);
		RobotMap.Component.timEncoder.reset();
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopExecute() {}
	
	@Override
	public void autonomousInitialize() {}
	
	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousExecute() {}
	
	@Override
	public void disabledInitialize() {}
	
	@Override
	public void disabledExecute() {}
	
	@Override
	public void testInitialize() {}
	
	@Override
	public void testExecute() {}
}
