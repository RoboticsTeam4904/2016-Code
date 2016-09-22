package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.commands.motor.MotorPositionConstant;

public class TimSet extends MotorPositionConstant {
	protected final Tim.TimState state;
	
	public TimSet(Tim.TimState state, boolean endOnArrival) {
		super(RobotMap.Component.tim, state.position, endOnArrival);
		this.state = state;
	}
	
	public Tim.TimState getState() {
		return state;
	}
	
	/**
	 * @see #TimSet
	 *      endOnArrival defaults to true
	 */
	public TimSet(Tim.TimState state) {
		super(RobotMap.Component.tim, state.position);
		this.state = state;
	}
}
