package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;

public class SpinUpFlywheel extends MotorConstant {
	public SpinUpFlywheel() {
		super(RobotMap.Component.flywheel, RobotMap.Constant.HumanInput.FLYWHEEL_TARGET_SPEED);
	}
	
	@Override
	public void initialize() {
		motorSpeed = RobotMap.Constant.HumanInput.FLYWHEEL_TARGET_SPEED;
		motor.set(motorSpeed);
	}
	
	@Override
	public void execute() {
		motorSpeed = RobotMap.Constant.HumanInput.FLYWHEEL_TARGET_SPEED;
		motor.set(motorSpeed);
	}
}
