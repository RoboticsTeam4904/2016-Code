package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.subsystems.Rocker;
import edu.wpi.first.wpilibj.command.Command;

public class RockTo extends Command {
	protected final Rocker rocker;
	protected final Rocker.RockerPosition position;
	
	public RockTo(String name, Rocker rocker, Rocker.RockerPosition position) {
		super(name);
		requires(rocker);
		this.rocker = rocker;
		this.position = position;
		setInterruptible(false);
	}
	
	public RockTo(Rocker rocker, Rocker.RockerPosition position) {
		this("RockTo", rocker, position);
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
