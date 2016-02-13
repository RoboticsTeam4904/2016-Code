package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Shoot extends CommandGroup {
	protected final Shooter shooter;
	
	public Shoot(Shooter shooter) {
		super("Shoot");
		this.shooter = shooter;
		// addSequential(new RunIf(new RockToShoot(shooter.rocker), shooter.hood::isUp, shooter.flywheel::isSpunUp));
		addSequential(new RockToShoot(shooter.rocker));
	}
}
