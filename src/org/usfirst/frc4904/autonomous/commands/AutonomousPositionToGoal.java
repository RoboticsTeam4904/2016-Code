package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonomousPositionToGoal extends CommandGroup {
	public AutonomousPositionToGoal(Chassis chassis, Camera camera, boolean usePID) {
		addParallel(new PrintCommand("Starting AutonomousPositionToGoal"));
		addSequential(new GoalFind(chassis, camera, RobotMap.Constant.AutonomousMetric.SEARCH_SPEED, usePID));
		addParallel(new PrintCommand("Goal Found"));
		addSequential(new WaitCommand(RobotMap.Constant.AutonomousMetric.AUTONOMOUS_POSITION_TO_GOAL_DELAY_AFTER_CAMERA_SIGHTING));
		addParallel(new GoalAlign(chassis, camera, RobotMap.Constant.AutonomousMetric.DRIVE_SPEED, RobotMap.Constant.AutonomousMetric.ALIGN_SPEED));
		addSequential(new PrintCommand("Goal Aligned"));
	}
}