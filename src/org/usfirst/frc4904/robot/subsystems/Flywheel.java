package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.custom.motioncontrollers.MotionController;
import org.usfirst.frc4904.standard.subsystems.motor.VelocityEncodedMotor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.SpeedModifier;
import edu.wpi.first.wpilibj.SpeedController;

public class Flywheel extends VelocityEncodedMotor {
	public enum FlywheelStatus {
		IDLE, SPINNING_UP, AT_SPEED
	}
	protected FlywheelStatus currentStatus;
	protected double targetSpeed = 0.0;
	protected Long lastSpinUpStartEpoch = Long.MAX_VALUE;
	
	public Flywheel(SpeedModifier speedModifier, MotionController motionController, SpeedController... motors) {
		super("Flywheel", speedModifier, motionController, motors);
		currentStatus = FlywheelStatus.IDLE;
	}
	
	public FlywheelStatus getStatus() {
		return currentStatus;
	}
	
	public boolean isSpunUp() {
		return lastSpinUpStartEpoch != null && (System.currentTimeMillis() - lastSpinUpStartEpoch) >= RobotMap.Constant.HumanInput.TIME_MILLIS_SHOOT_READY_SPINUP_BUFFER;
	}
	
	public void setSpinUpEpoch() {
		lastSpinUpStartEpoch = System.currentTimeMillis();
	}
	
	public void discardSpinUpEpoch() {
		lastSpinUpStartEpoch = null;
	}
}
