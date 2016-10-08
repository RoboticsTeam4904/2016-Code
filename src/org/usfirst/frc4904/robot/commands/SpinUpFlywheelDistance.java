package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.custom.sensors.DistanceSensor;
import org.usfirst.frc4904.standard.subsystems.motor.SensorMotor;
import edu.wpi.first.wpilibj.command.Command;

public class SpinUpFlywheelDistance extends Command {
	public final SensorMotor motor;
	public final DistanceSensor distanceSensor;
	public double motorSpeed;
	protected final boolean endOnArrival;
	
	public SpinUpFlywheelDistance(boolean endOnArrival) {
		motor = RobotMap.Component.flywheel;
		motorSpeed = RobotMap.Constant.HumanInput.FLYWHEEL_TARGET_SPEED;
		distanceSensor = RobotMap.Component.ultrasonicSensor;
		requires(motor);
		setInterruptible(true);
		this.endOnArrival = endOnArrival;
	}
	
	protected double calculatePower() {
		return RobotMap.Constant.HumanInput.FLYWHEEL_TARGET_SPEED * distanceSensor.getDistance() / 200.0;
	}
	
	@Override
	public void initialize() {
		motorSpeed = calculatePower();
		motor.enablePID();
		motor.set(motorSpeed);
	}
	
	@Override
	public void execute() {
		motorSpeed = calculatePower();
		motor.set(motorSpeed);
	}
	
	@Override
	public boolean isFinished() {
		return motor.onTarget() && endOnArrival;
	}
	
	@Override
	public void end() {
		motor.disablePID();
		motor.reset();
	}
	
	@Override
	public void interrupted() {
		end();
	}
}
