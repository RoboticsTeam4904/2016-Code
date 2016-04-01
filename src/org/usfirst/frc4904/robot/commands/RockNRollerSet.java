package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.RockNRoller;
import edu.wpi.first.wpilibj.command.Command;

public class RockNRollerSet extends Command {
	protected final RockNRoller.RockerState state;
	
	public RockNRollerSet(String name, RockNRoller.RockerState state) {
		super(name);
		requires(RobotMap.Component.rockNRoller);
		this.state = state;
		setInterruptible(true);
	}
	
	public RockNRollerSet(RockNRoller.RockerState state) {
		this("RockNRollerSet", state);
	}
	
	@Override
	protected void initialize() {
		RobotMap.Component.rockNRoller.set(state);
	}
	
	@Override
	protected void execute() {}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {}
	
	@Override
	protected void interrupted() {}
}
