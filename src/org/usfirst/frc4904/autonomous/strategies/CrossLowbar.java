package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.ChassisSetDistance;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.shooter.HoodDown;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowbar extends CommandGroup {
	public CrossLowbar(Chassis chassis, boolean usePID) {
		double tickDistance = (RobotMap.Constant.FieldMetric.DISTANCE_TO_LOW_BAR / RobotMap.Constant.RobotMetric.WHEEL_CIRCUMFERENCE) * RobotMap.Constant.RobotMetric.WHEEL_ENCODER_PPR;
		addSequential(new HoodDown(RobotMap.Component.hood));
		addParallel(new ChassisSetDistance(chassis, tickDistance, RobotMap.Constant.AutonomousMetric.DRIVE_SPEED, usePID, new CustomEncoder[] {RobotMap.Component.leftEncoder, RobotMap.Component.rightEncoder}));
	}
}
