package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimIntake extends CommandGroup {
	public TimIntake() {
		super("TimIntake");
		addParallel(new MotorConstant(RobotMap.Component.tim.intakeMotor, RobotMap.Constant.TIM_INTAKE_SPEED));
		addParallel(new TimSet(Tim.TimState.INTAKE, false));
	}
}
