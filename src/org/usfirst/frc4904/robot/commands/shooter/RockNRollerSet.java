package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.subsystems.RockNRoller;
import edu.wpi.first.wpilibj.command.Command;

public class RockNRollerSet extends Command {
	protected final RockNRoller rocker;
	protected final RockNRoller.RockerState state;
	
	public RockNRollerSet(String name, RockNRoller rocker, RockNRoller.RockerState state) {
		super(name);
		requires(rocker);
		this.rocker = rocker;
		this.state = state;
		setInterruptible(false);
	}
	
	public RockNRollerSet(RockNRoller rocker, RockNRoller.RockerState state) {
		this("RockNRollerSet", rocker, state);
	}
	
	@Override
	protected void initialize() {
		rocker.set(state);
	}
	
	@Override
	protected void execute() {}
	
	@Override
	protected boolean isFinished() {
		return rocker.getState() == state;
	}
	
	@Override
	protected void end() {}
	
	@Override
	protected void interrupted() {}
}
