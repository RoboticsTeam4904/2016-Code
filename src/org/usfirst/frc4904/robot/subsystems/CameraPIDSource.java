package org.usfirst.frc4904.robot.subsystems;


import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class CameraPIDSource extends Camera implements PIDSource {
	private PIDSourceType sourceType;
	
	public CameraPIDSource(Camera camera, PIDSourceType sourceType) {
		super();
		this.sourceType = sourceType;
	}
	
	@Override
	public PIDSourceType getPIDSourceType() {
		return sourceType;
	}
	
	/**
	 * 
	 * Gets the value to use in PID.
	 * If PIDSourceType is set to kDisplacement then it returns the goalX.
	 * If PIDSourceType is set to kRate then it returns the goalY.
	 * 
	 */
	@Override
	public double pidGet() {
		if (sourceType == PIDSourceType.kRate) {
			return super.getCameraData().getGoalX();
		} else {
			return super.getCameraData().getGoalY();
		}
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType sourceType) {
		this.sourceType = sourceType;
	}
}
