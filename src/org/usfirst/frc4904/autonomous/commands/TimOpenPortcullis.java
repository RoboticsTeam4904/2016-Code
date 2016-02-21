package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.commands.motor.MotorPositionConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimOpenPortcullis extends CommandGroup {
	public TimOpenPortcullis() {
		addSequential(new MotorPositionConstant(RobotMap.Component.tim, Tim.TIM_FULL_DOWN));
	}
}
