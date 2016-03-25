package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousMoveTime extends CommandGroup {
	public AutonomousMoveTime(Chassis chassis, double speed, double time, boolean usePID) {
		addSequential(new ChassisConstant(chassis, 0.0, speed, 0.0, time));
		addSequential(new ChassisConstant(chassis, 0.0, RobotMap.Constant.AutonomousMetric.BURST_SPEED, 0.0, RobotMap.Constant.AutonomousMetric.BURST_TIME));
	}
}
