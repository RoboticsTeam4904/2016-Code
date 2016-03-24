package org.usfirst.frc4904.robot;


public enum SmartDashboardKey {
	FLYWHEEL_STATE("Flywheel spun up"), HOOD_STATE("Hood up"), BALL_LOAD("Ball loaded"), TIM("Tim"), ANGLE_OFF_GOAL("Angle off Goal"), IN_RANGE("In Range"), DISTANCE_FROM_GOAL("Distance From Goal"), ANGLE_WITHIN_FIVE("Angle less than Five");
	public final String key;
	
	private SmartDashboardKey(String key) {
		this.key = key;
	}
}
