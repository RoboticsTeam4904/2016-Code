package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.SmartDashboardKey;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HoodSet extends Command {
	protected final boolean position;
	
	public HoodSet(String name, boolean position) {
		super(name);
		requires(RobotMap.Component.hood);
		this.position = position;
		setInterruptible(true);
	}
	
	public HoodSet(boolean position) {
		this("HoodSet", position);
	}
	
	@Override
	protected void initialize() {
		RobotMap.Component.hood.setPosition(position);
		SmartDashboard.putBoolean(SmartDashboardKey.HOOD_STATE.key, position);
	}
	
	@Override
	protected void execute() {
		SmartDashboard.putBoolean(SmartDashboardKey.HOOD_STATE.key, position);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {}
	
	@Override
	protected void interrupted() {}
}
