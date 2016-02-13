package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.subsystems.Flywheel;
import edu.wpi.first.wpilibj.command.Command;

public class SpinDownFlywheel extends Command {
	protected Flywheel flywheel;
	
	public SpinDownFlywheel(Flywheel flywheel) {
		super("SpinDownFlywheel");
		this.flywheel = flywheel;
	}
	
	@Override
	protected void initialize() {
		flywheel.spinDown();
	}
	
	@Override
	protected void execute() {}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
	@Override
	protected void end() {}
	
	@Override
	protected void interrupted() {}
}
