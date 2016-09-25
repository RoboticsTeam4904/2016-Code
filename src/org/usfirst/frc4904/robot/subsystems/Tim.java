package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.TimSet;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.Util;
import org.usfirst.frc4904.standard.custom.motioncontrollers.MotionController;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import org.usfirst.frc4904.standard.subsystems.motor.PositionEncodedMotor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.AccelerationCap;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.LinearModifier;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.SpeedModifierGroup;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;

public class Tim extends PositionEncodedMotor {
	public enum TimState {
		FULL_UP(75), FULL_DOWN(1740);
		public final double position;
		
		private TimState(double position) {
			this.position = position;
		}
	}
	protected final CustomEncoder encoder;
	protected final Util.Range range;
	public final Motor intakeMotor;
	
	public Tim(MotionController motionController, CustomEncoder encoder, SpeedController intakeMotor, SpeedController... motors) {
		super("Tim", new SpeedModifierGroup(new LinearModifier(RobotMap.Constant.HumanInput.DEFENSE_MANIPULATOR_SPEED_SCALE), new AccelerationCap(RobotMap.Component.pdp)), motionController, motors);
		this.encoder = encoder;
		this.intakeMotor = new Motor("Tim Intake Motor", intakeMotor);
		this.intakeMotor.setInverted(true);
		range = new Util.Range(TimState.FULL_UP.position, TimState.FULL_DOWN.position);
	}
	
	@Override
	public void setPosition(double position) {
		double safePosition = range.limitValue(position);
		super.setPosition(safePosition);
	}
	
	public void setPosition(Tim.TimState state) {
		setPosition(state.position);
	}
	
	@Override
	public void set(double speed) {
		if (encoder.getDistance() > Tim.TimState.FULL_DOWN.position && speed < 0) {
			LogKitten.w("Tim overshoot");
			super.set(0);
			return;
		}
		super.set(speed);
	}
	
	public void setOverride(double speed) {
		super.set(speed);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TimSet(Tim.TimState.FULL_UP));
	}
	
	@Override
	public Command getDefaultCommand() {
		return (new TimSet(Tim.TimState.FULL_UP));
	}
}
