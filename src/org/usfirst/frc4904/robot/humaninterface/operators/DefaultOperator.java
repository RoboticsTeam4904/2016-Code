package org.usfirst.frc4904.robot.humaninterface.operators;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.HoodDown;
import org.usfirst.frc4904.robot.commands.HoodUp;
import org.usfirst.frc4904.robot.commands.InnieControl;
import org.usfirst.frc4904.robot.commands.Outtake;
import org.usfirst.frc4904.robot.commands.RockNRollerShoot;
import org.usfirst.frc4904.robot.commands.SpinUpFlywheel;
import org.usfirst.frc4904.robot.commands.TimTap;
import org.usfirst.frc4904.standard.commands.RunAllParallel;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.commands.SingleOp;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import org.usfirst.frc4904.standard.humaninput.Operator;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DefaultOperator extends Operator {
	public DefaultOperator() {
		super("DefaultOperator");
	}
	
	@Override
	public void bindCommands() {
		RobotMap.HumanInput.Operator.stick.button1.onlyWhileHeld(new RunAllParallel(new MotorConstant(RobotMap.Component.innie, RobotMap.Constant.INNIE_SHOOT_SPEED), new RockNRollerShoot()));
		RobotMap.HumanInput.Operator.stick.button2.whenPressed(new HoodUp());
		RobotMap.HumanInput.Operator.stick.button2.onlyWhileHeld(new RunAllSequential(new WaitCommand(RobotMap.Constant.HumanInput.FLYWHEEL_SPINUP_AFTER_HOODUP_DELAY_SECONDS), new SpinUpFlywheel()));
		RobotMap.HumanInput.Operator.stick.button2.whenReleased(new RunAllSequential(new WaitCommand(RobotMap.Constant.HumanInput.HOODDOWN_AFTER_TRIGGERRELEASE_DELAY_SECONDS), new HoodDown()));
		RobotMap.HumanInput.Operator.stick.button4.onlyWhileHeld(new Outtake());
		RobotMap.HumanInput.Operator.stick.button4.onlyWhileReleased(new InnieControl());
		RobotMap.HumanInput.Operator.stick.button5.whenPressed(new HoodUp());
		RobotMap.HumanInput.Operator.stick.button3.whenPressed(new HoodDown());
		RobotMap.HumanInput.Operator.stick.button6.whenPressed(new SingleOp() {
			@Override
			protected void runOp() {
				RobotMap.Component.shooter.ballLoadOverride = !RobotMap.Component.shooter.ballLoadOverride; // toggle
			}
		});
		RobotMap.HumanInput.Operator.stick.button11.onlyWhileHeld(new TimTap());
		RobotMap.HumanInput.Operator.stick.button12.whenPressed(new SingleOp() {
			@Override
			protected void runOp() {
				RobotMap.Component.ballLoadSensor.resetCalibration();
			}
		});
	}
}