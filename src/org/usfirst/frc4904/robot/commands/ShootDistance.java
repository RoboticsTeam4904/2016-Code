package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootDistance extends CommandGroup {
	public ShootDistance() {
		SpinUpFlywheelDistance s = new SpinUpFlywheelDistance();
		addSequential(s);
		addParallel(new MotorConstant(RobotMap.Component.flywheel, s.motorSpeed));
		addParallel(new RockNRollerShoot());
	}
}
