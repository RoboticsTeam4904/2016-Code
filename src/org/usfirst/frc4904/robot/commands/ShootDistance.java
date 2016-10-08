package org.usfirst.frc4904.robot.commands;


import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootDistance extends CommandGroup {
	public ShootDistance() {
		addSequential(new SpinUpFlywheelDistance(true));
		addParallel(new SpinUpFlywheelDistance(false));
		addParallel(new RockNRollerShoot());
	}
}
