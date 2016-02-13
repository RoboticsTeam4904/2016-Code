package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.subsystems.RockNRoller;
import edu.wpi.first.wpilibj.command.Command;

public class RockNRollerSet extends Command {
	protected final RockNRoller rocker;
	protected final RockNRoller.RockerPosition position;
	
	public RockNRollerSet(String name, RockNRoller rocker, RockNRoller.RockerPosition position) {
		super(name);
		requires(rocker);
		this.rocker = rocker;
		this.position = position;
		setInterruptible(false);
	}
	
	public RockNRollerSet(RockNRoller rocker, RockNRoller.RockerPosition position) {
		this("RockNRollerSet", rocker, position);
	}
	
	@Override
	protected void initialize() {
		rocker.set(position);
	}
	
	@Override
	protected void execute() {}
	
	@Override
	protected boolean isFinished() {
		return rocker.getPosition() == position;
	}
	
	@Override
	protected void end() {}
	
	@Override
	protected void interrupted() {}
}
