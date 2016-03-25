package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousPositionToGoal extends CommandGroup {
	public AutonomousPositionToGoal(Chassis chassis, Camera camera, boolean usePID) {
		addSequential(new GoalFind(chassis, camera, RobotMap.Constant.AutonomousMetric.SEARCH_SPEED, RobotMap.Constant.Network.PI_IR_STATUS_INDEX_POSITION, RobotMap.Constant.Network.PI_IR_STATUS_GOOD, usePID));
		addSequential(new GoalAlign(chassis, camera, usePID));
	}
}