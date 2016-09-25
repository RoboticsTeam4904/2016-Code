package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.commands.camera.CameraPoll;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Camera extends Subsystem {
	protected CameraData cameraDataCurrent;
	
	public Camera() {
		cameraDataCurrent = new CameraData();
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new CameraPoll(this));
	}
	
	public void setCameraData(CameraData data) {
		cameraDataCurrent = data;
	}
	
	public CameraData getCameraData() {
		return cameraDataCurrent;
	}
	
	public static class CameraData {
		private final boolean canSeeGoal;
		private final double goalX;
		private final double goalY;
		
		private CameraData(boolean canSeeGoal, double goalX, double goalY) {
			this.canSeeGoal = canSeeGoal;
			this.goalX = goalX;
			this.goalY = goalY;
		}
		
		public CameraData(double goalX, double goalY) {
			this(true, goalX, goalY);
		}
		
		public CameraData() {
			this(false, 0.0, 0.0);
		}
		
		public boolean canSeeGoal() {
			return canSeeGoal;
		}
		
		public double getGoalX() {
			return goalX;
		}
		
		public double getGoalY() {
			return goalY;
		}
		
		@Override
		public String toString() {
			return this.getClass().getName() + "#{" + "GoalX: " + goalX + ", " + "GoalY: " + goalY + ", " + "Goal Visible: " + Boolean.toString(canSeeGoal) + "}";
		}
	}
}