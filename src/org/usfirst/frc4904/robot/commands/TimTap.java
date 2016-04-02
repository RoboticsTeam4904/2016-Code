package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.motor.MotorPositionConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimTap extends CommandGroup {
	public TimTap() {
		addSequential(new MotorPositionConstant(RobotMap.Component.tim, Tim.TIM_DRAWBRIDGE, true));
		addSequential(new RunFor(new MotorPositionConstant(RobotMap.Component.tim, Tim.TIM_DRAWBRIDGE_TAP, true), RobotMap.Constant.TIMTAP_DURATION));
		addSequential(new MotorPositionConstant(RobotMap.Component.tim, Tim.TIM_DRAWBRIDGE, true));
	}
}
