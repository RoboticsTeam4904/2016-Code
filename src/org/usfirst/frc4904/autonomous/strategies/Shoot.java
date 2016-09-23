package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.RockNRollerShoot;
import org.usfirst.frc4904.robot.commands.SpinUpFlywheel;
import org.usfirst.frc4904.standard.commands.motor.MotorIdle;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Shoot extends CommandGroup {
	public Shoot() {
		addParallel(new SpinUpFlywheel());
		addSequential(new WaitCommand(RobotMap.Constant.AutonomousMetric.FLYWHEEL_SPINUP_DURATION));
		addSequential(new RockNRollerShoot());
		addParallel(new MotorIdle(RobotMap.Component.flywheel));
	}
}