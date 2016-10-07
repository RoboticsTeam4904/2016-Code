package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.subsystems.RockNRoller;

public class RockNRollerShoot extends RockNRollerSet {
	public RockNRollerShoot() {
		super("RockNRollerShoot", RockNRoller.RockerState.SHOOT);
	}
}
