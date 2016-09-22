package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.subsystems.RockNRoller;
import org.usfirst.frc4904.standard.commands.RunIfElse;
import edu.wpi.first.wpilibj.command.PrintCommand;

public class RockNRollerShoot extends RunIfElse {
	public RockNRollerShoot() {
		super(new RockNRollerSet("RockNRollerShoot", RockNRoller.RockerState.SHOOT), new PrintCommand("RockNRoller was denied shoot because the hood wasn't up."));
	}
}
