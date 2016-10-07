package org.usfirst.frc4904.robot.humaninterface.operators;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.InnieControl;
import org.usfirst.frc4904.robot.commands.Outtake;
import org.usfirst.frc4904.robot.commands.RockNRollerShoot;
import org.usfirst.frc4904.robot.commands.SpinUpFlywheel;
import org.usfirst.frc4904.standard.commands.RunAllParallel;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.humaninput.Operator;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DefaultOperator extends Operator {
	public DefaultOperator() {
		super("DefaultOperator");
	}
	
	@Override
	public void bindCommands() {
		RobotMap.HumanInput.Operator.stick.button1.onlyWhileHeld(new RunAllParallel(new SpinUpFlywheel(), new RunAllSequential(new WaitCommand(2), new RockNRollerShoot())));
		RobotMap.HumanInput.Operator.stick.button2.onlyWhileHeld(new SpinUpFlywheel());
		RobotMap.HumanInput.Operator.stick.button4.onlyWhileHeld(new Outtake());
		RobotMap.HumanInput.Operator.stick.button4.onlyWhileReleased(new InnieControl());
	}
}