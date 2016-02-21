package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.CrossDefenseIMU;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.shooter.HoodDown;
import org.usfirst.frc4904.standard.custom.sensors.IMU;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowbarIMU extends CommandGroup {
	public CrossLowbarIMU(Chassis chassis, IMU imu) {
		addParallel(new HoodDown());
		RobotMap.Component.leftWheelEncoder.reset();
		RobotMap.Component.rightWheelEncoder.reset();
		addSequential(new CrossDefenseIMU(chassis, RobotMap.Constant.AutonomousMetric.LOWBAR_DRIVE_SPEED, imu));
	}
}
