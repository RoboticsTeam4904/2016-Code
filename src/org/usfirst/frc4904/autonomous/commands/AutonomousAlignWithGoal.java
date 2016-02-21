package org.usfirst.frc4904.autonomous.commands;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.standard.LogKitten;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousAlignWithGoal extends CommandGroup {
	public AutonomousAlignWithGoal(Chassis chassis, String irCameraIP, int irCameraPort, boolean usePID) {
		String irCameraData = null;
		Socket socket = null;
		try {
			socket = new Socket(irCameraIP, irCameraPort);
			BufferedReader irCameraReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// String format should be DEGREES::DISTANCE
			// Example: 15.5::42.5
			irCameraData = irCameraReader.readLine();
			if (socket != null) {
				socket.close();
			}
		}
		catch (Exception e) {
			LogKitten.e(e.getMessage());
		}
		if (irCameraData != null) {
			String[] amountsToChange = irCameraData.split("::");
			if (amountsToChange.length == 2) {
				Integer degreesToTurn = (int) Math.round(Math.toDegrees(Double.parseDouble(amountsToChange[0])));
				Double distanceToMove = Double.parseDouble(amountsToChange[1]);
				addSequential(new ChassisTurnDegrees(chassis, RobotMap.Constant.RobotMetric.LENGTH, RobotMap.Constant.RobotMetric.WIDTH, RobotMap.Constant.RobotMetric.WHEEL_ENCODER_PPR, RobotMap.Constant.RobotMetric.WHEEL_DIAMETER, degreesToTurn, RobotMap.Constant.AutonomousMetric.DRIVE_SPEED, usePID, new CustomEncoder[] {RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder}));
				addSequential(new AutonomousMoveDistance(chassis, distanceToMove, RobotMap.Constant.AutonomousMetric.DRIVE_SPEED, usePID, new CustomEncoder[] {RobotMap.Component.leftWheelEncoder, RobotMap.Component.rightWheelEncoder}));
			} else {
				LogKitten.wtf("Got " + amountsToChange.length + " parameters from IR Camera, expected 2");
				LogKitten.wtf("IR camera data: " + irCameraData);
				LogKitten.wtf("Amounts to change: " + amountsToChange);
			}
		}
	}
}
