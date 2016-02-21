package org.usfirst.frc4904.robot.commands.shooter;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.standard.LogKitten;
import edu.wpi.first.wpilibj.command.Command;

public class CameraPoll extends Command {
	protected Integer cameraPort;
	protected String cameraIP;
	protected Long lastTime;
	protected URI cameraURI;
	protected Camera camera;
	
	public CameraPoll(String name, Camera camera) {
		super(name);
		requires(camera);
		this.camera = camera;
		cameraIP = camera.getCameraIP();
		cameraPort = camera.getCameraPort();
		try {
			cameraURI = new URI(cameraIP + String.valueOf(cameraPort) + camera.getCameraPath());
		}
		catch (URISyntaxException e) {
			LogKitten.e(e.getStackTrace().toString());
		}
	}
	
	public CameraPoll(Camera camera) {
		this("CameraPoll", camera);
	}
	
	@Override
	protected void initialize() {
		LogKitten.i("Camera is starting to poll ");
	}
	
	@Override
	protected void execute() {
		try {
			HttpURLConnection connection = (HttpURLConnection) cameraURI.toURL().openConnection();
			connection.setRequestMethod(RobotMap.Constant.Network.CONNECTION_METHOD_GET);
			connection.setRequestProperty("UserAgent", "CAMERA-SUBSYSTEM");
			int responseCode = connection.getResponseCode();
			LogKitten.v("Camera poll request: " + cameraURI.toURL());
			LogKitten.v("Camera poll response (code): " + responseCode);
			if (responseCode == 200) {
				camera.setCameraStatus(Camera.CameraStatus.CONNECTED);
				BufferedReader connectionReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuffer responseBuffer = new StringBuffer();
				String input;
				while ((input = connectionReader.readLine()) != null) {
					responseBuffer.append(input);
				}
				connectionReader.close();
				String response = responseBuffer.toString().trim();
				LogKitten.v("Camera poll response (total): " + response);
				camera.setCameraData(response);
			} else {
				camera.setCameraStatus(Camera.CameraStatus.DISCONNECTED);
				camera.setCameraData(RobotMap.Constant.Network.CONNECTION_ERROR_MESSAGE);
				LogKitten.e("Could not establish connection to camera using request '" + cameraURI.toURL().toString() + "'");
			}
		}
		catch (MalformedURLException e) {
			LogKitten.e(e.getStackTrace().toString());
		}
		catch (IOException e) {
			LogKitten.e(e.getStackTrace().toString());
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
	}
}
