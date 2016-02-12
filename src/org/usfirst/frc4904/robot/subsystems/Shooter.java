package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.Idle;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	public final Rocker rocker;
	public final Hood hood;
	public final Flywheel flywheel;
	
	public Shooter(Rocker rocker, Hood hood, Flywheel flywheel) {
		super("Shooter");
		this.rocker = rocker;
		this.hood = hood;
		this.flywheel = flywheel;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}
