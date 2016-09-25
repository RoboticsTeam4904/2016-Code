package org.usfirst.frc4904.robot.humaninterface.operators;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.InnieControl;
import org.usfirst.frc4904.robot.commands.Outtake;
import org.usfirst.frc4904.robot.commands.RockNRollerShoot;
import org.usfirst.frc4904.robot.commands.SpinUpFlywheel;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.humaninput.Operator;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class OneButtonShot extends Operator {
	public OneButtonShot() {
		super("OneButtonShot");
	}
	
	@Override
	public void bindCommands() {
		// One button shot
		RobotMap.HumanInput.Operator.stick.button1.onlyWhileHeld(new RockNRollerShoot());
		RobotMap.HumanInput.Operator.stick.button1.onlyWhileHeld(new RunAllSequential(new WaitCommand(RobotMap.Constant.HumanInput.ONE_BUTTON_SHOT_SPINUP_DURATION), new RockNRollerShoot()));
		// Intake/Outtake control
		RobotMap.HumanInput.Operator.stick.button4.onlyWhileReleased(new InnieControl());
		RobotMap.HumanInput.Operator.stick.button4.onlyWhileHeld(new Outtake()); // Backup outtake
		// Backup manual shooting
		RobotMap.HumanInput.Operator.stick.button3.onlyWhileHeld(new SpinUpFlywheel());
		RobotMap.HumanInput.Operator.stick.button5.onlyWhileHeld(new RockNRollerShoot());
	}
}