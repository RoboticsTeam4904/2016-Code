package org.usfirst.frc4904.robot.commands.camera;


import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.robot.subsystems.Camera.CameraData;
import org.usfirst.frc4904.standard.LogKitten;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

public class CameraPoll extends Command {
	protected final Camera camera;
	protected final NetworkTable server;
	
	public CameraPoll(String name, Camera camera) {
		super(name);
		requires(camera);
		this.camera = camera;
		server = NetworkTable.getTable("RoboRealm");
		setRunWhenDisabled(true);
		setInterruptible(false);
	}
	
	public CameraPoll(Camera camera) {
		this("CameraPoll", camera);
	}
	
	@Override
	protected void initialize() {
		LogKitten.v("Camera is starting to poll...");
	}
	
	@Override
	protected void execute() {
		double goalX = 0;
		double goalY = 0;
		try {
			goalX = server.getNumber("goalX", 0);
			goalY = server.getNumber("goalY", 0);
		}
		catch (TableKeyNotDefinedException ex) {
			LogKitten.ex(ex);
			return;
		}
		CameraData fetchedData;
		if (goalX == 0 && goalY == 0) {
			fetchedData = new CameraData();
		} else {
			fetchedData = new CameraData(goalX, goalY);
		}
		camera.setCameraData(fetchedData);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {}
	
	@Override
	protected void interrupted() {}
}