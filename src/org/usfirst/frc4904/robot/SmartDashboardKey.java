package org.usfirst.frc4904.robot;


public enum SmartDashboardKey {
	SHOOT_READY("Shoot!"), BATTER_END_OF_MATCH_TURN("20 seconds left"), FLYWHEEL_STATE("Flywheel spun up"), HOOD_STATE("Hood up"), BALL_LOAD("Ball loaded"), TIM("Tim"), ANGLE_OFF_GOAL("Angle off Goal"), IN_RANGE("In Range"), DISTANCE_FROM_GOAL("Distance From Goal"), ANGLE_WITHIN_FIVE("Angle less than Five"), AUTON_ROUTINE("Auton Routine"), SUBSYSTEM_SUMMARY("Subsystem summary"), ALIGN_P("Align P"), ALIGN_I("Align I"), ALIGN_D("Align D"), ALIGN_E("Align E"), ALIGN_SETPOINT("Align Setpoint");
	public final String key;
	
	private SmartDashboardKey(String key) {
		this.key = key;
	}
}
