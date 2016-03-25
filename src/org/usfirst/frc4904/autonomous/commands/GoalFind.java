package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoalFind extends CommandGroup {
	protected Camera camera;
	private static final boolean GET_REAL_DATA = true;
	protected int statusIndex;
	protected String statusGood;
	
	public GoalFind(Chassis chassis, Camera camera, double searchSpeed, int statusIndex, String statusGood, boolean usePID) {
		addSequential(new ChassisConstant(chassis, 0.0, 0.0, searchSpeed, Double.MAX_VALUE));
		this.camera = camera;
		this.statusIndex = statusIndex;
		this.statusGood = statusGood;
	}
	
	@Override
	public boolean isFinished() {
		boolean cameraData = camera.getCameraData(true).canSeeGoal();
		if (cameraData) {
			LogKitten.v("It was good. It got: " + cameraData);
			return true;
		} else {
			LogKitten.w("Does it see a goal? " + cameraData);
			return false;
		}
	}
}