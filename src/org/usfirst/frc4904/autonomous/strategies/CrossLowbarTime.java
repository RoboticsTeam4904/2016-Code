package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.AutonomousMoveTime;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.TimSet;
import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowbarTime extends CommandGroup {
	public CrossLowbarTime(Chassis chassis, boolean usePID) {
		addSequential(new TimSet(Tim.TimState.FULL_DOWN, true)); // Get Tim to low-bar position
		addParallel(new TimSet(Tim.TimState.FULL_DOWN, false)); // Keep Tim at low-bar position forever (until teleop)
		addSequential(new AutonomousMoveTime(chassis, RobotMap.Constant.AutonomousMetric.SPEED_LOWBAR, RobotMap.Constant.AutonomousMetric.TIME_LOWBAR, usePID));
	}
}
