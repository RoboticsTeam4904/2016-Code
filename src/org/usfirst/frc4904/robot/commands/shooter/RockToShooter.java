package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.subsystems.Rocker;
import org.usfirst.frc4904.robot.subsystems.Rocker.RockerPosition;
import edu.wpi.first.wpilibj.command.Command;

public class RockToShooter extends Command {
	protected Rocker rocker;
	
	public RockToShooter(Rocker rocker) {
		super();
		requires(rocker);
		this.rocker = rocker;
		setInterruptible(false);
	}
	
	@Override
	protected void initialize() {
		rocker.set(RockerPosition.SHOOT);
	}
	
	@Override
	protected void execute() {}
	
	@Override
	protected boolean isFinished() {
		return rocker.isInShootPosition();
	}
	
	@Override
	protected void end() {}
	
	@Override
	protected void interrupted() {}
}
