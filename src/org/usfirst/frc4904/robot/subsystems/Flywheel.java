package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.subsystems.motor.EncodedMotor;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Flywheel extends Subsystem {
	public enum FlywheelStatus {
		IDLE, SPINNING_UP, AT_SPEED
	}
	FlywheelStatus currentStatus;
	protected EncodedMotor wheelMotors;
	protected PIDController pid;
	double targetSpeed = 0.0;
	double SPEED_ERROR_TOLERANCE = 5; // 5% error
	
	public Flywheel(EncodedMotor wheelMotors) {
		super();
		this.wheelMotors = wheelMotors;
		this.currentStatus = FlywheelStatus.IDLE;
		pid = new PIDController(wheelMotors.getP(), wheelMotors.getI(), wheelMotors.getD(), wheelMotors.getEncoder(), wheelMotors);
		pid.setPercentTolerance(SPEED_ERROR_TOLERANCE);
	}
	
	public void setTargetSpeedForDistance(double distance) {
		targetSpeed = 0.7; // temporary magic number
		if (currentStatus != FlywheelStatus.IDLE) {
			wheelMotors.set(targetSpeed);
		}
	}
	
	public void spinUp() {
		pid.enable();
		wheelMotors.set(targetSpeed);
		currentStatus = FlywheelStatus.SPINNING_UP;
	}
	
	public boolean isSpunUp() {
		if (currentStatus == FlywheelStatus.IDLE) return false;
		if (pid.onTarget()) {
			currentStatus = FlywheelStatus.AT_SPEED;
			return true;
		}
		currentStatus = FlywheelStatus.SPINNING_UP;
		return false;
	}
	
	public void spinDown() {
		pid.disable();
		wheelMotors.set(0.0);
		targetSpeed = 0.0;
		currentStatus = FlywheelStatus.IDLE;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
