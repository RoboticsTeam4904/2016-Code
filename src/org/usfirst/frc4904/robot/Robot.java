package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.autonomous.strategies.CrossLowbarTime;
import org.usfirst.frc4904.autonomous.strategies.CrossLowbarTimeAndShoot;
import org.usfirst.frc4904.autonomous.strategies.CrossMoatTime;
import org.usfirst.frc4904.autonomous.strategies.CrossMoatTimeAndShoot;
import org.usfirst.frc4904.autonomous.strategies.CrossRampartsTime;
import org.usfirst.frc4904.autonomous.strategies.CrossRampartsTimeAndShoot;
import org.usfirst.frc4904.autonomous.strategies.CrossRoughTerrainTime;
import org.usfirst.frc4904.autonomous.strategies.CrossRoughTerrainTimeAndAlign;
import org.usfirst.frc4904.autonomous.strategies.CrossRoughTerrainTimeAndShoot;
import org.usfirst.frc4904.robot.humaninterface.drivers.NathanGain;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.robot.subsystems.Camera.CameraData;
import org.usfirst.frc4904.standard.CommandRobotBase;
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends CommandRobotBase {
	RobotMap map = new RobotMap();
	private SendableChooser positionChooser;
	
	@Override
	public void initialize() {
		// Configure autonomous command chooser
		autoChooser.addDefault(new ChassisIdle(RobotMap.Component.chassis));
		autoChooser.addObject(new CrossLowbarTime(RobotMap.Component.chassis, false));
		autoChooser.addObject(new CrossLowbarTimeAndShoot(RobotMap.Component.chassis, RobotMap.Component.camera, false));
		autoChooser.addObject(new CrossMoatTime(RobotMap.Component.chassis, false));
		autoChooser.addObject(new CrossMoatTimeAndShoot(RobotMap.Component.chassis, RobotMap.Component.camera, false));
		autoChooser.addObject(new CrossRoughTerrainTime(RobotMap.Component.chassis, false));
		autoChooser.addObject(new CrossRoughTerrainTimeAndAlign(RobotMap.Component.chassis, RobotMap.Component.camera, false));
		autoChooser.addObject(new CrossRoughTerrainTimeAndShoot(RobotMap.Component.chassis, RobotMap.Component.camera, false));
		autoChooser.addObject(new CrossRampartsTime(RobotMap.Component.chassis, false));
		autoChooser.addObject(new CrossRampartsTimeAndShoot(RobotMap.Component.chassis, RobotMap.Component.camera, false));
		// Configure driver command chooser
		driverChooser.addDefault(new NathanGain());
		// Configure operator command chooser
		operatorChooser.addDefault(new DefaultOperator());
		// Configure position Chooser
		positionChooser = new SendableChooser();
		positionChooser.addDefault("Left: 0", 0);
		positionChooser.addObject("Right: 1", 1);
		// Initialize SmartDashboard display values
		SmartDashboard.putBoolean(SmartDashboardKey.SHOOT_READY.key, false);
	}
	
	@Override
	public void teleopInitialize() {
		teleopCommand = new ChassisMove(RobotMap.Component.chassis, driverChooser.getSelected());
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopExecute() {
		CameraData d = RobotMap.Component.camera.getCameraData();
		final double offAngle = RobotMap.Constant.AutonomousMetric.ALIGN_SETPOINT - d.getGoalX();
		final double offDistance = RobotMap.Constant.AutonomousMetric.DISTANCE_ALIGN_SETPOINT - d.getGoalY();
		SmartDashboard.putNumber(SmartDashboardKey.ANGLE_OFF_GOAL.key, offAngle);
		SmartDashboard.putNumber(SmartDashboardKey.DISTANCE_FROM_GOAL.key, offDistance);
		final boolean angleAligned = Math.abs(offAngle) < RobotMap.Constant.AutonomousMetric.ALIGN_TOLERANCE;
		final boolean distanceAligned = Math.abs(offDistance) < RobotMap.Constant.AutonomousMetric.DISTANCE_ALIGN_TOLERANCE;
		SmartDashboard.putBoolean(SmartDashboardKey.ANGLE_ALIGNED.key, angleAligned);
		SmartDashboard.putBoolean(SmartDashboardKey.DISTANCE_ALIGNED.key, distanceAligned);
		SmartDashboard.putBoolean(SmartDashboardKey.SHOOT_READY.key, angleAligned && distanceAligned);
	}
	
	@Override
	public void autonomousInitialize() {}
	
	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousExecute() {}
	
	/**
	 * This function is called periodically in every robot mode
	 */
	@Override
	public void alwaysExecute() {
		putSDSubsystemSummary();
	}
	
	@Override
	public void disabledInitialize() {}
	
	@Override
	public void disabledExecute() {}
	
	@Override
	public void testInitialize() {}
	
	@Override
	public void testExecute() {}
	
	void putSDSubsystemSummary() {
		String summary = "";
		for (Subsystem subsystem : RobotMap.Component.mainSubsystems) {
			summary += "{" + subsystem.getName() + "} running command {" + subsystem.getCurrentCommand() + "}\n";
		}
		SmartDashboard.putString(SmartDashboardKey.SUBSYSTEM_SUMMARY.key, summary);
	}
}
