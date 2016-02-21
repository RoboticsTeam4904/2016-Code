package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.Util;
import edu.wpi.first.wpilibj.command.Command;

public class TimAutoCalibrate extends Command {
	protected boolean hasStartedSweep;
	protected boolean isCalibrated;
	
	public TimAutoCalibrate() {
		super("TimDefault");
		requires(RobotMap.Component.tim);
		setInterruptible(false);
		isCalibrated = false;
		hasStartedSweep = false;
	}
	
	@Override
	protected void initialize() {
		RobotMap.Component.tim.set(RobotMap.Constant.TIM_CALIBRATION_SWEEP_SPEED);
	}
	
	@Override
	protected void execute() {
		// If the sweep hasn't started, wait for it to start
		if (!hasStartedSweep) {
			RobotMap.Component.tim.setOverride(-0.05, true);
			hasStartedSweep = true;
			return;
		}
		// If we're calibrated (meaning the sweep has ended), stop
		if (isCalibrated) {
			RobotMap.Component.tim.set(0);
			return;
		}
		// If the encoder reads zero but we've already started sweeping (because the earlier if didn't return) then it auto-calibrated, so stop
		if (Util.isZero(RobotMap.Component.timEncoder.get())) {
			RobotMap.Component.tim.set(0);
			isCalibrated = true;
			return;
		}
		// If we are sweeping, sweep
		if (hasStartedSweep && !isCalibrated) {
			RobotMap.Component.tim.setOverride(-0.05, true);
		}
	}
	
	@Override
	protected boolean isFinished() {
		return isCalibrated;
	}
	
	@Override
	protected void end() {}
	
	@Override
	protected void interrupted() {}
}
