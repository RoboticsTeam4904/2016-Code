package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.commands.motor.MotorPositionConstant;

public class TimSet extends MotorPositionConstant {
	public TimSet(Tim.TimState state, boolean endOnArrival) {
		super(RobotMap.Component.tim, state.position, endOnArrival);
	}
	
	/**
	 * @see #TimSet
	 *      endOnArrival defaults to false
	 */
	public TimSet(Tim.TimState state) {
		super(RobotMap.Component.tim, state.position);
	}
}
