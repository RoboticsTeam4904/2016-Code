package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.custom.motioncontrollers.MotionController;
import org.usfirst.frc4904.standard.subsystems.motor.VelocityEncodedMotor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.AccelerationCap;
import edu.wpi.first.wpilibj.SpeedController;

public class Innie extends VelocityEncodedMotor {
	public Innie(MotionController motionController, SpeedController... motors) {
		super("Innie", new AccelerationCap(RobotMap.Component.pdp), motionController, motors);
	}
}
