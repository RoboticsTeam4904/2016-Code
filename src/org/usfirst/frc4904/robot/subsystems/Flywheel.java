package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.custom.motioncontrollers.MotionController;
import org.usfirst.frc4904.standard.subsystems.motor.VelocitySensorMotor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.SpeedModifier;
import edu.wpi.first.wpilibj.SpeedController;

public class Flywheel extends VelocitySensorMotor {
	public enum FlywheelStatus {
		IDLE, SPINNING_UP, AT_SPEED
	}
	protected FlywheelStatus currentStatus;
	protected double targetSpeed = 0.0;
	
	public Flywheel(SpeedModifier speedModifier, MotionController motionController, SpeedController... motors) {
		super("Flywheel", speedModifier, motionController, motors);
		currentStatus = FlywheelStatus.IDLE;
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
		super.enableMotionController();
		super.set(targetSpeed);
		currentStatus = FlywheelStatus.SPINNING_UP;
	}
	
	public boolean isSpunUp() {
		if (currentStatus == FlywheelStatus.IDLE) {
			return false;
		}
		if (motionController.onTarget()) {
			currentStatus = FlywheelStatus.AT_SPEED;
			return true;
		}
		currentStatus = FlywheelStatus.SPINNING_UP;
		return false;
	}
	
	public void spinDown() {
		super.disableMotionController();
		super.set(0.0);
		targetSpeed = 0.0;
		currentStatus = FlywheelStatus.IDLE;
	}
}
