package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.SmartDashboardKey;
import org.usfirst.frc4904.robot.subsystems.AligningCamera;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import org.usfirst.frc4904.standard.custom.ChassisController;
import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearAlign extends Command implements ChassisController {
	
	protected RobotMap robot;
	protected AligningCamera camera;
	protected CustomPIDController pidController;
	protected static final double angleP = 0;
	protected static final double angleI = 0;
	protected static final double angleD = 0;
	protected boolean onAngle = false;
	private ChassisMove chassisMove;
	
	public GearAlign(RobotMap robot, AligningCamera camera) {
		this.robot = robot;
		this.camera = camera;
		pidController = new CustomPIDController(RobotMap.Constant.AutonomousMetric.ALIGN_P, RobotMap.Constant.AutonomousMetric.ALIGN_I, RobotMap.Constant.AutonomousMetric.ALIGN_D, camera);
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
		double pidGet = pidController.get();
		if (camera.isVisible()) {
			onAngle = pidController.onTarget();
			return pidGet;
		}
		return 0;
	}
	
	@Override
	public void initialize() {
		chassisMove = new ChassisMove(RobotMap.Component.chassis, this);
		chassisMove.start();
		pidController.enable();
		pidController.setSetpoint(0); // Potentially change this to be the horizontal resolution of the camera / 2
	}
	
	@Override
	public void execute() {
		SmartDashboard.putNumber(SmartDashboardKey.ALIGN_E.key, pidController.getError());
	}
	
	@Override
	public boolean isFinished() {
		return onAngle;
	}
	
	@Override
	public void end() {
		chassisMove.cancel();
		pidController.reset();
		onAngle = false;
	}
	
	@Override
	public void interrupted() {
		end();
	}
	
	public void updatePID() {
		pidController.setPID(RobotMap.Constant.AutonomousMetric.ALIGN_P, RobotMap.Constant.AutonomousMetric.ALIGN_I, RobotMap.Constant.AutonomousMetric.ALIGN_D);
	}
}
