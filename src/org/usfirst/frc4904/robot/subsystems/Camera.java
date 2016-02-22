package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.Idle;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Camera extends Subsystem {
	public enum CameraStatus {
		CONNECTED, DISCONNECTED;
	}
	protected CameraStatus cameraStatus;
	protected String cameraIP;
	protected int cameraPort;
	protected String cameraPath;
	protected String cameraDataPrevious = "";
	protected String cameraDataCurrent = "";
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
		setDefaultCommand(new Idle(this));
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
	
	public void setCameraData(String data) {
		if (data.equals(RobotMap.Constant.Network.CONNECTION_ERROR_MESSAGE)) {
			cameraDataCurrent = data;
		} else {
			cameraDataPrevious = cameraDataCurrent;
			cameraDataCurrent = data;
		}
	}
	
	public String getCameraData(boolean shouldIgnoreError) {
		if (shouldIgnoreError) {
			if (cameraDataCurrent.equals(RobotMap.Constant.Network.CONNECTION_ERROR_MESSAGE)) {
				return cameraDataPrevious;
			} else {
				return cameraDataCurrent;
			}
		} else {
			return cameraDataCurrent;
		}
	}
	
	public double getGoalOffDistance(boolean shouldIgnoreError) {
		if (shouldIgnoreError) {
			if (cameraOffDistanceCurrent.equals(Camera.CAMERA_ERROR_VALUE)) {
				return cameraOffDistancePrevious;
			} else {
				return cameraOffDistanceCurrent;
			}
		} else {
			return cameraOffDistanceCurrent;
		}
	}
	
	public void setGoalOffDistance(Double offDistance) {
		if (offDistance.equals(Camera.CAMERA_ERROR_VALUE)) {
			cameraOffDistanceCurrent = offDistance;
		} else {
			cameraOffDistancePrevious = cameraOffDistanceCurrent;
			cameraOffDistanceCurrent = offDistance;
		}
	}
	
	public double getGoalOffAngle(boolean shouldIgnoreError) {
		if (shouldIgnoreError) {
			if (cameraOffAngleCurrent.equals(Camera.CAMERA_ERROR_VALUE)) {
				return cameraOffAnglePrevious;
			} else {
				return cameraOffAngleCurrent;
			}
		} else {
			return cameraOffAngleCurrent;
		}
	}
	
	public void setGoalOffAngle(Double offAngle) {
		if (offAngle.equals(Camera.CAMERA_ERROR_VALUE)) {
			cameraOffAngleCurrent = offAngle;
		} else {
			cameraOffAnglePrevious = cameraOffAngleCurrent;
			cameraOffAngleCurrent = offAngle;
		}
	}
}
