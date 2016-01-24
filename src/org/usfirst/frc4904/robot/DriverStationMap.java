package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;

public class DriverStationMap {
	// *** CONSTANTS *** //
	// Joystick/controller ports
	public static final int JOYSTICK_PORT = 0;
	public static final int XBOX_CONTROLLER_PORT = 1;
	// OI constants
	public static final double X_SPEED_SCALE = 1; // negative because axis is flipped
	public static final double Y_SPEED_SCALE = 1;
	public static final double TURN_SPEED_SCALE = 1;
	public static final double XBOX_MINIMUM_THRESHOLD = 0.1;
	public static final double SPEED_GAIN = 1;
	public static final double SPEED_EXP = 2;
	public static final double TURN_GAIN = 1;
	public static final double TURN_EXP = 2;
	// *** DRIVER *** //
	// Initialize driver Xbox controller
	public static CustomXbox xbox;
	// *** OPERATOR *** //
	// Initialize operator joystick
	public static CustomJoystick stick;
	
	// Initialize operator buttons
	public DriverStationMap() {
		xbox = new CustomXbox(XBOX_CONTROLLER_PORT);
		stick = new CustomJoystick(JOYSTICK_PORT);
		// Initialize driver Xbox controller and buttons
		DriverStationMap.xbox.rightStick.setXDeadZone(DriverStationMap.XBOX_MINIMUM_THRESHOLD);
		DriverStationMap.xbox.rightStick.setYDeadZone(DriverStationMap.XBOX_MINIMUM_THRESHOLD);
		DriverStationMap.xbox.leftStick.setXDeadZone(DriverStationMap.XBOX_MINIMUM_THRESHOLD);
		DriverStationMap.xbox.leftStick.setYDeadZone(DriverStationMap.XBOX_MINIMUM_THRESHOLD);
	}
}