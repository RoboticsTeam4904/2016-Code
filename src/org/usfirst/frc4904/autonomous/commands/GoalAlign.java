package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoalAlign extends CommandGroup {
	public GoalAlign(Chassis chassis, Camera camera, boolean usePID) {
		addSequential(new ChassisTurnDegrees(chassis, RobotMap.Constant.RobotMetric.LENGTH, RobotMap.Constant.RobotMetric.WIDTH, RobotMap.Constant.RobotMetric.WHEEL_ENCODER_PPR, RobotMap.Constant.RobotMetric.WHEEL_DIAMETER, RobotMap.Component.cameraIR.getCameraData(true).getDegreesToTurn(), RobotMap.Constant.AutonomousMetric.DRIVE_SPEED, usePID, new CustomEncoder[] {RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder}));
		addSequential(new AutonomousMoveDistance(chassis, RobotMap.Component.cameraIR.getCameraData(true).getDistanceToMove(), RobotMap.Constant.AutonomousMetric.DRIVE_SPEED, usePID, new CustomEncoder[] {RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder}));
	}
}