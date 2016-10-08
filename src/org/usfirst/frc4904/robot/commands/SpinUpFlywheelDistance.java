package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.subsystems.motor.SensorMotor;
import edu.wpi.first.wpilibj.command.Command;

public class SpinUpFlywheelDistance extends Command {
	public final SensorMotor motor;
	public double motorSpeed;
	
	public SpinUpFlywheelDistance() {
		motor = RobotMap.Component.flywheel;
		motorSpeed = RobotMap.Constant.HumanInput.FLYWHEEL_TARGET_SPEED;
		requires(motor);
		setInterruptible(true);
	}
	
	@Override
	public void initialize() {
		motorSpeed = RobotMap.Constant.HumanInput.FLYWHEEL_TARGET_SPEED;
		motor.enablePID();
		motor.set(motorSpeed);
	}
	
	@Override
	public void execute() {
		motorSpeed = RobotMap.Constant.HumanInput.FLYWHEEL_TARGET_SPEED;
		motor.set(motorSpeed);
	}
	
	@Override
	public boolean isFinished() {
		return motor.onTarget();
	}
	
	@Override
	public void end() {
		motor.disablePID();
	}
	
	@Override
	public void interrupted() {
		end();
	}
}
