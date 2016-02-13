package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.Idle;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Flywheel extends Subsystem {
	public enum FlywheelStatus {
		IDLE, SPINNING_UP, AT_SPEED
	}
	protected FlywheelStatus currentStatus;
	// protected final EncodedMotor wheelMotors;
	protected double targetSpeed = 0.0;
	
	public Flywheel(/* EncodedMotor wheelMotors */) {
		super("Flywheel");
		// this.wheelMotors = wheelMotors;
		this.currentStatus = FlywheelStatus.IDLE;
		// wheelMotors.getPIDController().setPercentTolerance(RobotMap.Constant.FLYWHEEL_PERCENT_TOLERANCE);
	}
	
	public FlywheelStatus getStatus() {
		return currentStatus;
	}
	
	public void setTargetSpeedForDistance(double distance) {
		targetSpeed = 0.7; // temporary magic number
		if (currentStatus != FlywheelStatus.IDLE) {
			// wheelMotors.set(targetSpeed);
		}
	}
	
	public void spinUp() {
		// wheelMotors.enablePID();
		// wheelMotors.set(targetSpeed);
		currentStatus = FlywheelStatus.SPINNING_UP;
	}
	
	public boolean isSpunUp() {
		if (currentStatus == FlywheelStatus.IDLE) {
			return false;
		}
		/*
		 * if (wheelMotors.getPIDController().onTarget()) {
		 * currentStatus = FlywheelStatus.AT_SPEED;
		 * return true;
		 * }
		 */
		currentStatus = FlywheelStatus.SPINNING_UP;
		return false;
	}
	
	public void spinDown() {
		// wheelMotors.disablePID();
		// wheelMotors.set(0.0);
		targetSpeed = 0.0;
		currentStatus = FlywheelStatus.IDLE;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}
