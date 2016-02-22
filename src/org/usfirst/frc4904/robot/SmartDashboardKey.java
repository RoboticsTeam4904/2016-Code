package org.usfirst.frc4904.robot;


public enum SmartDashboardKey {
	FLYWHEEL_STATE("Flywheel spun up"), HOOD_STATE("Hood up"), BALL_LOAD("Ball loaded"), ANGLE_OFF_GOAL("Angle off Goal"), IN_RANGE("In Range"), DISTANCE_FROM_GOAL("Distance From Goal");
	public final String key;
	
	private SmartDashboardKey(String key) {
		this.key = key;
	}
}
