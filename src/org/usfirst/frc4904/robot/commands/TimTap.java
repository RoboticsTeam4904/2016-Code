package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.commands.RunFor;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimTap extends CommandGroup {
	public TimTap() {
		addSequential(new TimSet(Tim.TimState.DRAWBRIDGE));
		addSequential(new RunFor(new TimSet(Tim.TimState.DRAWBRIDGE_TAP), RobotMap.Constant.TIMTAP_DURATION));
		addSequential(new TimSet(Tim.TimState.DRAWBRIDGE));
	}
}
