package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.AutonomousMoveDistance;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.commands.RunFor;
import org.usfirst.frc4904.standard.commands.motor.MotorPositionConstant;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossPortcullis extends CommandGroup {
	public CrossPortcullis(Chassis chassis, boolean usePID) {
		addSequential(new MotorPositionConstant(RobotMap.Component.tim, Tim.TIM_FULL_DOWN, true));
		addParallel(new MotorPositionConstant(RobotMap.Component.tim, Tim.TIM_FULL_DOWN, false));
		addSequential(new AutonomousMoveDistance(chassis, 10, RobotMap.Constant.AutonomousMetric.DRIVE_SPEED, usePID, new CustomEncoder[] {RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder}));
		addSequential(new MotorPositionConstant(RobotMap.Component.tim, Tim.TIM_FULL_UP, true));
		addParallel(new MotorPositionConstant(RobotMap.Component.tim, Tim.TIM_FULL_UP, false));
		addSequential(new RunFor(new MotorPositionConstant(RobotMap.Component.tim, Tim.TIM_FULL_UP), 4));
		addSequential(new AutonomousMoveDistance(chassis, 60, RobotMap.Constant.AutonomousMetric.DRIVE_SPEED, usePID, new CustomEncoder[] {RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder}));
	}
}
