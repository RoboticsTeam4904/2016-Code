package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.RobotMap.Component;
import org.usfirst.frc4904.robot.RobotMap.Constant;
import org.usfirst.frc4904.robot.commands.TimSet;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.Util;
import org.usfirst.frc4904.standard.custom.motioncontrollers.MotionController;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.motor.PositionEncodedMotor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.AccelerationCap;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.LinearModifier;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.SpeedModifierGroup;
import edu.wpi.first.wpilibj.SpeedController;

public class Tim extends PositionEncodedMotor {
	public enum TimState {
		// TODO determine actual value for DRAWBRIDGE, DRAWBRIDGE_TAP, CDF, and INTAKE
		FULL_UP(50), DEFAULT(TimState.FULL_UP.position), INTAKE(2016), LOWBAR(2000), DRAWBRIDGE(2000), DRAWBRIDGE_TAP(1900), CDF(2000), FULL_DOWN(2150);
		public final double position;
		
		private TimState(double position) {
			this.position = position;
		}
	}
	protected final CustomEncoder encoder;
	protected final Util.Range range;
	
	public Tim(MotionController motionController, CustomEncoder encoder, SpeedController... motors) {
		super("Tim", new SpeedModifierGroup(new LinearModifier(Constant.HumanInput.DEFENSE_MANIPULATOR_SPEED_SCALE), new AccelerationCap(Component.pdp)), motionController, motors);
		this.encoder = encoder;
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
