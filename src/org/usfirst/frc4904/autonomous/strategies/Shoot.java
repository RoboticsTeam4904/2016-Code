package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.RockNRollerShoot;
import org.usfirst.frc4904.robot.commands.SpinUpFlywheel;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.commands.RunFor;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Shoot extends CommandGroup {
	public Shoot() {
		addParallel(new RunFor(new SpinUpFlywheel(), RobotMap.Constant.AutonomousMetric.FLYWHEEL_SPINUP_DURATION + 1)); // spin up the flywheel (an extra second after the shot)
		addSequential(new RunAllSequential(new WaitCommand(RobotMap.Constant.AutonomousMetric.FLYWHEEL_SPINUP_DURATION), new RockNRollerShoot())); // wait for the flywheel to spin up and then shoot
	}
}