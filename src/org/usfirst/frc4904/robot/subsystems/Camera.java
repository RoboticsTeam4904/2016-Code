package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.camera.CameraPoll;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Camera extends Subsystem {
	public enum CameraStatus {
		CONNECTED, DISCONNECTED, ERRDATA;
	}
	protected CameraStatus cameraStatus;
	protected String cameraIP;
	protected int cameraPort;
	protected String cameraPath;
	protected CameraData cameraDataPrevious;
	protected CameraData cameraDataCurrent;
	protected Double cameraOffAngleCurrent = 0.0;
	protected Double cameraOffAnglePrevious = 0.0;
	protected Double cameraOffDistanceCurrent = 0.0;
	protected Double cameraOffDistancePrevious = 0.0;
	protected boolean cameraCanSeeGoal = false;
	protected String cameraProtocol;
	public static final double CAMERA_ERROR_VALUE = -0;
	
	public Camera(String cameraIP, int cameraPort, String cameraPath, String cameraProtocol) {
		cameraStatus = CameraStatus.DISCONNECTED;
		this.cameraIP = cameraIP;
		this.cameraPort = cameraPort;
		this.cameraPath = cameraPath;
		this.cameraProtocol = cameraProtocol;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new CameraPoll(this));
	}
	
	public String getCameraIP() {
		return cameraIP;
	}
	
	public int getCameraPort() {
		return cameraPort;
	}
	
	public String getCameraProtocol() {
		return cameraProtocol;
	}
	
	public String getCameraPath() {
		return cameraPath;
	}
	
	public void setCameraStatus(CameraStatus status) {
		cameraStatus = status;
	}
	
	public CameraStatus getCameraStatus() {
		return cameraStatus;
	}
	
	public boolean getCameraCanSeeGoal() {
		return cameraCanSeeGoal;
	}
	
	public void setCameraCanSeeGoal(boolean canSee) {
		cameraCanSeeGoal = canSee;
	}
	
	public void setCameraData(CameraData data) {
		if (data.getTotalData().equals(RobotMap.Constant.Network.CONNECTION_ERROR_MESSAGE)) {
			cameraDataCurrent = data;
		} else {
			cameraDataPrevious = cameraDataCurrent;
			cameraDataCurrent = data;
		}
	}
	
	public CameraData getCameraData(boolean shouldIgnoreError) {
		if (shouldIgnoreError) {
			if (cameraDataCurrent.getTotalData().equals(RobotMap.Constant.Network.CONNECTION_ERROR_MESSAGE)) {
				return cameraDataPrevious;
			} else {
				return cameraDataCurrent;
			}
		} else {
			return cameraDataCurrent;
		}
	}
	
	public static class CameraData {
		private boolean canSeeGoal = false;
		private Double degreesToTurn = 0D;
		private Double distanceToMove = 0D;
		private CameraStatus cameraStatus;
		private String totalData = RobotMap.Constant.Network.CONNECTION_ERROR_MESSAGE;
		
		public CameraData() {
			cameraStatus = CameraStatus.DISCONNECTED;
		}
		
		public void setCanSeeGoal(boolean canSeeGoal) {
			this.canSeeGoal = canSeeGoal;
		}
		
		public boolean canSeeGoal() {
			return canSeeGoal;
		}
		
		public void setDegreesToTurn(Double degreesToTurn) {
			this.degreesToTurn = degreesToTurn;
		}
		
		public Double getDegreesToTurn() {
			return degreesToTurn;
		}
		
		public void setDistanceToMove(Double distanceToMove) {
			this.distanceToMove = distanceToMove;
		}
		
		public Double getDistanceToMove() {
			return distanceToMove;
		}
		
		public void setCameraStatus(CameraStatus cameraStatus) {
			this.cameraStatus = cameraStatus;
		}
		
		public CameraStatus getCameraStatus() {
			return cameraStatus;
		}
		
		public void setTotalData(String totalData) {
			this.totalData = totalData;
		}
		
		public String getTotalData() {
			return totalData;
		}
		
		@Override
		public String toString() {
			return this.getClass().getName() + "#{" + "Distance: " + distanceToMove.toString() + ", " + "Turn: " + degreesToTurn.toString() + ", " + "Goal Visible: " + Boolean.toString(canSeeGoal) + ", " + "Total Data: " + totalData + ", " + "Camera Status: " + cameraStatus + "}";
		}
	}
}