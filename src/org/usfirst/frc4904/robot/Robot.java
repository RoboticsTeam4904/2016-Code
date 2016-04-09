package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.autonomous.strategies.CrossLowbarTime;
import org.usfirst.frc4904.autonomous.strategies.CrossMoatTime;
import org.usfirst.frc4904.autonomous.strategies.CrossRampartsTime;
import org.usfirst.frc4904.autonomous.strategies.CrossRoughTerrainTime;
import org.usfirst.frc4904.robot.humaninterface.drivers.Nathan;
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
		autoChooser.addObject(new CrossRampartsTime(RobotMap.Component.chassis, false));
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
		SmartDashboard.putBoolean(SmartDashboardKey.BATTER_END_OF_MATCH_TURN.key, false);
		SmartDashboard.putBoolean(SmartDashboardKey.SHOOT_READY.key, false);
	}
	
	@Override
	public void teleopInitialize() {
		SmartDashboard.putBoolean(SmartDashboardKey.BATTER_END_OF_MATCH_TURN.key, false);
		teleopCommand = new ChassisMove(RobotMap.Component.chassis, driverChooser.getSelected());
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopExecute() {
		SmartDashboard.putNumber(SmartDashboardKey.TIM.key, RobotMap.Component.timEncoder.getDistance());
		SmartDashboard.putBoolean(SmartDashboardKey.FLYWHEEL_STATE.key, RobotMap.Component.flywheelEncoder.getRate() >= RobotMap.Constant.FLYWHEEL_SPIN_UP_SPEED);
		SmartDashboard.putBoolean(SmartDashboardKey.BATTER_END_OF_MATCH_TURN.key, DriverStation.getInstance().getMatchTime() <= RobotMap.Constant.BATTER_END_OF_MATCH_TURN_TIME);
		SmartDashboard.putBoolean(SmartDashboardKey.SHOOT_READY.key, SmartDashboard.getBoolean(SmartDashboardKey.HOOD_STATE.key));
	}
	
	@Override
	public void autonomousInitialize() {
		RobotMap.Component.imu.reset();
	}
	
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
