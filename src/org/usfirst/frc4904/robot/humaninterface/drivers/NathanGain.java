package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.RobotMap;

public class NathanGain extends Nathan {
	public NathanGain() {
		super("NathanGain");
	}
	
	protected double scaleGain(double input, double gain, double exp) {
		return Math.pow(input, exp) * gain * Math.signum(input);
	}
	
	@Override
	public double getY() {
		double rawSpeed = RobotMap.HumanInput.Driver.xbox.rt.getX() - RobotMap.HumanInput.Driver.xbox.lt.getX();
		double speed = scaleGain(rawSpeed, RobotMap.Constant.HumanInput.SPEED_GAIN, RobotMap.Constant.HumanInput.SPEED_EXP) * RobotMap.Constant.HumanInput.Y_SPEED_SCALE;
		return speed;
	}
	
	@Override
	public double getTurnSpeed() {
		double rawTurnSpeed = RobotMap.HumanInput.Driver.xbox.leftStick.getX();
		double turnSpeed = scaleGain(rawTurnSpeed, RobotMap.Constant.HumanInput.TURN_GAIN, RobotMap.Constant.HumanInput.TURN_EXP) * RobotMap.Constant.HumanInput.TURN_SPEED_SCALE;
		return turnSpeed;
	}
}