package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.subsystems.RockNRoller;

public class RockNRollerIdle extends RockNRollerSet {
	public RockNRollerIdle(RockNRoller rocker) {
		super("RockNRollerIdle", rocker, RockNRoller.RockerPosition.IDLE);
	}
}
