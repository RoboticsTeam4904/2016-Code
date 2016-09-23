package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.autonomous.strategies.CrossLowbarTime;
import org.usfirst.frc4904.autonomous.strategies.CrossLowbarTimeAndAlign;
import org.usfirst.frc4904.autonomous.strategies.CrossLowbarTimeAndShoot;
import org.usfirst.frc4904.autonomous.strategies.CrossMoatTime;
import org.usfirst.frc4904.autonomous.strategies.CrossRampartsTime;
import org.usfirst.frc4904.autonomous.strategies.CrossRoughTerrainTime;
import org.usfirst.frc4904.robot.humaninterface.drivers.NathanGain;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.standard.CommandRobotBase;
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import edu.wpi.first.wpilibj.DriverStation;
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
		autoChooser.addObject(new CrossMoatTime(RobotMap.Component.chassis, false));
		autoChooser.addObject(new CrossRoughTerrainTime(RobotMap.Component.chassis, false));
		autoChooser.addObject(new CrossLowbarTimeAndAlign(RobotMap.Component.chassis, RobotMap.Component.camera, false));
		autoChooser.addObject(new CrossLowbarTimeAndShoot(RobotMap.Component.chassis, RobotMap.Component.camera, false));
		autoChooser.addObject(new CrossRampartsTime(RobotMap.Component.chassis, false));
		// Configure driver command chooser
		driverChooser.addDefault(new NathanGain());
		// Configure operator command chooser
		operatorChooser.addDefault(new DefaultOperator());
		// Configure position Chooser
		positionChooser = new SendableChooser();
		positionChooser.addDefault("Left: 0", 0);
		positionChooser.addObject("Right: 1", 1);
		// Initialize SmartDashboard display values
		SmartDashboard.putNumber(SmartDashboardKey.TIM.key, 0);
		SmartDashboard.putBoolean(SmartDashboardKey.FLYWHEEL_STATE.key, false);
		SmartDashboard.putBoolean(SmartDashboardKey.BATTER_END_OF_MATCH_TURN.key, false);
		SmartDashboard.putBoolean(SmartDashboardKey.SHOOT_READY.key, false);
	}
	
	@Override
	public void teleopInitialize() {
		SmartDashboard.putBoolean(SmartDashboardKey.BATTER_END_OF_MATCH_TURN.key, false);
		teleopCommand = new ChassisMove(RobotMap.Component.chassis, driverChooser.getSelected());
		teleopCommand.start();
		RobotMap.Component.timEncoder.reset();
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopExecute() {
		final double offAngle = 0;
		final double offDistance = 0;
		SmartDashboard.putNumber(SmartDashboardKey.TIM.key, RobotMap.Component.timEncoder.getDistance());
		SmartDashboard.putNumber(SmartDashboardKey.ANGLE_OFF_GOAL.key, offAngle);
		SmartDashboard.putNumber(SmartDashboardKey.DISTANCE_FROM_GOAL.key, offDistance);
		SmartDashboard.putBoolean(SmartDashboardKey.ANGLE_WITHIN_FIVE.key, Math.abs(offAngle) < 5);
		// SmartDashboard.putBoolean(SmartDashboardKey.IN_RANGE.key, MIN < Math.abs(offDistance) < MAX);
		SmartDashboard.putBoolean(SmartDashboardKey.FLYWHEEL_STATE.key, RobotMap.Component.flywheelEncoder.getRate() >= RobotMap.Constant.FLYWHEEL_SPIN_UP_SPEED);
		SmartDashboard.putBoolean(SmartDashboardKey.SHOOT_READY.key, true);
		double matchTime = DriverStation.getInstance().getMatchTime();
		boolean nearEndOfMatch = matchTime <= RobotMap.Constant.END_OF_MATCH_NOTIF_START_TIME;
		boolean veryNearEndOfMatch = matchTime <= RobotMap.Constant.END_OF_MATCH_NOTIF_START_TIME - RobotMap.Constant.END_OF_MATCH_NOTIF_DURATION;
		SmartDashboard.putBoolean(SmartDashboardKey.BATTER_END_OF_MATCH_TURN.key, nearEndOfMatch);
		if (nearEndOfMatch && !veryNearEndOfMatch) {
			RobotMap.HumanInput.Driver.xbox.setRumble(1);
		}
		if (veryNearEndOfMatch) {
			RobotMap.HumanInput.Driver.xbox.setRumble(0);
		}
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
