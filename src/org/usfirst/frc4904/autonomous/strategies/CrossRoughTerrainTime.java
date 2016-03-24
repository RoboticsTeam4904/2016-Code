package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.AutonomousMoveTime;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.commands.motor.MotorPositionConstant;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossRoughTerrainTime extends CommandGroup {
	public CrossRoughTerrainTime(Chassis chassis, boolean usePID) {
		addSequential(new MotorPositionConstant(RobotMap.Component.tim, Tim.TIM_FULL_DOWN));
		addSequential(new AutonomousMoveTime(chassis, RobotMap.Constant.AutonomousMetric.SPEED_ROUGH_TERRAIN, RobotMap.Constant.AutonomousMetric.TIME_ROUGH_TERRAIN, usePID));
	}
}
