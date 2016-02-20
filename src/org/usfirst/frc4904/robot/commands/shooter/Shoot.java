package org.usfirst.frc4904.robot.commands.shooter;


import edu.wpi.first.wpilibj.command.CommandGroup;

public class Shoot extends CommandGroup {
	public Shoot() {
		super("Shoot");
		addSequential(new RockNRollerShoot());
	}
}
