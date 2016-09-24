package org.usfirst.frc4904.robot.commands;

import org.usfirst.frc4904.robot.subsystems.Tim;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimIntake extends CommandGroup {
	public TimIntake() {
		super("TimIntake");
		addParallel(new TimSpin(true));
		addParallel(new TimSet(Tim.TimState.FULL_DOWN, false));
	}
}
