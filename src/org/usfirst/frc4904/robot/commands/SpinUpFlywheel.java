package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.motor.MotorSet;

public class SpinUpFlywheel extends MotorSet {
	public SpinUpFlywheel() {
		super(RobotMap.Component.flywheel);
	}
	
	@Override
	public void initialize() {
		super.set(RobotMap.Constant.HumanInput.FLYWHEEL_TARGET_SPEED);
		RobotMap.Component.flywheel.enablePID();
		super.initialize();
	}
	
	@Override
	public void execute() {
		super.set(RobotMap.Constant.HumanInput.FLYWHEEL_TARGET_SPEED);
		super.execute();
	}
	
	@Override
	public void end() {
		RobotMap.Component.flywheel.disablePID();
		super.end();
	}
	
	@Override
	public void interrupted() {
		end();
	}
}
