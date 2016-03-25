package org.usfirst.frc4904.robot.sensors;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.custom.sensors.CANInfraredDistanceSensor;

public class BallLoadSensor extends CANInfraredDistanceSensor {
	private int calibrationTicksAccumulated = 0;
	private double calibrationRunningTotal = 0;
	private boolean calibrationCompleted = false;
	public static double EMPTY_STATE_LOWER_BOUND = Double.NEGATIVE_INFINITY;
	public static double EMPTY_STATE_UPPER_BOUND = Double.POSITIVE_INFINITY;
	
	public BallLoadSensor(String name, int id) {
		super(name, id);
	}
	
	public void calibrate() { // assumes that the first X ticks will occur without a loaded ball
		if (calibrationCompleted) {
			return;
		}
		if (calibrationTicksAccumulated >= RobotMap.Constant.BALL_LOAD_SENSOR_CALIBRATION_TICK_COUNT) {
			double averageEmptyValue = calibrationRunningTotal / calibrationTicksAccumulated;
			BallLoadSensor.EMPTY_STATE_LOWER_BOUND = averageEmptyValue - RobotMap.Constant.BALL_LOAD_EMPTY_STATE_ABSOLUTE_ERROR;
			BallLoadSensor.EMPTY_STATE_UPPER_BOUND = averageEmptyValue + RobotMap.Constant.BALL_LOAD_EMPTY_STATE_ABSOLUTE_ERROR;
			calibrationCompleted = true;
			LogKitten.v("BallLoadSensor calibration yielded: " + averageEmptyValue + "±" + RobotMap.Constant.BALL_LOAD_EMPTY_STATE_ABSOLUTE_ERROR);
			return;
		}
		calibrationRunningTotal += getDistance();
		++calibrationTicksAccumulated;
	}
	
	public void resetCalibration() {
		BallLoadSensor.EMPTY_STATE_LOWER_BOUND = Double.NEGATIVE_INFINITY;
		BallLoadSensor.EMPTY_STATE_UPPER_BOUND = Double.POSITIVE_INFINITY;
		calibrationTicksAccumulated = 0;
		calibrationRunningTotal = 0;
		calibrationCompleted = false;
		// calibrate will be re-run by Robot#TeleopPeriodic()
	}
}
