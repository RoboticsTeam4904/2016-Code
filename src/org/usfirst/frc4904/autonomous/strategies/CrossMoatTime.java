package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.AutonomousMoveTime;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.commands.motor.MotorPositionConstant;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossMoatTime extends CommandGroup {
	public CrossMoatTime(Chassis chassis, boolean usePID) {
		addSequential(new MotorPositionConstant(RobotMap.Component.tim, Tim.TIM_FULL_DOWN));
		addSequential(new AutonomousMoveTime(chassis, RobotMap.Constant.AutonomousMetric.SPEED_MOAT, RobotMap.Constant.AutonomousMetric.TIME_MOAT, usePID));
	}
}
