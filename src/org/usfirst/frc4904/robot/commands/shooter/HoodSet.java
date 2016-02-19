package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.subsystems.Hood;
import edu.wpi.first.wpilibj.command.Command;

public class HoodSet extends Command {
	protected final Hood hood;
	protected final boolean position;
	
	public HoodSet(String name, Hood hood, boolean position) {
		super(name);
		requires(hood);
		this.hood = hood;
		this.position = position;
		setInterruptible(true);
	}
	
	public HoodSet(Hood hood, boolean position) {
		this("HoodSet", hood, position);
	}
	
	@Override
	protected void initialize() {
		hood.setPosition(position);
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
