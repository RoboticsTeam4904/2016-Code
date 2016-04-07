package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;

public class TimIntake extends MotorConstant {
	public TimIntake() {
		super(RobotMap.Component.tim.intakeMotor, RobotMap.Constant.TIM_INTAKE_SPEED);
	}
}
