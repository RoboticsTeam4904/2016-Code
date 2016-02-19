package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.subsystems.RockNRoller;

public class RockNRollerOuttake extends RockNRollerSet {
	public RockNRollerOuttake(RockNRoller rocker) {
		super("RockNRollerOuttake", rocker, RockNRoller.RockerState.OUTTAKE);
	}
}
