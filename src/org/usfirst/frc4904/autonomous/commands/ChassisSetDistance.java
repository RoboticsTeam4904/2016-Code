package org.usfirst.frc4904.autonomous.commands;

import java.util.ArrayList;

import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.motor.MotorSet;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import org.usfirst.frc4904.standard.subsystems.motor.EncodedMotor;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;

import edu.wpi.first.wpilibj.command.Command;

public class ChassisSetDistance extends Command{
	ArrayList<CustomEncoder> motorEncoders = new ArrayList<CustomEncoder>();
	Chassis chassis;
	double distance;
	double[] motorSpeeds;
	ArrayList<MotorSet> motorSetList;
	double speed;
	
	public ChassisSetDistance(Chassis chassis, double distance, double speed){
		 LogKitten.v("Moving Chassis " + distance + ". ");
		 this.chassis = chassis;
		 this.distance = distance;
		 this.speed = speed;
		 motorSetList = new ArrayList<MotorSet>();
		 for(Motor motor : chassis.getMotors()) {
			 if(motor instanceof EncodedMotor) {
				 motorEncoders.add(((EncodedMotor) motor).getEncoder());
			 } else {
				 motorSetList.add(new MotorSet(motor));
			 }
		 }
		 this.motorSpeeds = chassis.getMotorSpeeds();
	}

	@Override
	protected void initialize() {
		LogKitten.v("Chassis is moving distance.");
	}

	@Override
	protected void execute() {
		chassis.move2dc(0, speed, 0);
		String motorSpeedString = "";
		for(int i = 0; i < motorSpeeds.length; i++) {
			motorSetList.get(i).set(motorSpeeds[i]);
			motorSpeedString.concat(" " + motorSpeeds[i]);
		}
		LogKitten.v("Chassis Motor Speeds:");
	}

	@Override
	protected boolean isFinished() {
		double distanceSum = 0.0;
		for(CustomEncoder encoder : motorEncoders) {
			distanceSum += encoder.getDistance();
		}
		return distance < (distanceSum / motorEncoders.size());
		
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
	
	
	
}
