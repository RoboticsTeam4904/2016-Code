package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.subsystems.Flywheel;
import org.usfirst.frc4904.robot.subsystems.Hood;
import org.usfirst.frc4904.robot.subsystems.Innie;
import org.usfirst.frc4904.robot.subsystems.RockNRoller;
import org.usfirst.frc4904.robot.subsystems.Shooter;
import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;
import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import org.usfirst.frc4904.standard.custom.sensors.CANEncoder;
import org.usfirst.frc4904.standard.custom.sensors.CANSensor;
import org.usfirst.frc4904.standard.custom.sensors.CANTalonEncoder;
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
		}
		
		public static class CANMotor {
			public static final int intakeRoller = 3;
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
			public static final double X_SPEED_SCALE = 1;
			public static final double Y_SPEED_SCALE = 1;
			public static final double TURN_SPEED_SCALE = 1;
			public static final double XBOX_MINIMUM_THRESHOLD = 0.1;
			public static final double SPEED_GAIN = 1;
			public static final double SPEED_EXP = 2;
			public static final double TURN_GAIN = 1;
			public static final double TURN_EXP = 2;
			public static final double DEFENSE_MANIPULATOR_SPEED_SCALE = 0.25;
		}
		public static final double ROCKNROLLER_OUTTAKE_SPEED = 1.0;
		public static final double ROCKNROLLER_SHOOT_SPEED = -1.0;
		public static final int FLYWHEEL_PERCENT_TOLERANCE = 5; // 5% error
		public static final double TIM_CALIBRATION_SWEEP_SPEED = -0.1;
	}
	
	public static class Component {
		public static PDP pdp;
		public static PositionEncodedMotor leftWheel;
		public static PositionEncodedMotor rightWheel;
		public static Innie intakeRoller;
		public static Tim tim; // His name is Tim.
		public static TankDrive chassis;
		public static VelocityEncodedMotor flywheelMotor;
		public static Solenoid hoodSolenoid;
		public static CANSensor ultrasonicSensor;
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
		Component.leftWheel = new PositionEncodedMotor("leftWheel", new AccelerationCap(Component.pdp), new CustomPIDController(Component.leftWheelEncoder), new VictorSP(Port.PWM.leftDriveAMotor), new VictorSP(Port.PWM.leftDriveBMotor));
		Component.leftWheel.disablePID(); // TODO add encoders
		Component.leftWheel.setInverted(true);
		Component.rightWheelEncoder = new CANEncoder(Port.CAN.rightEncoder);
		Component.rightWheel = new PositionEncodedMotor("rightWheel", new AccelerationCap(Component.pdp), new CustomPIDController(Component.rightWheelEncoder), new VictorSP(Port.PWM.rightDriveAMotor), new VictorSP(Port.PWM.rightDriveBMotor));
		Component.rightWheel.disablePID(); // TODO add encoders
		Component.rightWheel.setInverted(true);
		Component.chassis = new TankDrive("StrongholdChassis", Component.leftWheel, Component.rightWheel);
		// Intake
		Component.intakeTalon = new CANTalon(Port.CANMotor.intakeRoller);
		Component.intakeEncoder = new CANTalonEncoder(Component.intakeTalon);
		Component.intakeRoller = new Innie(new CustomPIDController(Component.intakeEncoder), Component.intakeTalon);
		Component.intakeRoller.disablePID(); // TODO add encoders
		Component.rockNRoller = new RockNRoller("rockNRoller", new AccelerationCap(Component.pdp), new CANTalon(Port.CANMotor.rockNRoller));
		Component.timEncoder = new CANEncoder(Port.CAN.defenseManipulatorEncoder);
		Component.timEncoder.setReverseDirection(true);
		Component.tim = new Tim(new CustomPIDController(Component.timEncoder), Component.timEncoder, new CANTalon(Port.CANMotor.tim));
		Component.tim.setInverted(true);
		Component.tim.disablePID(); // TODO add encoders
		// Flywheel
		Component.flywheelEncoder = new CANEncoder(Port.CAN.flywheelEncoder);
		Component.flywheel = new Flywheel(new AccelerationCap(Component.pdp), new CustomPIDController(Component.flywheelEncoder), new VictorSP(Port.PWM.flywheelAMotor), new VictorSP(Port.PWM.flywheelBMotor));
		Component.flywheel.disablePID(); // TODO add encoders
		Component.hood = new Hood(new DoubleSolenoid(Port.PCM.hoodSolenoidDown, Port.PCM.hoodSolenoidUp));
		Component.ultrasonicSensor = new CANSensor("Ultrasonic", Port.CAN.ultrasonic);
		Component.shooter = new Shooter(Component.rockNRoller, Component.hood, Component.flywheel, Component.ultrasonicSensor);
		// Human inputs
		HumanInput.Operator.stick = new CustomJoystick(Port.HumanInput.joystick);
		HumanInput.Driver.xbox = new CustomXbox(Port.HumanInput.xboxController);
		HumanInput.Driver.xbox.setDeadZone(RobotMap.Constant.HumanInput.XBOX_MINIMUM_THRESHOLD);
	}
}
