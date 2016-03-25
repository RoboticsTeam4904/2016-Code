package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.autonomous.strategies.CrossLowbarTime;
import org.usfirst.frc4904.autonomous.strategies.CrossMoatTime;
import org.usfirst.frc4904.autonomous.strategies.CrossRoughTerrainTime;
import org.usfirst.frc4904.robot.humaninterface.drivers.Nathan;
import org.usfirst.frc4904.robot.humaninterface.drivers.NathanGain;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.standard.CommandRobotBase;
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends CommandRobotBase {
	RobotMap map = new RobotMap();
	
	@Override
	public void initialize() {
		// Configure autonomous command chooser
		autoChooser.addObject(new ChassisIdle(RobotMap.Component.chassis));
		autoChooser.addDefault(new CrossLowbarTime(RobotMap.Component.chassis, false));
		autoChooser.addObject(new CrossMoatTime(RobotMap.Component.chassis, false));
		autoChooser.addObject(new CrossRoughTerrainTime(RobotMap.Component.chassis, false));
		// Configure driver command chooser
		driverChooser.addDefault(new NathanGain());
		driverChooser.addObject(new Nathan());
		// Configure operator command chooser
		operatorChooser.addDefault(new DefaultOperator());
		SmartDashboard.putBoolean(SmartDashboardKey.FLYWHEEL_STATE.key, false);
	}
	
	@Override
	public void teleopInitialize() {
		teleopCommand = new ChassisMove(RobotMap.Component.chassis, driverChooser.getSelected());
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
	public void disabledExecute() {
		RobotMap.Component.ballLoadSensor.calibrate();
	}
	
	@Override
	public void testInitialize() {}
	
	@Override
	public void testExecute() {}
}