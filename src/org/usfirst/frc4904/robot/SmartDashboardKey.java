package org.usfirst.frc4904.robot;


public enum SmartDashboardKey {
	ANGLE_OFF_GOAL("Angle off Goal"), //
	DISTANCE_FROM_GOAL("Distance From Goal"), //
	ANGLE_ALIGNED("Angle aligned"), //
	DISTANCE_ALIGNED("Distance aligned"), //
	SHOOT_READY("Shoot!"), //
	SUBSYSTEM_SUMMARY("Subsystem summary"); //
	public final String key;
	
	private SmartDashboardKey(String key) {
		this.key = key;
	}
}
