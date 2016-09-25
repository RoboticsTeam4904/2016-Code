package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;

public class SpinUpFlywheel extends MotorConstant {
	public SpinUpFlywheel() {
		super(RobotMap.Component.flywheel, RobotMap.Constant.Component.FLYWHEEL_SHOOT_SPEED);
	}
}
