package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.shooter.HoodUp;
import org.usfirst.frc4904.robot.commands.shooter.Shoot;
import org.usfirst.frc4904.robot.commands.shooter.SpinUpFlywheel;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CrossLowbarAndShoot extends CommandGroup {
	public CrossLowbarAndShoot(Chassis chassis, boolean usePID) {
		addSequential(new CrossLowbar(chassis, usePID));
		addParallel(new HoodUp(RobotMap.Component.hood));
		// TODO: Align with goal
		addParallel(new SpinUpFlywheel());
		addSequential(new WaitCommand(1));
		addSequential(new Shoot());
	}
}
