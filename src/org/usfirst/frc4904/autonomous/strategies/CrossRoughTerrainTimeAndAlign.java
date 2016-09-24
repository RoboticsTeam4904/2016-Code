package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.AutonomousPositionToGoal;
import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossRoughTerrainTimeAndAlign extends CommandGroup {
	public CrossRoughTerrainTimeAndAlign(Chassis chassis, Camera camera, boolean usePID) {
		addSequential(new CrossRoughTerrainTime(chassis, usePID));
		addSequential(new AutonomousPositionToGoal(chassis, camera, usePID));
	}
}
