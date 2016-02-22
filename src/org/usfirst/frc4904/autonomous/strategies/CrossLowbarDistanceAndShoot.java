package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.GoalAlign;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowbarDistanceAndShoot extends CommandGroup {
	public CrossLowbarDistanceAndShoot(Chassis chassis, boolean usePID) {
		addSequential(new CrossLowbarDistance(chassis, usePID));
		addSequential(new GoalAlign(chassis, RobotMap.Component.cameraIR, usePID));
		addSequential(new Shoot());
		// addParallel(new HoodUp());
		// TODO: Align with goal
		// addParallel(new SpinUpFlywheel());
		// addSequential(new WaitCommand(1));
	}
}
