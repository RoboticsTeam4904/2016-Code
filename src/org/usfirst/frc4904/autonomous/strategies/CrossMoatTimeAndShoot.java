package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossMoatTimeAndShoot extends CommandGroup {
	public CrossMoatTimeAndShoot(Chassis chassis, boolean usePID) {
		addSequential(new CrossMoatTime(chassis, usePID));
	}
}
