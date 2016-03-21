package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.Kill;
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;
import org.usfirst.frc4904.standard.commands.motor.MotorControl;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;
import org.usfirst.frc4904.standard.humaninput.Driver;

public class Nathan extends Driver {
	public Nathan() {
		super("Nathan"); // supernathan!
	}
	
	@Override
	public void bindCommands() {
		RobotMap.HumanInput.Driver.xbox.back.whenPressed(new Kill(new ChassisIdle(RobotMap.Component.chassis)));
		(new MotorControl(RobotMap.Component.defenseManipulator, RobotMap.HumanInput.Driver.xbox, CustomXbox.RIGHT_Y_AXIS, RobotMap.Constant.HumanInput.DEFENSE_MANIPULATOR_SPEED_SCALE)).start();
	}
	
	@Override
	public double getX() {
		return 0;
	}
	
	@Override
	public double getY() {
		return (RobotMap.HumanInput.Driver.xbox.rt.getX() - RobotMap.HumanInput.Driver.xbox.lt.getX()) * RobotMap.Constant.HumanInput.Y_SPEED_SCALE;
	}
	
	@Override
	public double getTurnSpeed() {
		return RobotMap.HumanInput.Driver.xbox.leftStick.getX() * RobotMap.Constant.HumanInput.TURN_SPEED_SCALE;
	}
}