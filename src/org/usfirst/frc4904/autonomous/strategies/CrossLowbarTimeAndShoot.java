package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.AutonomousPositionToGoal;
import org.usfirst.frc4904.robot.commands.shooter.HoodDown;
import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowbarTimeAndShoot extends CommandGroup {
	public CrossLowbarTimeAndShoot(Chassis chassis, Camera camera, boolean usePID) {
		addParallel(new HoodDown());
		addSequential(new CrossLowbarTime(chassis, usePID));
		addSequential(new AutonomousPositionToGoal(chassis, camera, usePID));
		addSequential(new Shoot());
	}
}
