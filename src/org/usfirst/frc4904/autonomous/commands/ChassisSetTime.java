package org.usfirst.frc4904.autonomous.commands;

import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;

import edu.wpi.first.wpilibj.command.Command;

public class ChassisSetTime extends Command{

	private Chassis chassis;
	private double timeout;
	private double speed;
	
	public ChassisSetTime(Chassis chassis, double timeout, double speed) {
		this.chassis = chassis;
		this.timeout = timeout;
		this.speed = speed;
	}

	@Override
	protected void initialize() {
		setTimeout(timeout);
		chassis.move2dc(0, speed, 0);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		chassis.move2dc(0, 0, 0);
	}

	@Override
	protected void interrupted() {
		end();
	}

	
}
