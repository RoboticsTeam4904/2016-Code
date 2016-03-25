package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.Kill;
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;
import org.usfirst.frc4904.standard.commands.motor.MotorControl;
import org.usfirst.frc4904.standard.humaninput.Driver;

public class NathanGain extends Driver {
	public NathanGain() {
		super("NathanGain");
	}
	
	protected double scaleGain(double input, double gain, double exp) {
		return Math.pow(input, exp) * gain * Math.signum(input);
	}
	
	@Override
	public void bindCommands() {
		RobotMap.HumanInput.Driver.xbox.back.whenPressed(new Kill(new ChassisIdle(RobotMap.Component.chassis)));
		// TODO: If we switch to Xbox Ones, change the port to the Xbox One port. The Xbox 360 has a slightly different mapping, so we have a special comment for that.
		(new MotorControl(RobotMap.Component.tim, RobotMap.HumanInput.Driver.xbox, RobotMap.HumanInput.Driver.xbox360RightAxisY, false)).start();
	}
	
	@Override
	public double getX() {
		return 0;
	}
	
	@Override
	public double getY() {
		double rawSpeed = RobotMap.HumanInput.Driver.xbox.rt.getX() - RobotMap.HumanInput.Driver.xbox.lt.getX();
		double speed = scaleGain(rawSpeed, RobotMap.Constant.HumanInput.SPEED_GAIN, RobotMap.Constant.HumanInput.SPEED_EXP) * RobotMap.Constant.HumanInput.TURN_SPEED_SCALE;
		return speed;
	}
	
	@Override
	public double getTurnSpeed() {
		double rawTurnSpeed = RobotMap.HumanInput.Driver.xbox.leftStick.getX();
		double turnSpeed = scaleGain(rawTurnSpeed, RobotMap.Constant.HumanInput.TURN_GAIN, RobotMap.Constant.HumanInput.TURN_EXP) * RobotMap.Constant.HumanInput.Y_SPEED_SCALE;
		return turnSpeed;
	}
}