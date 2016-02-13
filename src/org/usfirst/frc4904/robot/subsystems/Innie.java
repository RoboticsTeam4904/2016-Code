package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.motor.sensormotor.EncodedMotor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.AccelerationCap;
import edu.wpi.first.wpilibj.CANTalon;

public class Innie extends EncodedMotor {
	public Innie(CANTalon motor, CustomEncoder encoder) {
		super("Innie", new AccelerationCap(RobotMap.Component.pdp), encoder, motor);
	}
}
