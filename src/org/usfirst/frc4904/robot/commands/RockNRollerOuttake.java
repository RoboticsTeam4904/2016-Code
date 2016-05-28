package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.subsystems.RockNRoller;

public class RockNRollerOuttake extends RockNRollerSet {
	public RockNRollerOuttake() {
		super("RockNRollerOuttake", RockNRoller.RockerState.OUTTAKE);
	}
}
