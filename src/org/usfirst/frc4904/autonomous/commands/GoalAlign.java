package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import org.usfirst.frc4904.standard.custom.ChassisController;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoalAlign extends CommandGroup implements ChassisController {
	private final Camera camera;
	private final double driveSpeed;
	private final double turnSpeed;
	private boolean isAngleAligned;
	private boolean isDistanceAligned;
	
	public GoalAlign(Chassis chassis, Camera camera, double driveSpeed, double turnSpeed) {
		this.camera = camera;
		this.driveSpeed = driveSpeed;
		this.turnSpeed = turnSpeed;
		addSequential(new ChassisMove(chassis, this));
		isAngleAligned = false;
		isDistanceAligned = false;
	}
	
	@Override
	public double getX() {
		return 0;
	}
	
	@Override
	public double getY() {
		if (!isAngleAligned) {
			return 0.0;
		}
		if (camera.getCameraData().getGoalX() - RobotMap.Constant.CAMERA_WIDTH_PIXELS / 2 > 0) {
			return driveSpeed;
		} else if (camera.getCameraData().getGoalX() - RobotMap.Constant.CAMERA_WIDTH_PIXELS / 2 > 0) {
			return -1.0 * driveSpeed;
		}
		isDistanceAligned = true;
		return 0;
	}
	
	@Override
	public double getTurnSpeed() {
		if (camera.getCameraData().getGoalX() - RobotMap.Constant.CAMERA_WIDTH_PIXELS / 2 > 0) {
			return turnSpeed;
		} else if (camera.getCameraData().getGoalX() - RobotMap.Constant.CAMERA_WIDTH_PIXELS / 2 > 0) {
			return -1.0 * turnSpeed;
		}
		isAngleAligned = true;
		return 0.0;
	}
	
	@Override
	public boolean isFinished() {
		return isAngleAligned && isDistanceAligned;
	}
}