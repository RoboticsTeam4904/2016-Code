package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.RockNRoller;
import org.usfirst.frc4904.standard.commands.LogWarning;
import org.usfirst.frc4904.standard.commands.RunIfElse;

public class RockNRollerShoot extends RunIfElse {
	public RockNRollerShoot() {
		super(new RockNRollerSet("RockNRollerShoot", RobotMap.Component.rockNRoller, RockNRoller.RockerState.SHOOT), new LogWarning("RockNRoller was denied shoot because the hood wasn't up."), RobotMap.Component.hood::isUp);
	}
}
