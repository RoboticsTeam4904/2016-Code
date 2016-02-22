package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AlignWithGoal extends CommandGroup {
	public AlignWithGoal(Chassis chassis, Camera camera, boolean usePID) {
		String irCameraData = camera.getCameraData(true);
		// STRING FORMAT = <Sees a goal?>::<Radians to turn to goal>::<Distance to travel to goal>
		String[] amountsToChange = irCameraData.split("::");
		if (amountsToChange.length == 3) {
			// This should be 1 if it can see a camera or 0 if it can't.
			String status = amountsToChange[0];
			LogKitten.v("Got camera status of " + status);
			Integer degreesToTurn = (int) Math.round(Math.toDegrees(Double.parseDouble(amountsToChange[1])));
			LogKitten.v("Got radians to turn of " + amountsToChange[1] + " and degrees to turn of " + degreesToTurn);
			Double distanceToMove = Double.parseDouble(amountsToChange[2]);
			LogKitten.v("Got distance to move of " + amountsToChange[2] + " and rounded of " + distanceToMove);
			addSequential(new ChassisTurnDegrees(chassis, RobotMap.Constant.RobotMetric.LENGTH, RobotMap.Constant.RobotMetric.WIDTH, RobotMap.Constant.RobotMetric.WHEEL_ENCODER_PPR, RobotMap.Constant.RobotMetric.WHEEL_DIAMETER, degreesToTurn, RobotMap.Constant.AutonomousMetric.DRIVE_SPEED, usePID, new CustomEncoder[] {RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder}));
			addSequential(new AutonomousMoveDistance(chassis, distanceToMove, RobotMap.Constant.AutonomousMetric.DRIVE_SPEED, usePID, new CustomEncoder[] {RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder}));
		} else {
			LogKitten.wtf("Got " + amountsToChange.length + " parameters from IR Camera, expected 2");
			LogKitten.wtf("IR camera data: " + irCameraData);
			LogKitten.wtf("Amounts to change: " + amountsToChange);
		}
	}
}
