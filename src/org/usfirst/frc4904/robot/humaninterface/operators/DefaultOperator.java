package org.usfirst.frc4904.robot.humaninterface.operators;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.motor.ControlMotor;
import org.usfirst.frc4904.standard.humaninput.Operator;

public class DefaultOperator extends Operator {
	public DefaultOperator() {
		super("DefaultOperator");
	}
	
	@Override
	public void bindCommands() {
		// RobotMap.HumanInput.Operator.stick.button1.whenPressed(new Shoot(RobotMap.Component.shooter));
		RobotMap.HumanInput.Operator.stick.button1.onlyWhileHeld(new ControlMotor(RobotMap.Component.flywheelMotor, RobotMap.HumanInput.Operator.stick, RobotMap.HumanInput.Operator.stick.Y_AXIS, false));
		// RobotMap.HumanInput.Operator.stick.button2.onlyWhileHeld(new SpinUp(RobotMap.Component.shooter, 0)); // TODO
		// RobotMap.HumanInput.Operator.stick.button3.whenPressed(new HoodDown(RobotMap.Component.shooter.hood)); // TODO
		// RobotMap.HumanInput.Operator.stick.button5.whenPressed(new HoodUp(RobotMap.Component.shooter.hood)); // TODO
	}
}