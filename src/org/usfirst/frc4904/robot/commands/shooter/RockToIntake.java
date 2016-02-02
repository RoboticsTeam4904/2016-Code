package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.subsystems.Rocker;
import org.usfirst.frc4904.robot.subsystems.Rocker.RockerPosition;
import edu.wpi.first.wpilibj.command.Command;

public class RockToIntake extends Command {
	protected Rocker rocker;
	
	public RockToIntake(Rocker rocker) {
		super();
		requires(rocker);
		this.rocker = rocker;
		setInterruptible(false);
	}
	
	@Override
	protected void initialize() {
		rocker.set(RockerPosition.INTAKE);
	}
	
	@Override
	protected void execute() {}
	
	@Override
	protected boolean isFinished() {
		return rocker.isInIntakePosition();
	}
	
	@Override
	protected void end() {}
	
	@Override
	protected void interrupted() {}
}
