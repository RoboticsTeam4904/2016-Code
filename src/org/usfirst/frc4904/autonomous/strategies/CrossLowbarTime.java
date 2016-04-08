package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.AutonomousMoveTime;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.TimSet;
import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CrossLowbarTime extends CommandGroup {
	public CrossLowbarTime(Chassis chassis, boolean usePID) {
		addParallel(new RunAllSequential(new WaitCommand(0.5), new AutonomousMoveTime(chassis, RobotMap.Constant.AutonomousMetric.SPEED_LOWBAR, RobotMap.Constant.AutonomousMetric.TIME_LOWBAR, usePID)));
		addParallel(new TimSet(Tim.TimState.FULL_DOWN, false));
	}
}
