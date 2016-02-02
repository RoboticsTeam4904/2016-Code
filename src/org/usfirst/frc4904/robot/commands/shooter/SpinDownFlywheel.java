package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.subsystems.Flywheel;
import edu.wpi.first.wpilibj.command.Command;

public class SpinDownFlywheel extends Command {
	protected Flywheel flywheel;
	
	public SpinDownFlywheel(Flywheel flywheel) {
		super();
		this.flywheel = flywheel;
	}
	
	@Override
	protected void initialize() {
		flywheel.spinDown();
	}
	
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
	}
}
