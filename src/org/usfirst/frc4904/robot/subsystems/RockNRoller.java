package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.shooter.RockNRollerIdle;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RockNRoller extends Subsystem {
	public enum RockerState {
		IDLE(0), SHOOT(RobotMap.Constant.ROCKNROLLER_SHOOT_SPEED), OUTTAKE(RobotMap.Constant.ROCKNROLLER_OUTTAKE_SPEED);
		public final double speed; // the architecture allowing the enum states to have values
		
		private RockerState(double speed) {
			this.speed = speed;
		}
	}
	protected RockerState currentState;
	protected final Motor motor;
	
	public RockNRoller(Motor motor) {
		super("RockNRoller");
		this.motor = motor;
		set(RockerState.IDLE);
	}
	
	public RockerState getState() {
		return currentState;
	}
	
	public void set(RockerState desiredState) {
		if (desiredState == currentState) {
			return;
		}
		motor.set(desiredState.speed);
		currentState = desiredState;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RockNRollerIdle(this));
	}
}
