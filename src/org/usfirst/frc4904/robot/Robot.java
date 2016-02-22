package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.autonomous.strategies.CrossLowbarTime;
import org.usfirst.frc4904.autonomous.strategies.CrossLowbarTimeAndShoot;
import org.usfirst.frc4904.autonomous.strategies.CrossMoatTime;
import org.usfirst.frc4904.robot.humaninterface.drivers.Nathan;
import org.usfirst.frc4904.robot.humaninterface.drivers.NathanGain;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.standard.CommandRobotBase;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends CommandRobotBase {
	RobotMap map = new RobotMap();
	
	@Override
	public void initialize() {
		// Configure autonomous command chooser
		autoChooser.addObject(new ChassisIdle(RobotMap.Component.chassis));
		autoChooser.addObject(new CrossLowbarTimeAndShoot(RobotMap.Component.chassis, RobotMap.Component.cameraIR, false));
		autoChooser.addObject(new CrossLowbarTime(RobotMap.Component.chassis, false));
		autoChooser.addDefault(new CrossMoatTime(RobotMap.Component.chassis, false));
		// Configure driver command chooser
		driverChooser.addDefault(new NathanGain());
		driverChooser.addObject(new Nathan());
		// Configure operator command chooser
		operatorChooser.addDefault(new DefaultOperator());
		SmartDashboard.putBoolean(SmartDashboardKey.FLYWHEEL_STATE.key, false);
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
	public void teleopExecute() {
		SmartDashboard.putNumber(SmartDashboardKey.DISTANCE_FROM_GOAL.key, RobotMap.Component.cameraIR.getGoalOffDistance(true));
		SmartDashboard.putNumber(SmartDashboardKey.ANGLE_OFF_GOAL.key, RobotMap.Component.cameraIR.getGoalOffAngle(true));
		SmartDashboard.putBoolean(SmartDashboardKey.IN_RANGE.key, (RobotMap.Constant.SHOOTING_RANGE_MIN <= RobotMap.Component.cameraIR.getGoalOffDistance(true)) && (RobotMap.Component.cameraIR.getGoalOffDistance(true) < RobotMap.Constant.SHOOTING_RANGE_MAX));
		SmartDashboard.putBoolean(SmartDashboardKey.FLYWHEEL_STATE.key, RobotMap.Component.flywheelEncoder.getRate() >= RobotMap.Constant.FLYWHEEL_SPIN_UP_SPEED);
		SmartDashboard.putBoolean(SmartDashboardKey.ANGLE_WITHIN_FIVE.key, (RobotMap.Component.cameraIR.getGoalOffAngle(true) < 5) && (RobotMap.Component.cameraIR.getGoalOffAngle(true) > -5));
	}
	
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
