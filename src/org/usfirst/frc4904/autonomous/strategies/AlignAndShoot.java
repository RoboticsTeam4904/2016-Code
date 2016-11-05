package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.AutonomousPositionToGoal;
import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AlignAndShoot extends CommandGroup {
	public AlignAndShoot(Chassis chassis, Camera camera, boolean usePID) {
		addSequential(new AutonomousPositionToGoal(chassis, camera, usePID));
		addSequential(new Shoot());
	}
}