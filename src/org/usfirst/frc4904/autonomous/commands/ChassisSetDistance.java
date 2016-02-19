package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.motor.MotorSet;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import org.usfirst.frc4904.standard.subsystems.motor.SensorMotor;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ChassisSetDistance extends CommandGroup {
	protected CustomEncoder[] motorEncoders;
	protected MotorSet[] motorSetCommands;
	protected Chassis chassis;
	protected double distance;
	protected double speed;
	
	public ChassisSetDistance(Chassis chassis, double distance, double speed, boolean usePID, CustomEncoder... customEncoders) {
		requires(chassis);
		this.chassis = chassis;
		this.distance = distance;
		this.speed = speed;
		Motor[] chassisMotors = chassis.getMotors();
		motorSetCommands = new MotorSet[chassisMotors.length];
		motorEncoders = customEncoders;
		for (int i = 0; i < chassisMotors.length; i++) {
			if (chassisMotors[i] instanceof SensorMotor) {
				if (usePID) {
					((SensorMotor) chassisMotors[i]).enablePID();
				} else {
					((SensorMotor) chassisMotors[i]).disablePID();
				}
			}
			motorSetCommands[i] = new MotorSet(chassisMotors[i]);
			addParallel(motorSetCommands[i]);
		}
	}
	
	@Override
	public void execute() {
		chassis.move2dc(0.0, speed, 0.0);
		double[] chassisMotorSpeeds = chassis.getMotorSpeeds();
		for (int i = 0; i < motorSetCommands.length; i++) {
			motorSetCommands[i].set(chassisMotorSpeeds[i]);
		}
	}
	
	@Override
	public boolean isFinished() {
		double distanceSum = 0;
		for (int i = 0; i < motorEncoders.length; i++) {
			distanceSum += motorEncoders[i].getDistance();
			LogKitten.wtf("Encoder " + i + " reads " + motorEncoders[i].getDistance() + "out of " + distance);
		}
		double distanceAvg = distanceSum / motorEncoders.length;
		return distanceAvg > distance;
	}
}
