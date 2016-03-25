package org.usfirst.frc4904.robot.commands.camera;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.robot.subsystems.Camera.CameraData;
import org.usfirst.frc4904.robot.subsystems.Camera.CameraStatus;
import org.usfirst.frc4904.standard.LogKitten;
import edu.wpi.first.wpilibj.command.Command;

public class CameraPoll extends Command {
	public static final double MINIMUM_POLL_BREAK = 30;
	protected Long lastTime;
	protected URL cameraURL;
	protected final Camera camera;
	protected Future future = null;
	protected final ExecutorService threadPool = Executors.newFixedThreadPool(1);
	protected List<CameraData> dataArchive = new ArrayList<CameraData>();
	
	public CameraPoll(String name, Camera camera) {
		super(name);
		LogKitten.wtf("CameraPoll Constructed");
		requires(camera);
		this.camera = camera;
		try {
			cameraURL = new URL(camera.getCameraProtocol(), camera.getCameraIP(), camera.getCameraPort(), camera.getCameraPath());
		}
		catch (MalformedURLException e) {
			LogKitten.ex(e);
		}
		setRunWhenDisabled(true);
		setInterruptible(false);
	}
	
	public CameraPoll(Camera camera) {
		this("CameraPoll", camera);
	}
	
	@Override
	protected void initialize() {
		LogKitten.v("Camera is starting to poll...");
		lastTime = System.currentTimeMillis();
	}
	
	@Override
	protected void execute() {
		if (future != null) {
			if (future.isDone()) {
				try {
					CameraData fetchedData = (CameraData) future.get();
					dataArchive.add(fetchedData);
					camera.setCameraData(fetchedData);
					future = null;
				}
				catch (InterruptedException | ExecutionException e) {
					LogKitten.ex(e);
				}
			}
		} else {
			CameraPollRunner task = new CameraPollRunner(cameraURL);
			future = threadPool.submit(task);
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void interrupted() {
		for (CameraData archiveItem : dataArchive) {
			LogKitten.i(archiveItem.toString());
		}
	}
	
	public static class CameraPollRunner implements Callable<CameraData> {
		private final URL cameraURL;
		
		public CameraPollRunner(URL cameraURL) {
			this.cameraURL = cameraURL;
		}
		
		private CameraData fetchData(URL url) throws IOException {
			CameraData dataFetched = new CameraData();
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(RobotMap.Constant.Network.CONNECTION_METHOD_GET);
			connection.setRequestProperty("UserAgent", "CAMERA-SUBSYSTEM");
			int responseCode = connection.getResponseCode();
			LogKitten.v("Camera poll request: " + url);
			LogKitten.v("Camera poll response (code): " + responseCode);
			if (responseCode == 200) {
				dataFetched.setCameraStatus(CameraStatus.CONNECTED);
				BufferedReader connectionReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuffer responseBuffer = new StringBuffer();
				String input;
				while ((input = connectionReader.readLine()) != null) {
					responseBuffer.append(input);
				}
				connectionReader.close();
				String response = responseBuffer.toString().trim();
				LogKitten.v("Camera poll response (total): " + response);
				dataFetched.setTotalData(response);
				String[] cameraVariables = response.split("::");
				if (cameraVariables.length == 3) {
					boolean canSeeGoal = cameraVariables[0].equals(RobotMap.Constant.Network.PI_IR_STATUS_GOOD) ? true : false;
					LogKitten.v("Got camera vision status of " + canSeeGoal);
					Double degreesToTurn = Math.toDegrees(Double.parseDouble(cameraVariables[1])) + RobotMap.Constant.AutonomousMetric.INFRARED_ADJUSTMENT;
					LogKitten.v("Got radians to turn of " + cameraVariables[1] + " and degrees to turn of " + degreesToTurn);
					Double distanceToMove = Double.parseDouble(cameraVariables[2]);
					LogKitten.v("Got distance to move of " + cameraVariables[2] + " and rounded of " + distanceToMove);
					dataFetched.setDistanceToMove(distanceToMove);
					dataFetched.setCanSeeGoal(canSeeGoal);
					dataFetched.setDegreesToTurn(degreesToTurn);
				} else {
					LogKitten.e("Got " + cameraVariables.length + " parameters from IR Camera, expected 3");
					LogKitten.e("Camera Data: " + response);
					LogKitten.e("Camera Variables: " + cameraVariables);
					dataFetched.setCameraStatus(CameraStatus.ERRDATA);
				}
			} else {
				dataFetched.setCameraStatus(CameraStatus.DISCONNECTED);
				LogKitten.e("Could not establish connection to camera using request '" + url.toString() + "'");
			}
			return dataFetched;
		}
		
		@Override
		public CameraData call() throws Exception {
			CameraData cameraData = fetchData(cameraURL);
			return cameraData;
		}
	}
}