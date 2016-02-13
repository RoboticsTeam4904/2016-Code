package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.subsystems.Rocker;

public class RockToShoot extends RockTo {
	public RockToShoot(Rocker rocker) {
		super("RockToShoot", rocker, Rocker.RockerPosition.SHOOT);
	}
}
