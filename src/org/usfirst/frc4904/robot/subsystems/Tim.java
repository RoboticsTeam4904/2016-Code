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

public class Tim extends PositionEncodedMotor {
	public enum TimState {
		// TODO determine actual value for DRAWBRIDGE, DRAWBRIDGE_TAP, and CDF
		FULL_UP(50), FULL_DOWN(1800), DEFAULT(TimState.FULL_UP.position), INTAKE(TimState.FULL_DOWN.position), LOWBAR(TimState.FULL_DOWN.position), DRAWBRIDGE(TimState.FULL_DOWN.position), DRAWBRIDGE_TAP(TimState.DRAWBRIDGE.position - 200), CDF(TimState.FULL_DOWN.position),;
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
		this.intakeMotor = new Motor(intakeMotor);
		range = new Util.Range(TimState.FULL_DOWN.position, TimState.FULL_UP.position);
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
		setDefaultCommand(new TimSet(Tim.TimState.DEFAULT));
	}
}
