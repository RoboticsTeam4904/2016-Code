package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import edu.wpi.first.wpilibj.command.Command;

public class TimLowbar extends Command {
	private final Tim tim;
	private final CustomEncoder timEncoder;
	
	public TimLowbar(Tim tim, CustomEncoder timEncoder) {
		this.tim = tim;
		this.timEncoder = timEncoder;
	}
	
	@Override
	protected void initialize() {}
	
	@Override
	protected void execute() {
		if (timEncoder.getDistance() > Tim.TIM_LOWBAR + 20) {
			LogKitten.v("Tim too low");
			tim.set((timEncoder.getDistance() - Tim.TIM_LOWBAR) / 3200);
		} else if (timEncoder.getDistance() < Tim.TIM_LOWBAR - 20) {
			LogKitten.v("Tim too high");
			tim.set((timEncoder.getDistance() - Tim.TIM_LOWBAR) / 3200);
		} else {
			LogKitten.v("Tim is golilocks");
			tim.set(0.0);
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		tim.set(0.0);
	}
	
	@Override
	protected void interrupted() {
		tim.set(0.0);
	}
}
