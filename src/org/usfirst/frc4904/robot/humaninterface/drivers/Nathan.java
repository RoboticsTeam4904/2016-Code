package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.TimSet;
import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.commands.Kill;
import org.usfirst.frc4904.standard.commands.chassis.ChassisIdle;
import org.usfirst.frc4904.standard.commands.chassis.ChassisMove;
import org.usfirst.frc4904.standard.commands.chassis.ChassisTurnAbsolute;
import org.usfirst.frc4904.standard.humaninput.Driver;

public class Nathan extends Driver {
	public Nathan() {
		super("Nathan"); // supernathan!
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
		return (RobotMap.HumanInput.Driver.xbox.rt.getX() - RobotMap.HumanInput.Driver.xbox.lt.getX()) * RobotMap.Constant.HumanInput.Y_SPEED_SCALE;
	}
	
	@Override
	public double getTurnSpeed() {
		return RobotMap.HumanInput.Driver.xbox.leftStick.getX() * RobotMap.Constant.HumanInput.TURN_SPEED_SCALE;
	}
}