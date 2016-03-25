package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.autonomous.strategies.CrossLowbarTime;
import org.usfirst.frc4904.autonomous.strategies.CrossMoatTime;
import org.usfirst.frc4904.autonomous.strategies.CrossRoughTerrainTime;
import org.usfirst.frc4904.robot.custom.PIDOffAngleChassisController;
import org.usfirst.frc4904.robot.humaninterface.drivers.Nathan;
import org.usfirst.frc4904.robot.humaninterface.drivers.NathanGain;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.standard.CommandRobotBase;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;<<<<<<<HEAD import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;=======import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;>>>>>>>master
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends CommandRobotBase {
	RobotMap map = new RobotMap();
	private ChassisMove teleopNormal;
	private ChassisMove teleopAlign;
	private SendableChooser positionChooser;
	
	@Override
	public void initialize() {
		// Configure autonomous command chooser
		autoChooser.addDefault(new ChassisIdle(RobotMap.Component.chassis));
		autoChooser.addObject(new CrossLowbarTime(RobotMap.Component.chassis, false));
		autoChooser.addObject(new CrossMoatTime(RobotMap.Component.chassis, false));
		autoChooser.addObject(new CrossRoughTerrainTime(RobotMap.Component.chassis, false));
		// Configure driver command chooser
		driverChooser.addDefault(new NathanGain());
		driverChooser.addObject(new Nathan());
		// Configure operator command chooser
		operatorChooser.addDefault(new DefaultOperator());
		// Configure position Chooser
		positionChooser = new SendableChooser();
		positionChooser.addDefault("Left: 0", 0);
		positionChooser.addObject("Right: 1", 1);
		// Initialize SmartDashboard display values
		SmartDashboard.putNumber(SmartDashboardKey.TIM.key, 0);
		SmartDashboard.putBoolean(SmartDashboardKey.FLYWHEEL_STATE.key, false);
	}
	
	@Override
	public void teleopInitialize() {
		teleopCommand = new ChassisMove(RobotMap.Component.chassis, driverChooser.getSelected());
		teleopAlign = new ChassisMove(RobotMap.Component.chassis, new PIDOffAngleChassisController(driverChooser.getSelected(), RobotMap.Component.cameraPIDSource, new CustomPIDController(RobotMap.Constant.Component.AlignAngle_P, RobotMap.Constant.Component.AlignAngle_I, RobotMap.Constant.Component.AlignAngle_D, RobotMap.Component.cameraPIDSource), RobotMap.Constant.Component.AlignAngleTolerance));
		teleopCommand = teleopNormal;
		teleopCommand.start();
		RobotMap.Component.timEncoder.reset();
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopExecute() {
		if (RobotMap.HumanInput.Driver.xbox.y.get() && (teleopCommand != teleopAlign)) {
			teleopCommand.cancel();
			teleopAlign = new ChassisMove(RobotMap.Component.chassis, new PIDOffAngleChassisController(driverChooser.getSelected(), RobotMap.Component.cameraPIDSource, new CustomPIDController(RobotMap.Constant.Component.AlignAngle_P, RobotMap.Constant.Component.AlignAngle_I, RobotMap.Constant.Component.AlignAngle_D, RobotMap.Component.cameraPIDSource), RobotMap.Constant.Component.AlignAngleTolerance));
			teleopCommand = teleopAlign;
			teleopCommand.start();
		}
		if (((!RobotMap.HumanInput.Driver.xbox.y.get()) || teleopAlign.getController().finished()) && (teleopCommand != teleopNormal)) {
			teleopCommand.cancel();
			teleopNormal = new ChassisMove(RobotMap.Component.chassis, driverChooser.getSelected());
			teleopCommand = teleopNormal;
			teleopCommand.start();
		} // totaly sketch, Idealy we would not need to change the teleop command and just run a command, but we need a need to change the chassis controller
		SmartDashboard.putNumber(SmartDashboardKey.DISTANCE_FROM_GOAL.key, RobotMap.Component.cameraIR.getGoalOffDistance(true));
		SmartDashboard.putNumber(SmartDashboardKey.ANGLE_OFF_GOAL.key, RobotMap.Component.cameraIR.getGoalOffAngle(true));
		SmartDashboard.putBoolean(SmartDashboardKey.IN_RANGE.key, (RobotMap.Constant.SHOOTING_RANGE_MIN <= RobotMap.Component.cameraIR.getGoalOffDistance(true)) && (RobotMap.Component.cameraIR.getGoalOffDistance(true) < RobotMap.Constant.SHOOTING_RANGE_MAX));
		SmartDashboard.putBoolean(SmartDashboardKey.FLYWHEEL_STATE.key, RobotMap.Component.flywheelEncoder.getRate() >= RobotMap.Constant.FLYWHEEL_SPIN_UP_SPEED);
		SmartDashboard.putBoolean(SmartDashboardKey.ANGLE_WITHIN_FIVE.key, (RobotMap.Component.cameraIR.getGoalOffAngle(true) < 5) && (RobotMap.Component.cameraIR.getGoalOffAngle(true) > -5));
		LogKitten.v("Raw Data: " + RobotMap.Component.cameraIR.getCameraData(false) + " Off Angle: " + RobotMap.Component.cameraIR.getGoalOffAngle(false) + " Distance:" + RobotMap.Component.cameraIR.getGoalOffDistance(false) + " In Range: " + ((RobotMap.Constant.SHOOTING_RANGE_MIN >= RobotMap.Component.cameraIR.getGoalOffDistance(true)) && (RobotMap.Component.cameraIR.getGoalOffDistance(true) > RobotMap.Constant.SHOOTING_RANGE_MAX)), true);
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
