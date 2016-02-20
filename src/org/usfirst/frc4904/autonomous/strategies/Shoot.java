package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.robot.commands.shooter.HoodUp;
import org.usfirst.frc4904.robot.commands.shooter.RockNRollerShoot;
import org.usfirst.frc4904.robot.commands.shooter.SpinUpFlywheel;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Shoot extends CommandGroup {
	public Shoot() {
		addParallel(new HoodUp());
		addSequential(new WaitCommand(0.25));
		addParallel(new SpinUpFlywheel());
		addSequential(new WaitCommand(0.5));
		addSequential(new RockNRollerShoot());
	}
}
