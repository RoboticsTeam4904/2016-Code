package org.usfirst.frc4904.robot;


public enum SmartDashboardKey {
	FLYWHEEL_STATE("Flywheel spun up"), HOOD_STATE("Hood up"), BALL_LOAD("Ball loaded");
	public final String key;
	
	private SmartDashboardKey(String key) {
		this.key = key;
	}
}
