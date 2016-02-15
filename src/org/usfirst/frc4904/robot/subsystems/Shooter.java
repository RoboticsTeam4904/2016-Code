package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	public final Motor rockNRoller;
	public final Hood hood;
	public final Motor flywheel;
	
	public Shooter(Motor rockNRoller, Hood hood, Motor flywheel) {
		super("Shooter");
		this.rockNRoller = rockNRoller;
		this.hood = hood;
		this.flywheel = flywheel;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}
