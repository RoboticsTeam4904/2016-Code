package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.RunIfElse;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.PrintCommand;

public class SpinUpFlywheel extends RunIfElse {
	public SpinUpFlywheel() {
		super(new MotorConstant(RobotMap.Component.flywheel, RobotMap.Constant.HumanInput.FLYWHEEL_TARGET_SPEED), new PrintCommand("Flywheel was denied spin-up because the hood wasn't up."), RobotMap.Component.hood::isUp);
	}
}
