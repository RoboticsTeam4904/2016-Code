package org.usfirst.frc4904.robot.subsystems;


import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Rocker extends Subsystem {
	public enum RockerPosition {
		UNDEFINED, INTAKE, SHOOT
	}
	protected RockerPosition currentPosition;
	protected Servo rockerServo;
	protected double ANGLE_INTAKE = 0;
	protected double ANGLE_SHOOT = 90;
	
	public Rocker(Servo rockerServo) {
		super("Rocker");
		this.rockerServo = rockerServo;
		this.currentPosition = RockerPosition.UNDEFINED;
	}
	
	public void updateState() {
		double currentAngle = rockerServo.getAngle(); // TODO does this reflect actual or set angle?
		if (currentAngle <= ANGLE_INTAKE) {
			currentPosition = RockerPosition.INTAKE;
		}
		if (currentAngle >= ANGLE_SHOOT) {
			currentPosition = RockerPosition.SHOOT;
		}
		currentPosition = RockerPosition.UNDEFINED;
	}
	
	public boolean isInIntakePosition() {
		updateState();
		return currentPosition == RockerPosition.INTAKE;
	}
	
	public boolean isInShootPosition() {
		updateState();
		return currentPosition == RockerPosition.SHOOT;
	}
	
	public boolean isInUndefinedPosition() {
		updateState();
		return currentPosition == RockerPosition.UNDEFINED;
	}
	
	public void set(RockerPosition desiredPosition) {
		if (desiredPosition == currentPosition) {
			return;
		}
		if (desiredPosition == RockerPosition.INTAKE) {
			rockerServo.setAngle(ANGLE_INTAKE);
		}
		if (desiredPosition == RockerPosition.SHOOT) {
			rockerServo.setAngle(ANGLE_SHOOT);
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
