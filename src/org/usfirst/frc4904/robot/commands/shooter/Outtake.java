package org.usfirst.frc4904.robot.commands.shooter;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.commands.motor.MotorConstant;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Outtake extends CommandGroup {
	public Outtake() {
		super("Outtake");
		addParallel(new MotorConstant(RobotMap.Component.intakeRoller, -1));
		addParallel(new RockNRollerOuttake());
	}
}
