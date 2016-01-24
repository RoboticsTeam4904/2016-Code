package org.usfirst.frc4904.robot.humaninterface.drivers;


import org.usfirst.frc4904.robot.DriverStationMap;
import org.usfirst.frc4904.standard.humaninterface.Driver;
import edu.wpi.first.wpilibj.command.Command;

public class Nathan extends Driver {
	public Nathan() {
		super("Nathan"); // supernathan!
	}
	
	public void bindCommands() {
		DriverStationMap.xbox.back.whenPressed(new Command(){

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
			}});
		//DriverStationMap.xbox.back.whenPressed(new Kill(new ChassisIdle(RobotMap.chassis)));
		//DriverStationMap.xbox.a.whenPressed(new ChassisShift(RobotMap.chassis.getShifter(), SolenoidShifters.ShiftState.DOWN));
		//DriverStationMap.xbox.b.whenPressed(new ChassisShift(RobotMap.chassis.getShifter(), SolenoidShifters.ShiftState.UP));
	}
	
	public double getX() {
		return 0;
	}
	
	public double getY() {
		return DriverStationMap.xbox.rt.getX() - DriverStationMap.xbox.lt.getX();
	}
	
	public double getTurnSpeed() {
		return DriverStationMap.xbox.leftStick.getX();
	}
}