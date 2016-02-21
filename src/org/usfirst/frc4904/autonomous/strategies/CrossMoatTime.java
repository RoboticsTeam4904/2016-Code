package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.AutonomousMoveTime;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossMoatTime extends CommandGroup {
	public CrossMoatTime(Chassis chassis, boolean usePID) {
		addSequential(new AutonomousMoveTime(chassis, RobotMap.Constant.AutonomousMetric.DRIVE_SPEED, RobotMap.Constant.AutonomousMetric.TIME_MOAT, usePID));
	}
}
