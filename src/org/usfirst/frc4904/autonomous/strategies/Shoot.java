package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.robot.commands.RockNRollerShoot;
import org.usfirst.frc4904.robot.commands.SpinUpFlywheelDistance;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Shoot extends CommandGroup {
	public Shoot() {
		addSequential(new SpinUpFlywheelDistance(true));
		addParallel(new RockNRollerShoot());
		addParallel(new SpinUpFlywheelDistance(true));
	}
}