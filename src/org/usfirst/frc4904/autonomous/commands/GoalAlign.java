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

public class GoalAlign extends Command implements ChassisController {
	private final Chassis chassis;
	private final Camera camera;
	private final boolean stopWhenOnTarget;
	private boolean isAngleAligned;
	private ChassisMove chassisMove;
	private final CustomPIDController pidController;
	private long waitStart;
	private boolean isStable;
	
	public GoalAlign(Chassis chassis, Camera camera, boolean stopWhenOnTarget) {
		this.camera = camera;
		this.chassis = chassis;
		this.stopWhenOnTarget = stopWhenOnTarget;
		isAngleAligned = false;
		isStable = false;
		waitStart = 0;
		pidController = new CustomPIDController(RobotMap.Constant.AutonomousMetric.ALIGN_P, RobotMap.Constant.AutonomousMetric.ALIGN_I, RobotMap.Constant.AutonomousMetric.ALIGN_D, new CameraPIDSource(camera, PIDSourceType.kRate));
		pidController.setAbsoluteTolerance(RobotMap.Constant.AutonomousMetric.ALIGN_TOLERANCE);
		pidController.setOutputRange(-1, 1);
		pidController.enable();
		pidController.setSetpoint(RobotMap.Constant.AutonomousMetric.ALIGN_SETPOINT);
	}
	
	@Override
	public double getX() {
		return 0;
	}
	
	@Override
	public double getY() {
		return 0;
	}
	
	@Override
	public double getTurnSpeed() {
		double get = pidController.get();
		if (camera.getCameraData().canSeeGoal()) {
			isAngleAligned = pidController.onTarget();
			return get;
		} else {
			return 0;
		}
	}
	
	@Override
	protected void initialize() {
		chassisMove = new ChassisMove(chassis, this);
		chassisMove.start();
		pidController.enable();
		pidController.setSetpoint(RobotMap.Constant.AutonomousMetric.ALIGN_SETPOINT);
	}
	
	@Override
	protected void execute() {}
	
	@Override
	public boolean isFinished() {
		if (pidController.onTarget()) {
			if (waitStart == 0) {
				waitStart = System.currentTimeMillis();
			} else {
				if ((System.currentTimeMillis() - waitStart) >= RobotMap.Constant.AutonomousMetric.ALIGN_TIME_TOLERANCE) {
					isStable = true;
				}
			}
		} else {
			waitStart = 0;
		}
		return stopWhenOnTarget && isAngleAligned;
	}
	
	@Override
	protected void end() {
		chassisMove.cancel();
		pidController.reset();
		isAngleAligned = false;
	}
	
	@Override
	protected void interrupted() {
		end();
	}
}