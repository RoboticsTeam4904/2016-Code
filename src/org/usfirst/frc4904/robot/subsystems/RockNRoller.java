package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.motor.MotorIdle;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.SpeedModifier;
import edu.wpi.first.wpilibj.SpeedController;

public class RockNRoller extends Motor {
	public enum RockerState {
		IDLE(0), SHOOT(RobotMap.Constant.ROCKNROLLER_SHOOT_SPEED), OUTTAKE(RobotMap.Constant.ROCKNROLLER_OUTTAKE_SPEED);
		public final double speed; // the architecture allowing the enum states to have values
		
		private RockerState(double speed) {
			this.speed = speed;
		}
	}
	protected RockerState currentState;
	
	public RockNRoller(String name, SpeedModifier slopeController, SpeedController... motors) {
		super(name, slopeController, motors);
		set(RockerState.IDLE);
	}
	
	public RockerState getState() {
		return currentState;
	}
	
	public void set(RockerState desiredState) {
		super.set(desiredState.speed);
		currentState = desiredState;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new MotorIdle(this));
	}
}
