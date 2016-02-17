package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.subsystems.RockNRoller;

public class RockNRollerShoot extends RockNRollerSet {
	public RockNRollerShoot(RockNRoller rocker) {
		super("RockNRollerShoot", rocker, RockNRoller.RockerState.SHOOT);
	}
}
