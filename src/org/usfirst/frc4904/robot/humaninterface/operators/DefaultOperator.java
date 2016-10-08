package org.usfirst.frc4904.robot.humaninterface.operators;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.InnieControl;
import org.usfirst.frc4904.robot.commands.Outtake;
import org.usfirst.frc4904.robot.commands.ShootDistance;
import org.usfirst.frc4904.robot.commands.SpinUpFlywheelDistance;
import org.usfirst.frc4904.standard.humaninput.Operator;

public class DefaultOperator extends Operator {
	public DefaultOperator() {
		super("DefaultOperator");
	}
	
	@Override
	public void bindCommands() {
		RobotMap.HumanInput.Operator.stick.button1.onlyWhileHeld(new ShootDistance());
		RobotMap.HumanInput.Operator.stick.button2.onlyWhileHeld(new SpinUpFlywheelDistance());
		RobotMap.HumanInput.Operator.stick.button4.onlyWhileHeld(new Outtake());
		RobotMap.HumanInput.Operator.stick.button4.onlyWhileReleased(new InnieControl());
	}
}