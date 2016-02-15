package org.usfirst.frc4904.autonomous.commands;

import java.util.ArrayList;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.motor.MotorEncoderSet;
import org.usfirst.frc4904.standard.commands.motor.MotorSet;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import org.usfirst.frc4904.standard.subsystems.motor.EncodedMotor;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ChassisSetDistance extends CommandGroup{
	
	protected CustomEncoder[] motorEncoders;
	protected MotorSet[] motorSetCommands;
	protected Chassis chassis;
	protected double distance;
	protected double speed;
	
	public ChassisSetDistance(Chassis chassis, double distance, double speed, boolean shouldUseEncode){
		requires(chassis);
		this.chassis = chassis;
		this.distance = distance;
		this.speed = speed;
		Motor[] chassisMotors = chassis.getMotors();
		motorSetCommands = new MotorEncoderSet[chassisMotors.length];
		motorEncoders = new CustomEncoder[chassisMotors.length];
		for(int i = 0; i < chassisMotors.length; i++) {
			if(chassisMotors[i] instanceof EncodedMotor && shouldUseEncode) {
				EncodedMotor encodedMotor = (EncodedMotor) chassisMotors[i];
				motorSetCommands[i] = new MotorEncoderSet(encodedMotor);
				motorEncoders[i] = encodedMotor.getEncoder();
			}
			addParallel(motorSetCommands[i]);
		}
	}

	public void execute() {
		chassis.move2dc(0.0, speed, 0.0);
		double[] chassisMotorSpeeds = chassis.getMotorSpeeds();
		for(int i = 0; i < motorSetCommands.length; i++) {
			motorSetCommands[i].set(chassisMotorSpeeds[i]);
		}
	}

	public boolean isFinished() {
		double distanceSum = 0;
		for (int i = 0; i < motorEncoders.length; i++) {
			distanceSum += motorEncoders[i].getDistance();
		}
		double distanceAvg = distanceSum / motorEncoders.length;
		return distanceAvg > distance;
	}

}
