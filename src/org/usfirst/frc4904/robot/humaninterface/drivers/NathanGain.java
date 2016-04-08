package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.TimSet;
import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.commands.Kill;
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurnAbsolute;
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
		RobotMap.HumanInput.Driver.xbox.a.onlyWhileHeld(new TimSet(Tim.TimState.LOWBAR, false));
		RobotMap.HumanInput.Driver.xbox.dPad.left.whenPressed(new ChassisTurnAbsolute(RobotMap.Component.chassis, 45, RobotMap.Component.imu, RobotMap.MotionControl.chassisTurnMC));
		RobotMap.HumanInput.Driver.xbox.dPad.left.whenReleased(new ChassisMove(RobotMap.Component.chassis, this));
		RobotMap.HumanInput.Driver.xbox.dPad.right.whenPressed(new ChassisTurnAbsolute(RobotMap.Component.chassis, -45, RobotMap.Component.imu, RobotMap.MotionControl.chassisTurnMC));
		RobotMap.HumanInput.Driver.xbox.dPad.right.whenReleased(new ChassisMove(RobotMap.Component.chassis, this));
		RobotMap.HumanInput.Driver.xbox.dPad.up.whenPressed(new ChassisTurnAbsolute(RobotMap.Component.chassis, 0, RobotMap.Component.imu, RobotMap.MotionControl.chassisTurnMC));
		RobotMap.HumanInput.Driver.xbox.dPad.up.whenReleased(new ChassisMove(RobotMap.Component.chassis, this));
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