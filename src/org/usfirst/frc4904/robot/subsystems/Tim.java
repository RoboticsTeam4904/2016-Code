package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.RobotMap.Component;
import org.usfirst.frc4904.robot.RobotMap.Constant;
import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.custom.motioncontrollers.MotionController;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.motor.PositionEncodedMotor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.AccelerationCap;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.LinearModifier;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.SpeedModifierGroup;
import edu.wpi.first.wpilibj.SpeedController;

public class Tim extends PositionEncodedMotor {
	public static final double TIM_FULL_UP = 50;
	public static final double TIM_FULL_DOWN = 2150;
	protected final CustomEncoder encoder;
	
	public Tim(MotionController motionController, CustomEncoder encoder, SpeedController... motors) {
		super("Tim", new SpeedModifierGroup(new LinearModifier(Constant.HumanInput.DEFENSE_MANIPULATOR_SPEED_SCALE), new AccelerationCap(Component.pdp)), motionController, motors);
		this.encoder = encoder;
	}
	
	@Override
	public void setPosition(double position) {
		double safePosition = Math.max(Math.min(position, Tim.TIM_FULL_DOWN), Tim.TIM_FULL_UP);
		super.setPosition(safePosition);
	}
	
	@Override
	public void set(double speed) {
		if ((encoder.getDistance() < Tim.TIM_FULL_UP && speed < 0) || (encoder.getDistance() > Tim.TIM_FULL_DOWN && speed > 0)) {
			super.set(0); // TODO This is bad. No.
			return;
		}
		super.set(speed);
	}
	
	public void setOverride(double speed) {
		super.set(speed);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}
