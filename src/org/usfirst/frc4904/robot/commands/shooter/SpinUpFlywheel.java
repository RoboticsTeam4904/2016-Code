package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.LogWarning;
import org.usfirst.frc4904.standard.commands.RunIfElse;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;

public class SpinUpFlywheel extends RunIfElse {
	public SpinUpFlywheel() {
		super(new MotorConstant(RobotMap.Component.flywheel, 1), new LogWarning("Flywheel was denied spin-up because the hood wasn't up."), RobotMap.Component.hood::isUp);
	}
}
