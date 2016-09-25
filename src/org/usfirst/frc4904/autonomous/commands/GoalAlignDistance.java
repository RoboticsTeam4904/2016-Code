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

public class GoalAlignDistance extends Command implements ChassisController {
	private final Camera camera;
	private final double driveSpeed;
	private final double turnSpeed;
	private boolean isAngleAligned;
	private boolean isDistanceAligned;
	private final Chassis chassis;
	private ChassisMove chassisMove;
	private final CustomPIDController pidController;
	private final boolean stopWhenOnTarget;
	private final static double setpoint = 110;
	
	public GoalAlignDistance(Chassis chassis, Camera camera, double driveSpeed, double turnSpeed, boolean stopWhenOnTarget) {
		this.camera = camera;
		this.chassis = chassis;
		this.driveSpeed = driveSpeed;
		this.turnSpeed = turnSpeed;
		this.stopWhenOnTarget = stopWhenOnTarget;
		isAngleAligned = false;
		isDistanceAligned = false;
		pidController = new CustomPIDController(0.003, 0, 0.008, new CameraPIDSource(camera, PIDSourceType.kDisplacement));
		pidController.setAbsoluteTolerance(RobotMap.Constant.AutonomousMetric.ALIGN_TOLERANCE);
		pidController.setOutputRange(-1, 1);
		pidController.enable();
		pidController.setSetpoint(GoalAlignDistance.setpoint);
	}
	
	@Override
	public double getX() {
		return 0;
	}
	
	@Override
	public double getY() {
		double get = pidController.get();
		if (camera.getCameraData().canSeeGoal()) {
			LogKitten.wtf(get + "");
			isDistanceAligned = pidController.onTarget();
			return get;
		} else {
			// return Math.signum(get) * RobotMap.Constant.AutonomousMetric.ALIGN_SPEED;
			return 0;
		}
	}
	
	@Override
	public double getTurnSpeed() {
		isAngleAligned = true;
		return 0;
	}
	
	@Override
	protected void initialize() {
		chassisMove = new ChassisMove(chassis, this);
		chassisMove.start();
		pidController.enable();
		pidController.setSetpoint(GoalAlignDistance.setpoint);
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