package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.humaninterface.drivers.Nathan;
import org.usfirst.frc4904.robot.humaninterface.drivers.NathanGain;
import org.usfirst.frc4904.robot.humaninterface.operators.DefaultOperator;
import org.usfirst.frc4904.standard.CommandRobotBase;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends CommandRobotBase {
	RobotMap map = new RobotMap();
	private SendableChooser autonChooser;
	private SendableChooser positionChooser;
	
	@Override
	public void initialize() {
		// Configure autonomous command chooser
		autoChooser.addObject(RobotMap.Constant.AutonomousStrategies.StrategyMap.get(RobotMap.Constant.AutonomousStrategies.IDLE));
		// Configure driver command chooser
		driverChooser.addDefault(new NathanGain());
		driverChooser.addObject(new Nathan());
		// Configure operator command chooser
		operatorChooser.addDefault(new DefaultOperator());
		// Configure auton routine Chooser
		autonChooser = new SendableChooser();
		autonChooser.addDefault("Lowbar: 0", 0);
		autonChooser.addObject("Rough Terrain: 1", 1);
		autonChooser.addObject("Moat: 2", 2);
		// Configure position Chooser
		positionChooser = new SendableChooser();
		positionChooser.addDefault("Left: 0", 0);
		positionChooser.addObject("Right: 1", 1);
		// The Input numbers for the choosers
		SmartDashboard.putNumber(SmartDashboardKey.AUTON_POSITION.key, 0);
		SmartDashboard.putNumber(SmartDashboardKey.AUTON_ROUTINE.key, RobotMap.Constant.AutonomousStrategies.IDLE);
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
	public void teleopExecute() {
		SmartDashboard.putBoolean(SmartDashboardKey.FLYWHEEL_STATE.key, RobotMap.Component.flywheelEncoder.getRate() >= RobotMap.Constant.FLYWHEEL_SPIN_UP_SPEED);
		SmartDashboard.putNumber(SmartDashboardKey.TIM.key, RobotMap.Component.timEncoder.getDistance());
		SmartDashboard.putBoolean(SmartDashboardKey.FLYWHEEL_STATE.key, RobotMap.Component.flywheelEncoder.getRate() >= RobotMap.Constant.FLYWHEEL_SPIN_UP_SPEED);
	}
	
	@Override
	public void autonomousInitialize() {
		// SmartDashboard.getNumber(SmartDashboardKey.AUTON_POSITION.key);
		int autonIndex = (new Double(SmartDashboard.getNumber(SmartDashboardKey.AUTON_ROUTINE.key)).intValue());
		autoChooser.addDefault(RobotMap.Constant.AutonomousStrategies.StrategyMap.get(autonIndex));
	}
	
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
