package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.RobotMap.Constant.Autoalign;
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
		
		// X angle
		public double getAngleToGoal() {
			double cameraToGoalAngleY = Autoalign.DEG_PER_PIXEL_Y * (goalY - Autoalign.CAMERA_HEIGHT_PIXELS / 2);
			double cameraToGoalAngleX = Autoalign.DEG_PER_PIXEL_X * (goalX - Autoalign.CAMERA_WIDTH_PIXELS / 2);
			double cameraDistance = (Autoalign.GOAL_HEIGHT - Autoalign.CAMERA_HEIGHT) / Math.tan(cameraToGoalAngleY);
			double mountOffsetDistance = Math.sqrt(Math.pow(Autoalign.MOUNT_OFFSET_X, 2) + Math.pow(Autoalign.MOUNT_OFFSET_Y, 2));
			double mountOffsetAngle = Math.atan(Autoalign.MOUNT_OFFSET_Y / Autoalign.MOUNT_OFFSET_X);
			double innerCameraAngle = Math.PI / 2 - cameraToGoalAngleX - mountOffsetAngle;
			double distance = Math.sqrt(Math.pow(cameraDistance, 2) + Math.pow(mountOffsetDistance, 2) - 2 * cameraDistance * mountOffsetDistance * Math.cos(innerCameraAngle));
			double innerShooterAngle = Math.asin(Math.sin(innerCameraAngle) * cameraDistance / distance);
			double angleToGoal = innerShooterAngle - mountOffsetAngle - Math.PI / 2;
			return angleToGoal;
			// use distance
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