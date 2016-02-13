package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.subsystems.Rocker;

public class RockToIntake extends RockTo {
	public RockToIntake(Rocker rocker) {
		super("RockToIntake", rocker, Rocker.RockerPosition.INTAKE);
	}
}
