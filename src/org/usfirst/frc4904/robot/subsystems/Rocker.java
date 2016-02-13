package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.shooter.RockToIntake;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Rocker extends Subsystem {
	public enum RockerPosition {
		INTAKE(RobotMap.Constant.ROCKER_INTAKE_ANGLE), SHOOT(RobotMap.Constant.ROCKER_SHOOT_ANGLE);
		public final int angle; // the architecture allowing the enum states to have values
		
		private RockerPosition(int angle) {
			this.angle = angle;
		}
	}
	protected RockerPosition currentPosition;
	protected final Servo rockerServo;
	
	public Rocker(Servo rockerServo) {
		super("Rocker");
		this.rockerServo = rockerServo;
		set(RockerPosition.INTAKE);
	}
	
	public RockerPosition getPosition() {
		return currentPosition;
	}
	
	public boolean isInIntakePosition() {
		return currentPosition == RockerPosition.INTAKE;
	}
	
	public boolean isInShootPosition() {
		return currentPosition == RockerPosition.SHOOT;
	}
	
	public void set(RockerPosition desiredPosition) {
		if (desiredPosition == currentPosition) {
			return;
		}
		rockerServo.setAngle(desiredPosition.angle);
		currentPosition = desiredPosition;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RockToIntake(this));
	}
}
