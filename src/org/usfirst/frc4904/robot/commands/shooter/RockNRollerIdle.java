package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.subsystems.RockNRoller;
import org.usfirst.frc4904.standard.commands.Idle;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class RockNRollerIdle extends CommandGroup {
	public RockNRollerIdle(RockNRoller rockNRoller) {
		super("RockNRollerIdle");
		addSequential(new RockNRollerSet("RockNRollerIdle", rockNRoller, RockNRoller.RockerState.IDLE));
		addSequential(new Idle(rockNRoller));
	}
}
