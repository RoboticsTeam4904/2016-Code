package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.robot.subsystems.CameraPIDSource;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import org.usfirst.frc4904.standard.custom.ChassisController;
import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

public class GoalAlign extends Command implements ChassisController {
	private final Camera camera;
	private final double driveSpeed;
	private final double turnSpeed;
	private boolean isAngleAligned;
	private boolean isDistanceAligned;
	private final Chassis chassis;
	private ChassisMove chassisMove;
	private final CustomPIDController pidController;
	private final boolean stopWhenOnTarget;
	
	public GoalAlign(Chassis chassis, Camera camera, double driveSpeed, double turnSpeed, boolean stopWhenOnTarget) {
		this.camera = camera;
		this.chassis = chassis;
		this.driveSpeed = driveSpeed;
		this.turnSpeed = turnSpeed;
		this.stopWhenOnTarget = stopWhenOnTarget;
		isAngleAligned = false;
		isDistanceAligned = false;
		pidController = new CustomPIDController(RobotMap.Constant.AutonomousMetric.ALIGN_P, RobotMap.Constant.AutonomousMetric.ALIGN_I, RobotMap.Constant.AutonomousMetric.ALIGN_D, new CameraPIDSource(camera, PIDSourceType.kRate));
		pidController.setAbsoluteTolerance(RobotMap.Constant.AutonomousMetric.ALIGN_TOLERANCE);
		pidController.setOutputRange(-1, 1);
		pidController.enable();
		pidController.setSetpoint(RobotMap.Constant.CAMERA_WIDTH_PIXELS / 2);
	}
	
	@Override
	public double getX() {
		return 0;
	}
	
	@Override
	public double getY() {
		/*
		 * if (!isAngleAligned) {
		 * return 0.0;
		 * }
		 * if (camera.getCameraData().getGoalX() - RobotMap.Constant.CAMERA_WIDTH_PIXELS / 2 > 0) {
		 * return driveSpeed;
		 * } else if (camera.getCameraData().getGoalX() - RobotMap.Constant.CAMERA_WIDTH_PIXELS / 2 > 0) {
		 * return -1.0 * driveSpeed;
		 * }
		 */
		isDistanceAligned = true;
		return 0;
	}
	
	@Override
	public double getTurnSpeed() {
		/*
		 * LogKitten.wtf((camera.getCameraData().getGoalX() - RobotMap.Constant.CAMERA_WIDTH_PIXELS / 2) + "");
		 * if (camera.getCameraData().getGoalX() - RobotMap.Constant.CAMERA_WIDTH_PIXELS / 2 > 0) {
		 * return turnSpeed;
		 * } else if (camera.getCameraData().getGoalX() - RobotMap.Constant.CAMERA_WIDTH_PIXELS / 2 > 0) {
		 * return -1.0 * turnSpeed;
		 * }
		 * isAngleAligned = true;
		 * return 0.0;
		 */
		double get = pidController.get();
		if (camera.getCameraData().canSeeGoal()) {
			isAngleAligned = pidController.onTarget();
			return get;
		} else {
			// return Math.signum(get) * RobotMap.Constant.AutonomousMetric.ALIGN_SPEED;
			LogKitten.wtf("MASOIFMIASOFMAIOSDMIOASMDOIASFIAOA");
			return 0;
		}
	}
	
	@Override
	protected void initialize() {
		chassisMove = new ChassisMove(chassis, this);
		chassisMove.start();
		pidController.enable();
		pidController.setSetpoint(RobotMap.Constant.CAMERA_WIDTH_PIXELS / 2);
	}
	
	@Override
	protected void execute() {}
	
	@Override
	public boolean isFinished() {
		return stopWhenOnTarget && isAngleAligned && isDistanceAligned;
	}
	
	@Override
	protected void end() {
		chassisMove.cancel();
		pidController.reset();
		isAngleAligned = false;
		isDistanceAligned = false;
	}
	
	@Override
	protected void interrupted() {
		end();
	}
}