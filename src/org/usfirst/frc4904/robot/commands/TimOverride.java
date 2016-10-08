package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.commands.motor.MotorControl;
import org.usfirst.frc4904.standard.custom.controllers.Controller;

/**
 *
 */
public class TimOverride extends MotorControl {
	protected final Tim tim;
	
	public TimOverride(Tim tim, Controller controller, int axis, double scale) {
		super(tim, controller, axis, scale);
		this.tim = tim;
	}
	
	@Override
	protected void initialize() {
		tim.disablePID();
		super.initialize();
	}
	
	@Override
	protected void end() {
		super.end();
		tim.enablePID();
	}
	
	@Override
	protected void interrupted() {
		super.interrupted();
		tim.enablePID();
	}
}
