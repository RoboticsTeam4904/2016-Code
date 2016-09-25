package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.robot.subsystems.CameraPIDSource;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import org.usfirst.frc4904.standard.custom.ChassisController;
import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

public class GoalAlignDistance extends Command implements ChassisController {
	private final Chassis chassis;
	private final Camera camera;
	private final boolean stopWhenOnTarget;
	private boolean isDistanceAligned;
	private ChassisMove chassisMove;
	private final CustomPIDController pidController;
	
	public GoalAlignDistance(Chassis chassis, Camera camera, boolean stopWhenOnTarget) {
		this.camera = camera;
		this.chassis = chassis;
		this.stopWhenOnTarget = stopWhenOnTarget;
		isDistanceAligned = false;
		pidController = new CustomPIDController(RobotMap.Constant.AutonomousMetric.DISTANCE_ALIGN_P, RobotMap.Constant.AutonomousMetric.DISTANCE_ALIGN_I, RobotMap.Constant.AutonomousMetric.DISTANCE_ALIGN_D, new CameraPIDSource(camera, PIDSourceType.kDisplacement));
		pidController.setAbsoluteTolerance(RobotMap.Constant.AutonomousMetric.DISTANCE_ALIGN_TOLERANCE);
		pidController.setOutputRange(-1, 1);
		pidController.enable();
		pidController.setSetpoint(RobotMap.Constant.AutonomousMetric.DISTANCE_ALIGN_SETPOINT);
	}
	
	@Override
	public double getX() {
		return 0;
	}
	
	@Override
	public double getY() {
		double get = pidController.get();
		if (camera.getCameraData().canSeeGoal()) {
			isDistanceAligned = pidController.onTarget();
			return get;
		} else {
			return 0;
		}
	}
	
	@Override
	public double getTurnSpeed() {
		return 0;
	}
	
	@Override
	protected void initialize() {
		chassisMove = new ChassisMove(chassis, this);
		chassisMove.start();
		pidController.enable();
		pidController.setSetpoint(RobotMap.Constant.AutonomousMetric.DISTANCE_ALIGN_SETPOINT);
	}
	
	@Override
	protected void execute() {}
	
	@Override
	public boolean isFinished() {
		return stopWhenOnTarget && isDistanceAligned;
	}
	
	@Override
	protected void end() {
		chassisMove.cancel();
		pidController.reset();
		isDistanceAligned = false;
	}
	
	@Override
	protected void interrupted() {
		end();
	}
}