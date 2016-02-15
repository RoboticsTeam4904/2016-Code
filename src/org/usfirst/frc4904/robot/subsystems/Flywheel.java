package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.subsystems.motor.VelocityEncodedMotor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.SpeedModifier;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;

public class Flywheel extends VelocityEncodedMotor {
	public enum FlywheelStatus {
		IDLE, SPINNING_UP, AT_SPEED
	}
	protected FlywheelStatus currentStatus;
	protected double targetSpeed = 0.0;
	
	public Flywheel(SpeedModifier slopeController, PIDSource sensor, SpeedController... motors) {
		super("Flywheel", slopeController, sensor, motors);
		this.currentStatus = FlywheelStatus.IDLE;
	}
	
	public FlywheelStatus getStatus() {
		return currentStatus;
	}
	
	public void setTargetSpeedForDistance(double distance) {
		targetSpeed = 0.7; // temporary magic number
		if (currentStatus != FlywheelStatus.IDLE) {
			super.set(targetSpeed);
		}
	}
	
	public void spinUp() {
		super.enablePID();
		super.set(targetSpeed);
		currentStatus = FlywheelStatus.SPINNING_UP;
	}
	
	public boolean isSpunUp() {
		if (currentStatus == FlywheelStatus.IDLE) {
			return false;
		}
		if (super.pid.onTarget()) {
			currentStatus = FlywheelStatus.AT_SPEED;
			return true;
		}
		currentStatus = FlywheelStatus.SPINNING_UP;
		return false;
	}
	
	public void spinDown() {
		super.disablePID();
		super.set(0.0);
		targetSpeed = 0.0;
		currentStatus = FlywheelStatus.IDLE;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}
