package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.custom.sensors.IMU;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;

public class CrossDefenseIMU extends ChassisConstant {
	protected final IMU imu;
	protected boolean hasBegunCross;
	protected boolean hasFinishedCross;
	
	public CrossDefenseIMU(Chassis chassis, double speed, IMU imu) {
		super(chassis, 0, speed, 0, Double.MAX_VALUE);
		this.imu = imu;
		hasBegunCross = false;
		hasFinishedCross = false;
	}
	
	@Override
	protected void execute() {
		super.execute();
		if (imu.getPitch() > RobotMap.Constant.AutonomousMetric.DEFENSE_TILT) {
			hasBegunCross = true;
			return;
		}
		if (hasBegunCross && imu.getPitch() < -1.0 * RobotMap.Constant.AutonomousMetric.DEFENSE_TILT) {
			hasFinishedCross = true;
			return;
		}
	}
	
	@Override
	protected boolean isFinished() {
		return Math.abs(imu.getPitch()) < RobotMap.Constant.AutonomousMetric.DEFENSE_TILT && hasBegunCross && hasFinishedCross;
	}
}
