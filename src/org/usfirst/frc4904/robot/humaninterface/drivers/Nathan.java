package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.Kill;
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;
import org.usfirst.frc4904.standard.humaninput.Driver;

public class Nathan extends Driver {
	public Nathan() {
		super("Nathan"); // supernathan!
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
		return RobotMap.HumanInput.Driver.xbox.rt.getX() - RobotMap.HumanInput.Driver.xbox.lt.getX();
	}
	
	@Override
	public double getTurnSpeed() {
		return RobotMap.HumanInput.Driver.xbox.leftStick.getX();
	}
}