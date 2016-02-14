package org.usfirst.frc4904.autonomous.commands;

import java.util.ArrayList;

import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.motor.MotorSet;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import org.usfirst.frc4904.standard.subsystems.motor.EncodedMotor;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;

import edu.wpi.first.wpilibj.command.Command;

public class ChassisTurnDegrees extends Command {

	private Chassis chassis;
	private double distance;
	private double speed;
	
	public ChassisTurnDegrees(Chassis chassis, double distance, double speed) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	
	
}
