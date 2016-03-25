package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.AutonomousMoveTime;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.commands.motor.MotorPositionConstant;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CrossRoughTerrainTime extends CommandGroup {
	public CrossRoughTerrainTime(Chassis chassis, boolean usePID) {
		addParallel(new RunAllSequential(new WaitCommand(0.5), new AutonomousMoveTime(chassis, RobotMap.Constant.AutonomousMetric.SPEED_ROUGH_TERRAIN, RobotMap.Constant.AutonomousMetric.TIME_ROUGH_TERRAIN, usePID)));
		addParallel(new MotorPositionConstant(RobotMap.Component.tim, (Tim.TIM_FULL_DOWN - Tim.TIM_FULL_UP) / 2));
	}
}
