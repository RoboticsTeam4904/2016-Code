package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.Command;

public class GoalFind extends Command {
	protected Camera camera;
	protected final ChassisConstant chassisConstant;
	
	public GoalFind(Chassis chassis, Camera camera, double searchSpeed, boolean usePID) {
		chassisConstant = new ChassisConstant(chassis, 0.0, 0.0, searchSpeed, Double.MAX_VALUE);
		this.camera = camera;
	}
	
	@Override
	public boolean isFinished() {
		if (camera.getCameraData().canSeeGoal()) {
			LogKitten.w("Goal spotted. Stopping GoalFind.");
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	protected void initialize() {
		if (!camera.getCameraData().canSeeGoal()) {
			chassisConstant.start();
		}
	}
	
	@Override
	protected void execute() {}
	
	@Override
	public void end() {
		chassisConstant.cancel();
	}
	
	@Override
	public void interrupted() {
		chassisConstant.cancel();
	}
}