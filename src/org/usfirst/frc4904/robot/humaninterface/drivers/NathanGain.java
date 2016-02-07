package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.humaninput.Driver;
import edu.wpi.first.wpilibj.command.Command;

public class NathanGain extends Driver {
	public NathanGain() {
		super("NathanGain"); // supernathan!
	}
	
	public void bindCommands() {
		RobotMap.HumanInput.Driver.xbox.back.whenPressed(new Command() {
			@Override
			protected void end() {
				// TODO Auto-generated method stub
			}
			
			@Override
			protected void execute() {
				System.out.println("TODO: Kill robot here");
			}
			
			@Override
			protected void initialize() {
				// TODO Auto-generated method stub
			}
			
			@Override
			protected void interrupted() {
				// TODO Auto-generated method stub
			}
			
			@Override
			protected boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		// DriverStationMap.xbox.back.whenPressed(new Kill(new ChassisIdle(RobotMap.chassis)));
		// DriverStationMap.xbox.a.whenPressed(new ChassisShift(RobotMap.chassis.getShifter(), SolenoidShifters.ShiftState.DOWN));
		// DriverStationMap.xbox.b.whenPressed(new ChassisShift(RobotMap.chassis.getShifter(), SolenoidShifters.ShiftState.UP));
	}
	
	public double getX() {
		return 0;
	}
	
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