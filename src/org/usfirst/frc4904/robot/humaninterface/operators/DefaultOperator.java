package org.usfirst.frc4904.robot.humaninterface.operators;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.shooter.HoodDown;
import org.usfirst.frc4904.robot.commands.shooter.HoodUp;
import org.usfirst.frc4904.robot.commands.shooter.RockNRollerOuttake;
import org.usfirst.frc4904.robot.commands.shooter.RockNRollerShoot;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import org.usfirst.frc4904.standard.commands.motor.MotorControl;
import org.usfirst.frc4904.standard.custom.controllers.Controller;
import org.usfirst.frc4904.standard.humaninput.Operator;

public class DefaultOperator extends Operator {
	public DefaultOperator() {
		super("DefaultOperator");
	}
	
	@Override
	public void bindCommands() {
		RobotMap.HumanInput.Operator.stick.button1.onlyWhileHeld(new RockNRollerShoot(RobotMap.Component.rockNRoller));
		RobotMap.HumanInput.Operator.stick.button2.onlyWhileHeld(new MotorConstant(RobotMap.Component.flywheel, 1));
		RobotMap.HumanInput.Operator.stick.button4.onlyWhileHeld(new MotorConstant(RobotMap.Component.intakeRoller, -1));
		RobotMap.HumanInput.Operator.stick.button4.onlyWhileHeld(new RockNRollerOuttake(RobotMap.Component.rockNRoller));
		RobotMap.HumanInput.Operator.stick.button3.whenPressed(new HoodDown(RobotMap.Component.hood));
		RobotMap.HumanInput.Operator.stick.button5.whenPressed(new HoodUp(RobotMap.Component.hood));
		RobotMap.HumanInput.Operator.stick.button4.onlyWhileReleased(new MotorControl(RobotMap.Component.intakeRoller, RobotMap.HumanInput.Operator.stick, Controller.Y_AXIS, false));
	}
}