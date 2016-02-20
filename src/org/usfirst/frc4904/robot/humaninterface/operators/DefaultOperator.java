package org.usfirst.frc4904.robot.humaninterface.operators;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.shooter.HoodDown;
import org.usfirst.frc4904.robot.commands.shooter.HoodUp;
import org.usfirst.frc4904.robot.commands.shooter.Outtake;
import org.usfirst.frc4904.robot.commands.shooter.RockNRollerShoot;
import org.usfirst.frc4904.robot.commands.shooter.SpinUpFlywheel;
import org.usfirst.frc4904.standard.humaninput.Operator;

public class DefaultOperator extends Operator {
	public DefaultOperator() {
		super("DefaultOperator");
	}
	
	@Override
	public void bindCommands() {
		RobotMap.HumanInput.Operator.stick.button1.onlyWhileHeld(new RockNRollerShoot());
		RobotMap.HumanInput.Operator.stick.button2.onlyWhileHeld(new SpinUpFlywheel());
		RobotMap.HumanInput.Operator.stick.button4.onlyWhileHeld(new Outtake());
		RobotMap.HumanInput.Operator.stick.button3.whenPressed(new HoodDown());
		RobotMap.HumanInput.Operator.stick.button5.whenPressed(new HoodUp());
	}
}