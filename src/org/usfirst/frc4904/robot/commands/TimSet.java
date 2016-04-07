package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.commands.motor.MotorPositionConstant;

public class TimSet extends MotorPositionConstant {
	public TimSet(Tim.TimState state, boolean endOnArrival) {
		super(RobotMap.Component.tim, state.position, endOnArrival);
	}
	
	public TimSet(Tim.TimState state) {
		super(RobotMap.Component.tim, state.position);
	}
	
	@Override
	protected void execute() {}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		RobotMap.Component.tim.set(Tim.TimState.FULL_UP.position);
	}
	
	@Override
	protected void interrupted() {
		end();
	}
}
