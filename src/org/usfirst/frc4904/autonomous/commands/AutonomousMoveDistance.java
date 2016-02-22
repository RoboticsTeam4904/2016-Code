package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousMoveDistance extends CommandGroup {
	public AutonomousMoveDistance(Chassis chassis, double distance, double speed, boolean usePID, CustomEncoder... motorEncoders) {
		addSequential(new ChassisSetDistance(chassis, distance, speed, usePID, motorEncoders));
		addSequential(new ChassisConstant(chassis, 0.0, RobotMap.Constant.AutonomousMetric.BURST_SPEED, 0.0, RobotMap.Constant.AutonomousMetric.BURST_TIME));
	}
}
