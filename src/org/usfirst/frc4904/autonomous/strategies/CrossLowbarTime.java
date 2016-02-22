package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.AutonomousMoveTime;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowbarTime extends CommandGroup {
	public CrossLowbarTime(Chassis chassis, boolean usePID) {
		addSequential(new AutonomousMoveTime(chassis, RobotMap.Constant.AutonomousMetric.SPEED_LOWBAR, RobotMap.Constant.AutonomousMetric.TIME_LOWBAR, usePID));
	}
}
