package org.usfirst.frc4904.robot;


public enum SmartDashboardKey {
	SHOOT_READY("Shoot!"), BATTER_END_OF_MATCH_TURN("20 seconds left"), FLYWHEEL_STATE("Flywheel spun up"), ANGLE_OFF_GOAL("Angle off Goal"), IN_RANGE("In Range"), DISTANCE_FROM_GOAL("Distance From Goal"), ANGLE_ALIGNED("Angle aligned"), AUTON_ROUTINE("Auton Routine"), SUBSYSTEM_SUMMARY("Subsystem summary");
	public final String key;
	
	private SmartDashboardKey(String key) {
		this.key = key;
	}
}
