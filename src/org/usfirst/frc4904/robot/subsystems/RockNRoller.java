package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.shooter.RockNRollerIdle;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RockNRoller extends Subsystem {
	public enum RockerPosition {
		IDLE(0), SHOOT(RobotMap.Constant.ROCKNROLLER_SHOOT_SPEED), OUTTAKE(RobotMap.Constant.ROCKNROLLER_OUTTAKE_SPEED);
		public final double speed; // the architecture allowing the enum states to have values
		
		private RockerPosition(double speed) {
			this.speed = speed;
		}
	}
	protected RockerPosition currentPosition;
	protected final Motor motor;
	
	public RockNRoller(Motor motor) {
		super("RockNRoller");
		this.motor = motor;
		set(RockerPosition.IDLE);
	}
	
	public RockerPosition getPosition() {
		return currentPosition;
	}
	
	public void set(RockerPosition desiredPosition) {
		if (desiredPosition == currentPosition) {
			return;
		}
		motor.set(desiredPosition.speed);
		currentPosition = desiredPosition;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RockNRollerIdle(this));
	}
}
