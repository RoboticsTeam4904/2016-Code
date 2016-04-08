package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Outtake extends CommandGroup {
	public Outtake() {
		super("Outtake");
		addParallel(new MotorConstant(RobotMap.Component.innie, RobotMap.Constant.OUTTAKE_MOTOR_SPEED));
		addParallel(new RockNRollerOuttake());
	}
}
