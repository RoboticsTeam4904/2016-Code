package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.sensors.BallLoadSensor;
import org.usfirst.frc4904.robot.subsystems.Camera;
import org.usfirst.frc4904.robot.subsystems.Flywheel;
import org.usfirst.frc4904.robot.subsystems.Hood;
import org.usfirst.frc4904.robot.subsystems.Innie;
import org.usfirst.frc4904.robot.subsystems.RockNRoller;
import org.usfirst.frc4904.robot.subsystems.Shooter;
import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;
import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import org.usfirst.frc4904.standard.custom.sensors.CANEncoder;
import org.usfirst.frc4904.standard.custom.sensors.CANTalonEncoder;
import org.usfirst.frc4904.standard.custom.sensors.CANUltrasonicDistanceSensor;
import org.usfirst.frc4904.standard.custom.sensors.DistanceSensor;
import org.usfirst.frc4904.standard.custom.sensors.PDP;
import org.usfirst.frc4904.standard.subsystems.chassis.TankDrive;
import org.usfirst.frc4904.standard.subsystems.motor.PositionEncodedMotor;
import org.usfirst.frc4904.standard.subsystems.motor.VelocityEncodedMotor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.AccelerationCap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static class Port {
		public static class HumanInput {
			public static final int joystick = 0;
			public static final int xboxController = 1;
		}
		
		public static class PWM {
			public static final int leftDriveAMotor = 0;
			public static final int leftDriveBMotor = 1;
			public static final int rightDriveAMotor = 2;
			public static final int rightDriveBMotor = 3;
			public static final int flywheelAMotor = 4;
			public static final int flywheelBMotor = 5;
			public static final int rockNRollerMotor = 6;
		}
		
		public static class CAN {
			public static final int leftEncoder = 0x602;
			public static final int rightEncoder = 0x603;
			public static final int flywheelEncoder = 0x604;
			public static final int defenseManipulatorEncoder = 0x605;
			public static final int intakeEncoder = 0x606;
			public static final int ultrasonic = 0x610;
			public static final int ballLoadSensor = 0x611;
		}
		
		public static class CANMotor {
			public static final int innie = 3;
			public static final int rockNRoller = 1;
			public static final int tim = 2;
		}
		
		public static class PCM {
			public static final int hoodSolenoidDown = 0;
			public static final int hoodSolenoidUp = 1;
		}
	}
	
	public static class Constant {
		public static class HumanInput {
			public static final double Y_SPEED_SCALE = 1;
			public static final double TURN_SPEED_SCALE = 1;
			public static final double XBOX_MINIMUM_THRESHOLD = 0.1;
			public static final double SPEED_GAIN = 1;
			public static final double SPEED_EXP = 2;
			public static final double TURN_GAIN = 1;
			public static final double TURN_EXP = 2;
			public static final double DEFENSE_MANIPULATOR_SPEED_SCALE = 0.25;
			public static final double OPERATOR_Y_OUTTAKE_UPPER_THRESHOLD = -0.5;
			public static final double FLYWHEEL_SPINUP_AFTER_HOODUP_DELAY_SECONDS = 0.25;
			public static final double HOODDOWN_AFTER_TRIGGERRELEASE_DELAY_SECONDS = 1;
		}
		
		public static class RobotMetric {
			public static final double WIDTH = 22.15;
			public static final double LENGTH = 25.81;
			public static final double WHEEL_ENCODER_PPR = 1024;
			public static final double WHEEL_DIAMETER = 8;
			public static final double WHEEL_CIRCUMFERENCE = RobotMetric.WHEEL_DIAMETER * Math.PI;
		}
		
		public static class AutonomousMetric {
			/**
			 * The standard autonomous drive speed
			 */
			public static final double DRIVE_SPEED = 0.5;
			/**
			 * The amount of time that autonomous runs
			 * in order to cross the moat.
			 */
			public static final double TIME_MOAT = 3.5;
			public static final double SPEED_MOAT = 0.5;
			/**
			 * The amount of time that autonomous runs
			 * in order to cross the rough terrain.
			 */
			public static final double TIME_ROUGH_TERRAIN = 4;
			public static final double SPEED_ROUGH_TERRAIN = 0.5;
			/**
			 * The amount of time that autonomous runs
			 * in order to cross the rock wall.
			 */
			@Deprecated
			public static final double TIME_ROCK_WALL = 3;
			/**
			 * The amount of time that autonomous runs
			 * in order to cross the low bar.
			 */
			public static final double TIME_LOWBAR = 4;
			public static final double SPEED_LOWBAR = 0.4;
			/**
			 * The speed the robot turns when searching
			 * for the goal
			 */
			public static final double SEARCH_SPEED = 0.2;
			/**
			 * Used as the time to run a command when
			 * reversing direction to prevent drift.
			 */
			public static final double BURST_TIME = 0.15;
			/**
			 * Used as the speed to travel atx when
			 * reversing direction to prevent drift.
			 */
			public static final double BURST_SPEED = -0.25;
		}
		
		public static class FieldMetric {
			/**
			 * The distance that the robot needs to travel
			 * to cross and pass the low bar.
			 */
			public static final double DISTANCE_TO_LOW_BAR = 224;
			/**
			 * The tilt of the defenses, if any.
			 */
			public static final double DEFENSE_TILT = 25.0; // in degrees
		}
		
		public static class Component {
			public static final double ROCKNROLLER_OUTTAKE_SPEED = 1.0;
			public static final double ROCKNROLLER_SHOOT_SPEED = -1.0;
			public static final int FLYWHEEL_PERCENT_TOLERANCE = 5; // 5% error
		}
		
		public static class Network {
			public static final String IP_PREFACE = "10.49.4.";
			public static final String PI_IR_IP = Network.IP_PREFACE + "44";
			@Deprecated
			public static final String PI_VISUAL_IP = Network.IP_PREFACE + "80";
			public static final int PI_IR_PORT = 9999;
			@Deprecated
			public static final int PI_VISUAL_PORT = Integer.MAX_VALUE;
			/**
			 * The HTTP 'GET' request method constant.
			 */
			public static final String CONNECTION_METHOD_GET = "GET";
			/**
			 * The HTTP 'POST' request method constant.
			 */
			public static final String CONNECTION_METHOD_POST = "POST";
			/**
			 * The URL connection protocol string for HTTP
			 */
			public static final String CONNECTION_PROTOCOL_HTTP = "http";
			/**
			 * The URL connection protocol string for HTTPS
			 */
			public static final String CONNECTION_PROTOCOL_HTTPS = "https";
			/**
			 * Autonomous URL destination for the PI IR camera
			 */
			public static final String PI_IR_AUTO_PATH = "/autonomous";
			/**
			 * Return this when connnection cannot be established to anything"
			 */
			public static final String CONNECTION_ERROR_MESSAGE = "CONNECTION COULD NOT BE ESTABLISHED";
			/**
			 * This is value of the IR Camera status if it's good.
			 */
			public static final String PI_IR_STATUS_GOOD = "1";
			/**
			 * This is value of the IR Camera status if it's bad.
			 */
			public static final String PI_IR_STATUS_BAD = "0";
			/**
			 * The position of the IR Camera status in the IR Camerowa data
			 */
			public static final int PI_IR_STATUS_INDEX_POSITION = 0;
		}
		public static final double ROCKNROLLER_OUTTAKE_SPEED = 1.0;
		public static final double ROCKNROLLER_SHOOT_SPEED = -1.0;
		public static final int FLYWHEEL_PERCENT_TOLERANCE = 5; // 5% error
		public static final double TIM_CALIBRATION_SWEEP_SPEED = -0.1;
		public static final int FLYWHEEL_SPIN_UP_SPEED = 750000;
		public static final double HORIZONTAL_BATTER_LENGTH = 43.5;
		public static final double CAMERA_DISTANCE_FROM_FRONT_BUMPER = 13;
		public static final double SHOOTING_RANGE_LENGTH = 36;
		public static final double DISTANCE_FROM_BATTER = 0;
		public static final double SHOOTING_RANGE_MAX = Constant.SHOOTING_RANGE_LENGTH + Constant.CAMERA_DISTANCE_FROM_FRONT_BUMPER + Constant.HORIZONTAL_BATTER_LENGTH + Constant.DISTANCE_FROM_BATTER;
		public static final double SHOOTING_RANGE_MIN = Constant.CAMERA_DISTANCE_FROM_FRONT_BUMPER + Constant.HORIZONTAL_BATTER_LENGTH + Constant.DISTANCE_FROM_BATTER;
		public static final double OUTTAKE_MOTOR_SPEED = -1;
		public static final double INNIE_BALL_HOLD_SPEED = 0.1;
		public static final int BALL_LOAD_SENSOR_CALIBRATION_TICK_COUNT = 40;
		public static final double BALL_LOAD_EMPTY_STATE_ABSOLUTE_ERROR = 20;
	}
	
	public static class Component {
		public static PDP pdp;
		public static PositionEncodedMotor leftWheel;
		public static PositionEncodedMotor rightWheel;
		public static Innie innie;
		public static PositionEncodedMotor tim; // His name is Tim.
		public static TankDrive chassis;
		public static VelocityEncodedMotor flywheelMotor;
		public static Solenoid hoodSolenoid;
		public static DistanceSensor ultrasonicSensor;
		public static BallLoadSensor ballLoadSensor;
		public static RockNRoller rockNRoller;
		public static Hood hood;
		public static Flywheel flywheel;
		public static Shooter shooter;
		public static CANEncoder leftWheelEncoder;
		public static CANEncoder rightWheelEncoder;
		public static CANTalon intakeTalon;
		public static CANTalonEncoder intakeEncoder;
		public static CANEncoder timEncoder;
		public static CANEncoder flywheelEncoder;
		public static Camera cameraIR;
	}
	
	public static class HumanInput {
		public static class Driver {
			public static CustomXbox xbox;
		}
		
		public static class Operator {
			public static CustomJoystick stick;
		}
	}
	
	public RobotMap() {
		Component.pdp = new PDP();
		// Chassis
		Component.leftWheelEncoder = new CANEncoder(Port.CAN.leftEncoder);
		Component.leftWheelEncoder.setReverseDirection(true);
		Component.leftWheel = new PositionEncodedMotor("leftWheel", new AccelerationCap(Component.pdp), new CustomPIDController(Component.leftWheelEncoder), new VictorSP(Port.PWM.leftDriveAMotor), new VictorSP(Port.PWM.leftDriveBMotor));
		Component.leftWheel.disablePID(); // TODO add encoders
		Component.leftWheel.setInverted(true);
		Component.rightWheelEncoder = new CANEncoder(Port.CAN.rightEncoder);
		Component.rightWheel = new PositionEncodedMotor("rightWheel", new AccelerationCap(Component.pdp), new CustomPIDController(Component.rightWheelEncoder), new VictorSP(Port.PWM.rightDriveAMotor), new VictorSP(Port.PWM.rightDriveBMotor));
		Component.rightWheel.disablePID(); // TODO add encoders
		Component.rightWheel.setInverted(true);
		Component.chassis = new TankDrive("StrongholdChassis", Component.leftWheel, Component.rightWheel);
		// Intake
		Component.intakeTalon = new CANTalon(Port.CANMotor.innie);
		Component.intakeEncoder = new CANTalonEncoder(Component.intakeTalon);
		Component.innie = new Innie(new CustomPIDController(Component.intakeEncoder), Component.intakeTalon);
		Component.innie.disablePID(); // TODO add encoders
		Component.rockNRoller = new RockNRoller("rockNRoller", new AccelerationCap(Component.pdp), new CANTalon(Port.CANMotor.rockNRoller));
		Component.timEncoder = new CANEncoder(Port.CAN.defenseManipulatorEncoder);
		Component.timEncoder.setReverseDirection(true);
		Component.tim = new PositionEncodedMotor(new CustomPIDController(Component.timEncoder), new CANTalon(Port.CANMotor.tim));
		Component.tim.setInverted(true);
		Component.tim.disablePID(); // TODO add encoders
		Component.ballLoadSensor = new BallLoadSensor("ballLoadSensor", Port.CAN.ballLoadSensor);
		// Flywheel
		Component.flywheelEncoder = new CANEncoder(Port.CAN.flywheelEncoder);
		Component.flywheel = new Flywheel(new AccelerationCap(Component.pdp), new CustomPIDController(Component.flywheelEncoder), new VictorSP(Port.PWM.flywheelAMotor), new VictorSP(Port.PWM.flywheelBMotor));
		Component.flywheel.disablePID(); // TODO add encoders
		Component.hood = new Hood(new DoubleSolenoid(Port.PCM.hoodSolenoidDown, Port.PCM.hoodSolenoidUp));
		Component.ultrasonicSensor = new CANUltrasonicDistanceSensor("Ultrasonic", Port.CAN.ultrasonic);
		Component.shooter = new Shooter(Component.rockNRoller, Component.hood, Component.flywheel, Component.ultrasonicSensor, Component.ballLoadSensor);
		// Human inputs
		HumanInput.Operator.stick = new CustomJoystick(Port.HumanInput.joystick);
		HumanInput.Operator.stick.setDeadzone(0.1);
		HumanInput.Driver.xbox = new CustomXbox(Port.HumanInput.xboxController);
		HumanInput.Driver.xbox.setDeadZone(RobotMap.Constant.HumanInput.XBOX_MINIMUM_THRESHOLD);
		// IR Camera
		Component.cameraIR = new Camera(RobotMap.Constant.Network.PI_IR_IP, RobotMap.Constant.Network.PI_IR_PORT, RobotMap.Constant.Network.PI_IR_AUTO_PATH, RobotMap.Constant.Network.CONNECTION_PROTOCOL_HTTP);
	}
}
