package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.AutonomousMoveDistance;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.shooter.HoodDown;
import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.commands.RunAllSequential;
import org.usfirst.frc4904.standard.commands.motor.MotorPositionConstant;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CrossLowbarDistance extends CommandGroup {
	public CrossLowbarDistance(Chassis chassis, boolean usePID) {
		double tickDistance = (RobotMap.Constant.FieldMetric.DISTANCE_TO_LOW_BAR / RobotMap.Constant.RobotMetric.WHEEL_CIRCUMFERENCE) * RobotMap.Constant.RobotMetric.WHEEL_ENCODER_PPR;
		addParallel(new HoodDown());
		RobotMap.Component.leftWheelEncoder.reset();
		RobotMap.Component.rightWheelEncoder.reset();
		addSequential(new MotorPositionConstant(RobotMap.Component.tim, (Tim.TIM_FULL_DOWN - Tim.TIM_FULL_UP) / 2));
		addParallel(new RunAllSequential(new WaitCommand(0.5), new AutonomousMoveDistance(chassis, tickDistance, RobotMap.Constant.AutonomousMetric.SPEED_LOWBAR, usePID, new CustomEncoder[] {RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder})));
	}
}
