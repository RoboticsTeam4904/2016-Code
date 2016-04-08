package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.SingleOp;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimTap extends CommandGroup {
	public TimTap() {
		addSequential(new TimSet(Tim.TimState.DRAWBRIDGE));
		addParallel(new SingleOp() {
			
			@Override
			protected void runOp() {
				LogKitten.wtf("AAA");
			}
		});
		addSequential(new TimSet(Tim.TimState.DRAWBRIDGE_TAP));
		addParallel(new SingleOp() {
			
			@Override
			protected void runOp() {
				LogKitten.wtf("BBB");
			}
		});
		addSequential(new TimSet(Tim.TimState.DRAWBRIDGE));
		addParallel(new SingleOp() {
			
			@Override
			protected void runOp() {
				LogKitten.wtf("CCC");
			}
		});
	}
}
