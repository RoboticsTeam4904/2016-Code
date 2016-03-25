package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.AutonomousMoveTime;
import org.usfirst.frc4904.autonomous.commands.TimLowbar;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.shooter.HoodDown;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CrossMoatTime extends CommandGroup {
	public CrossMoatTime(Chassis chassis, boolean usePID) {
		addParallel(new HoodDown());
		addParallel(new RunAllSequential(new WaitCommand(0.5), new AutonomousMoveTime(chassis, RobotMap.Constant.AutonomousMetric.SPEED_MOAT, RobotMap.Constant.AutonomousMetric.TIME_MOAT, usePID)));
		addParallel(new TimLowbar(RobotMap.Component.tim, RobotMap.Component.timEncoder));
	}
}
