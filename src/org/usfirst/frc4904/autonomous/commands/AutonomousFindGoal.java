package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.standard.commands.chassis.ChassisConstant;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousFindGoal extends CommandGroup {
	protected Camera camera;
	private static final boolean GET_REAL_DATA = true;
	
	public AutonomousFindGoal(Chassis chassis, Camera camera, double searchSpeed, boolean usePID) {
		addSequential(new ChassisConstant(chassis, 0.0, 0.0, searchSpeed, Double.MAX_VALUE));
		this.camera = camera;
	}
	
	@Override
	public boolean isFinished() {
		if (!camera.getCameraData(AutonomousFindGoal.GET_REAL_DATA).isEmpty() && !camera.getCameraData(AutonomousFindGoal.GET_REAL_DATA).equals(RobotMap.Constant.Network.CONNECTION_ERROR_MESSAGE)) {
			return true;
		} else {
			return false;
		}
	}
}
