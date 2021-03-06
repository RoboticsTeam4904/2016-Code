package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowbarTimeAndShoot extends CommandGroup {
	public CrossLowbarTimeAndShoot(Chassis chassis, Camera camera, boolean usePID) {
		addSequential(new CrossLowbarTimeAndAlign(chassis, camera, usePID));
		addSequential(new Shoot());
	}
}
