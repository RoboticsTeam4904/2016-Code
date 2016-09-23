package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;

public class TimSpin extends MotorConstant {
	public TimSpin(boolean intake) {
		super(RobotMap.Component.tim.intakeMotor, intake ? RobotMap.Constant.TIM_INTAKE_SPEED : RobotMap.Constant.TIM_OUTTAKE_SPEED);
	}
}
