package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.autonomous.commands.TimLowbar;
import org.usfirst.frc4904.autonomous.commands.TimUp;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.Kill;
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;
import org.usfirst.frc4904.standard.commands.motor.MotorControl;
import org.usfirst.frc4904.standard.humaninput.Driver;

public class Nathan extends Driver {
	public Nathan() {
		super("Nathan"); // supernathan!
	}
	
	@Override
	public void bindCommands() {
		RobotMap.HumanInput.Driver.xbox.back.whenPressed(new Kill(new ChassisIdle(RobotMap.Component.chassis)));
		RobotMap.HumanInput.Driver.xbox.a.onlyWhileHeld(new TimLowbar(RobotMap.Component.tim, RobotMap.Component.timEncoder));
		RobotMap.HumanInput.Driver.xbox.a.whenReleased(new MotorControl(RobotMap.Component.tim, RobotMap.HumanInput.Driver.xbox, RobotMap.Constant.HumanInput.XBOX_360_RIGHT_STICK_Y, false));
		RobotMap.HumanInput.Driver.xbox.b.onlyWhileHeld(new TimUp(RobotMap.Component.tim, RobotMap.Component.timEncoder));
		RobotMap.HumanInput.Driver.xbox.b.whenReleased(new MotorControl(RobotMap.Component.tim, RobotMap.HumanInput.Driver.xbox, RobotMap.Constant.HumanInput.XBOX_360_RIGHT_STICK_Y, false));
		// TODO: If we switch to Xbox Ones, change the port to the Xbox One port. The Xbox 360 has a slightly different mapping, so we have a special comment for that.
		(new MotorControl(RobotMap.Component.tim, RobotMap.HumanInput.Driver.xbox, RobotMap.Constant.HumanInput.XBOX_360_RIGHT_STICK_Y, false)).start();
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