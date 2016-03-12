package org.usfirst.frc4904.robot.custom;


import org.usfirst.frc4904.robot.subsystems.CameraPIDSource;
import org.usfirst.frc4904.standard.custom.ChassisController;
import org.usfirst.frc4904.standard.custom.motioncontrollers.MotionController;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PIDOffAngleChassisController implements ChassisController {
	protected ChassisController controller;
	protected double maxDegreesPerSecond;
	protected double targetAngle;
	protected double lastUpdate;
	protected CameraPIDSource camera;
	protected MotionController motionController;
	protected boolean finished;
	protected double tolerance;
	
	public PIDOffAngleChassisController(ChassisController controller, CameraPIDSource camera, MotionController motionController, double maxDegreesPerSecond, double tolerance) {
		this.controller = controller;
		this.maxDegreesPerSecond = maxDegreesPerSecond;
		this.tolerance = tolerance;
		this.camera = camera;
		this.camera.setPIDSourceType(PIDSourceType.kDisplacement);
		this.motionController = motionController;
		motionController.setInputRange(-180.0f, 180.0f);
		motionController.setOutputRange(-1.0f, 1.0f);
		motionController.setContinuous(true);
		motionController.reset();
		motionController.enable();
		lastUpdate = System.currentTimeMillis() / 1000.0;
		targetAngle = 0;
		finished = false;
	}
	
	public void reset() {
		targetAngle = 0;
		lastUpdate = System.currentTimeMillis() / 1000.0;
		motionController.disable();
		motionController.reset();
		motionController.enable();
	}
	
	@Override
	public double getX() {
		return controller.getX();
	}
	
	@Override
	public double getY() {
		return controller.getY();
	}
	
	@Override
	public double getTurnSpeed() {
		motionController.setSetpoint(targetAngle);
		finished = (((-tolerance) < camera.getGoalOffAngle(true)) && (tolerance > camera.getGoalOffAngle(true)));
		if (finished) {
			return 0;
		}
		return motionController.get();
	}
	
	public boolean isFinished() {
		return finished;
	}
}
