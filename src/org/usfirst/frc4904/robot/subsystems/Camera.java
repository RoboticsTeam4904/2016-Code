package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.shooter.CameraPoll;
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
	
	public Camera(String cameraIP, int cameraPort, String cameraPath) {
		cameraStatus = CameraStatus.DISCONNECTED;
		this.cameraIP = cameraIP;
		this.cameraPort = cameraPort;
		this.cameraPath = cameraPath;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new CameraPoll(this));
	}
	
	public String getCameraData(boolean shouldGiveLast) {
		if (shouldGiveLast) {
			if (cameraDataCurrent.equals(RobotMap.Constant.Network.CONNECTION_ERROR_MESSAGE)) {
				return cameraDataPrevious;
			} else {
				return cameraDataCurrent;
			}
		} else {
			return cameraDataCurrent;
		}
	}
	
	public String getCameraIP() {
		return cameraIP;
	}
	
	public int getCameraPort() {
		return cameraPort;
	}
	
	public String getCameraPath() {
		return cameraPath;
	}
	
	public void setCameraData(String data) {
		if (data.equals(RobotMap.Constant.Network.CONNECTION_ERROR_MESSAGE)) {
			cameraDataCurrent = data;
		} else {
			cameraDataPrevious = cameraDataCurrent;
			cameraDataCurrent = data;
		}
	}
	
	public void setCameraStatus(CameraStatus status) {
		cameraStatus = status;
	}
}
