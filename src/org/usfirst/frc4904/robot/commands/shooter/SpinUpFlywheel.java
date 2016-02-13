package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.subsystems.Flywheel;
import edu.wpi.first.wpilibj.command.Command;

public class SpinUpFlywheel extends Command {
	protected Flywheel flywheel;
	protected double targetDistance;
	
	public SpinUpFlywheel(Flywheel flywheel, double targetDistance) {
		super("SpinUpFlywheel");
		this.flywheel = flywheel;
		this.targetDistance = targetDistance;
	}
	
	@Override
	protected void initialize() {
		flywheel.setTargetSpeedForDistance(targetDistance);
		flywheel.spinUp();
	}
	
	@Override
	protected void execute() {}
	
	@Override
	protected boolean isFinished() {
		return flywheel.isSpunUp();
	}
	
	@Override
	protected void end() {}
	
	@Override
	protected void interrupted() {}
}
