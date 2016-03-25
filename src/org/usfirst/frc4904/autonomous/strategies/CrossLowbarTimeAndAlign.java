package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.AutonomousPositionToGoal;
import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowbarTimeAndAlign extends CommandGroup {
	public CrossLowbarTimeAndAlign(Chassis chassis, Camera camera, boolean usePID) {
		addSequential(new CrossLowbarTime(chassis, usePID));
		addSequential(new AutonomousPositionToGoal(chassis, camera, usePID));
	}
}