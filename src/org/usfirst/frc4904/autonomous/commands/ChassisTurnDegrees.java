package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.commands.motor.MotorSet;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;

public strictfp class ChassisTurnDegrees extends ChassisConstant {
	protected double robotLength;
	protected double robotWidth;
	protected double encoderPPR;
	protected double wheelSize;
	protected double speed;
	protected double needsToTravel;
	protected CustomEncoder[] motorEncoders;
	protected MotorSet[] motorSetCommands;
	protected Chassis chassis;
	
	public ChassisTurnDegrees(Chassis chassis, double robotLength, double robotWidth, double encoderPPR, double wheelSize, int degrees, double speed, boolean usePID, CustomEncoder... customEncoders) {
		super(chassis, 0.0, 0.0, speed, Double.MAX_VALUE);
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
		needsToTravel = distancePerDegree * degrees;
	}
	
	@Override
	public boolean isFinished() {
		double distanceSum = 0;
		for (int i = 0; i < motorEncoders.length; i++) {
			distanceSum += motorEncoders[i].getDistance();
		}
		double distanceAvg = distanceSum / motorEncoders.length;
		return distanceAvg > needsToTravel;
	}
}