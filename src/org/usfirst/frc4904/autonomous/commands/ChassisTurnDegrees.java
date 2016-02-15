package org.usfirst.frc4904.autonomous.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.motor.MotorEncoderSet;
import org.usfirst.frc4904.standard.commands.motor.MotorSet;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import org.usfirst.frc4904.standard.subsystems.motor.EncodedMotor;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ChassisTurnDegrees extends CommandGroup {
	
	protected double robotLength;
	protected double robotWidth;
	protected double encoderPPR;
	protected double wheelSize;
	protected double speed;
	protected double needsToTravel;
//	protected CustomEncoder[] leftCustomEncoders;
//	protected CustomEncoder[] rightCustomEncoders;
//	protected MotorSet[] leftMotorSetCommands;
//	protected MotorSet[] rightMotorSetCommands;
	protected CustomEncoder[] motorEncoders;
	protected MotorSet[] motorSetCommands;
	protected Chassis chassis;
	
	public ChassisTurnDegrees(Chassis chassis, Motor[] leftEncodedMotors, Motor[] rightEncodedMotors, double robotLength, double robotWidth, double encoderPPR, double wheelSize, int degrees, double speed, boolean shouldUseEncode) {
		requires(chassis);
		this.chassis = chassis;
		this.robotLength = robotLength;
		this.robotWidth = robotWidth;
		this.encoderPPR = encoderPPR;
		this.wheelSize = wheelSize;
		this.speed = speed;
		double radius = (Math.sqrt(Math.pow(robotLength, 2) + Math.pow(robotWidth, 2))) / 2;
		double circumference = 2 * radius * Math.PI;
		double ticksPerCircumference = (circumference / wheelSize) * encoderPPR;
		double distancePerDegree = ticksPerCircumference / 360;
		this.needsToTravel = distancePerDegree * degrees;

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
		
/**		ArrayList<Motor> chassisMotors = new ArrayList<Motor>(Arrays.asList(chassis.getMotors()));
		leftMotorSetCommands = new MotorSet[leftEncodedMotors.length];
		rightMotorSetCommands = new MotorSet[rightEncodedMotors.length];
		leftCustomEncoders = new CustomEncoder[leftEncodedMotors.length];
		rightCustomEncoders = new CustomEncoder[rightEncodedMotors.length];

		for(int i = 0; i < leftEncodedMotors.length; i++){
			if(chassisMotors.contains(leftEncodedMotors[i])) {
				if(leftEncodedMotors[i] instanceof EncodedMotor && shouldUseEncode) {
					EncodedMotor encodedMotor = (EncodedMotor) leftEncodedMotors[i];
					leftCustomEncoders[i] = encodedMotor.getEncoder();
					leftMotorSetCommands[i] = new MotorEncoderSet(encodedMotor);
				}
				addParallel(leftMotorSetCommands[i]);
			}
		}
		for(int i = 0; i < rightEncodedMotors.length; i++){
			if(chassisMotors.contains(leftEncodedMotors[i])) {
				if(rightEncodedMotors[i] instanceof EncodedMotor && shouldUseEncode) {
					EncodedMotor encodedMotor = (EncodedMotor) rightEncodedMotors[i];
					rightCustomEncoders[i] = encodedMotor.getEncoder();
					rightMotorSetCommands[i] = new MotorEncoderSet(encodedMotor);
				}
				addParallel(rightMotorSetCommands[i]);
			}
		}*/
	}
	
	public void execute() {
		chassis.move2dc(0.0, 0.0, speed);
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
		return distanceAvg > needsToTravel;
	}
	
}