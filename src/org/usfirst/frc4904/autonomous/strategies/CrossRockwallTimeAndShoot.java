package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.AutonomousPositionToGoal;
import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

@Deprecated
public class CrossRockwallTimeAndShoot extends CommandGroup {
	public CrossRockwallTimeAndShoot(Chassis chassis, Camera camera, boolean usePID) {
		addSequential(new CrossRockwallTime(chassis, usePID));
		addSequential(new AutonomousPositionToGoal(chassis, camera, usePID));
		addSequential(new Shoot());
	}
}
