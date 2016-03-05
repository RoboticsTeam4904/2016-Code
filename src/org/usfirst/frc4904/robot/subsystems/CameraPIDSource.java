package org.usfirst.frc4904.robot.subsystems;


import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class CameraPIDSource extends Camera implements PIDSource {
	private PIDSourceType sourceType;
	
	public CameraPIDSource(String cameraIP, int cameraPort, String cameraPath, String cameraProtocol, PIDSourceType sourceType) {
		super(cameraIP, cameraPort, cameraPath, cameraProtocol);
		this.sourceType = sourceType;
	}
	
	@Override
	public PIDSourceType getPIDSourceType() {
		return sourceType;
	}
	
	/**
	 * 
	 * Gets the value to use in PID.
	 * If PIDSourceType is set to kDisplacement then it returns the distance.
	 * If PIDSourceType is set to kRate then it returns OffAngle
	 * 
	 */
	@Override
	public double pidGet() {
		if (sourceType == PIDSourceType.kRate) {
			return super.getGoalOffAngle(false);
		} else {
			return super.getGoalOffDistance(false);
		}
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType sourceType) {
		this.sourceType = sourceType;
	}
}
