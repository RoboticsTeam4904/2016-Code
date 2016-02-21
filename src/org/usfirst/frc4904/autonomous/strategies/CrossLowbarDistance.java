package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.AutonomousMoveDistance;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.shooter.HoodDown;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowbarDistance extends CommandGroup {
	public CrossLowbarDistance(Chassis chassis, boolean usePID) {
		double tickDistance = (RobotMap.Constant.FieldMetric.DISTANCE_TO_LOW_BAR / RobotMap.Constant.RobotMetric.WHEEL_CIRCUMFERENCE) * RobotMap.Constant.RobotMetric.WHEEL_ENCODER_PPR;
		addParallel(new HoodDown());
		RobotMap.Component.leftWheelEncoder.reset();
		RobotMap.Component.rightWheelEncoder.reset();
		addSequential(new AutonomousMoveDistance(chassis, tickDistance, RobotMap.Constant.AutonomousMetric.LOWBAR_DRIVE_SPEED, usePID, new CustomEncoder[] {RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder}));
	}
}
