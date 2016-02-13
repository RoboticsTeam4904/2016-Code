package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class SpinUp extends CommandGroup {
	public SpinUp(Shooter shooter, double targetDistance) {
		super("SpinUp");
		setInterruptible(true);
		addSequential(new SpinUpFlywheel(shooter.flywheel, targetDistance));
	}
}
