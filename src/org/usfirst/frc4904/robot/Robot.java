package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.humaninterface.drivers.Nathan;
import org.usfirst.frc4904.robot.humaninterface.drivers.NathanGain;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.standard.CommandRobotBase;<<<<<<<HEAD=======
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;>>>>>>>master
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends CommandRobotBase {
	RobotMap map = new RobotMap();
	
	@Override
	public void initialize() {
		// Configure autonomous command chooser
		autoChooser.addObject(RobotMap.Constant.AutonomousStrategies.StrategyMap.get(RobotMap.Constant.AutonomousStrategies.IDLE));
		autoChooser.addDefault(RobotMap.Constant.AutonomousStrategies.StrategyMap.get(RobotMap.Constant.AutonomousStrategies.TIMED_LOWBAR));
		autoChooser.addObject(RobotMap.Constant.AutonomousStrategies.StrategyMap.get(RobotMap.Constant.AutonomousStrategies.TIMED_ROUGH_TERRAIN));
		autoChooser.addObject(RobotMap.Constant.AutonomousStrategies.StrategyMap.get(RobotMap.Constant.AutonomousStrategies.TIMED_MOAT));
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
<<<<<<< HEAD
		RobotMap.Component.timEncoder.reset();
=======
>>>>>>> master
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
		SmartDashboard.putNumber(SmartDashboardKey.TIM.key, RobotMap.Component.timEncoder.getDistance());
		SmartDashboard.putBoolean(SmartDashboardKey.FLYWHEEL_STATE.key, RobotMap.Component.flywheelEncoder.getRate() >= RobotMap.Constant.FLYWHEEL_SPIN_UP_SPEED);
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
	public void disabledExecute() {
		RobotMap.Component.ballLoadSensor.calibrate();
	}
	
	@Override
	public void testInitialize() {}
	
	@Override
	public void testExecute() {}
}
