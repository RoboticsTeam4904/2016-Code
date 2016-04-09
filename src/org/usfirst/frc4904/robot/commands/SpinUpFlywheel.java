package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.RunIfElse;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.PrintCommand;

public class SpinUpFlywheel extends RunIfElse {
	public SpinUpFlywheel() {
		super(new MotorConstant(RobotMap.Component.flywheel, 1), new PrintCommand("Flywheel was denied spin-up because the hood wasn't up."), RobotMap.Component.hood::isUp);
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		RobotMap.Component.flywheel.setSpinUpEpoch();
	}
	
	@Override
	protected void end() {
		RobotMap.Component.flywheel.discardSpinUpEpoch();
		super.end();
	}
}
