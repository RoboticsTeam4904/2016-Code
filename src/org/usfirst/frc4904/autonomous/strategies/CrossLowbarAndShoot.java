package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.AutonomousAlignWithGoal;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowbarAndShoot extends CommandGroup {
	public CrossLowbarAndShoot(Chassis chassis, boolean usePID) {
		addSequential(new CrossLowbar(chassis, usePID));
		addSequential(new AutonomousAlignWithGoal(chassis, RobotMap.Constant.Network.PI_IR_IP, RobotMap.Constant.Network.PI_IR_PORT, usePID));
		addSequential(new Shoot());
		// addParallel(new HoodUp());
		// TODO: Align with goal
		// addParallel(new SpinUpFlywheel());
		// addSequential(new WaitCommand(1));
	}
}
