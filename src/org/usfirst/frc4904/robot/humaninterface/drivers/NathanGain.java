package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.Kill;
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;
import org.usfirst.frc4904.standard.humaninput.Driver;

public class NathanGain extends Driver {
	public NathanGain() {
		super("NathanGain");
	}
	
	@Override
	public void bindCommands() {
		RobotMap.HumanInput.Driver.xbox.back.whenPressed(new Kill(new ChassisIdle(RobotMap.Component.chassis)));
	}
	
	@Override
	public double getX() {
		return 0;
	}
	
	@Override
	public double getY() {
		double speed = RobotMap.HumanInput.Driver.xbox.rt.getX() - RobotMap.HumanInput.Driver.xbox.lt.getX();
		if (speed < 0) {
			speed = Math.pow(speed, RobotMap.Constant.HumanInput.SPEED_EXP);
			speed *= -RobotMap.Constant.HumanInput.SPEED_GAIN;
		} else {
			speed = Math.pow(speed, RobotMap.Constant.HumanInput.SPEED_EXP);
			speed *= RobotMap.Constant.HumanInput.SPEED_GAIN;
		}
		return speed;
	}
	
	@Override
	public double getTurnSpeed() {
		double turnSpeed = RobotMap.HumanInput.Driver.xbox.leftStick.getX();
		if (turnSpeed < 0) {
			turnSpeed = Math.pow(turnSpeed, RobotMap.Constant.HumanInput.TURN_EXP);
			turnSpeed *= -RobotMap.Constant.HumanInput.TURN_GAIN;
		} else {
			turnSpeed = Math.pow(turnSpeed, RobotMap.Constant.HumanInput.TURN_EXP);
			turnSpeed *= RobotMap.Constant.HumanInput.TURN_GAIN;
		}
		return turnSpeed;
	}
}