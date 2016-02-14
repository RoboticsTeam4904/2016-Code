package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.RobotMap.Port.Motors.CAN;
import org.usfirst.frc4904.robot.RobotMap.Port.Motors.PWM;
import org.usfirst.frc4904.robot.subsystems.Flywheel;
import org.usfirst.frc4904.robot.subsystems.Hood;
import org.usfirst.frc4904.robot.subsystems.RockNRoller;
import org.usfirst.frc4904.robot.subsystems.Shooter;
import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;
import org.usfirst.frc4904.standard.custom.sensors.CANEncoder;
import org.usfirst.frc4904.standard.custom.sensors.CANSensor;
import org.usfirst.frc4904.standard.custom.sensors.PDP;
import org.usfirst.frc4904.standard.subsystems.chassis.TankDrive;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import org.usfirst.frc4904.standard.subsystems.motor.sensormotor.EncodedMotor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.AccelerationCap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static class Port {
		public static class Motors {
			public static class PWM {
				public static final int leftDriveAMotor = 0;
				public static final int leftDriveB = 1;
				public static final int rightDriveA = 2;
				public static final int rightDriveB = 3;
				public static final int flywheelA = 4;
				public static final int flywheelB = 5;
				public static final int rockNRollerMotor = 6;
				public static final int hoodSolenoid = 7;
			}
			
			public static class CAN {
				public static final int topIntakeRoller = 1;
				public static final int bottomIntakeRoller = 2;
				public static final int defenseManipulator = 3;
			}
		}
		
		public static class Sensors {
			public static final int leftEncoder = 602;
			public static final int rightEncoder = 603;
			public static final int flywheelEncoder = 604;
			public static final int defenseManipulatorEncoder = 605;
			public static final int ultrasonic = 610;
		}
		
		public static class HumanInput {
			public static final int joystick = 0;
			public static final int xboxController = 1;
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
		}
		public static final int ROCKNROLLER_OUTTAKE_SPEED = -1;
		public static final int ROCKNROLLER_SHOOT_SPEED = 1;
		public static final int FLYWHEEL_PERCENT_TOLERANCE = 5; // 5% error
	}
	
	public static class Component {
		public static PDP pdp;
		public static EncodedMotor leftWheel;
		public static EncodedMotor rightWheel;
		public static EncodedMotor flywheelMotor;
		public static Motor bottomIntakeRoller;
		public static Motor topIntakeRoller;
		public static EncodedMotor defenseManipulator; // His name is Tim.
		public static Motor rockNRollerMotor;
		public static Solenoid hoodSolenoid;
		public static CANSensor ultrasonicSensor;
		public static TankDrive chassis;
		public static RockNRoller rockNRoller;
		public static Hood hood;
		public static Flywheel flywheel;
		public static Shooter shooter;
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
		Component.leftWheel = new EncodedMotor("leftWheel", new AccelerationCap(Component.pdp), new CANEncoder(Port.Sensors.leftEncoder), new VictorSP(PWM.leftDriveAMotor), new VictorSP(PWM.leftDriveB));
		Component.rightWheel = new EncodedMotor("rightWheel", new AccelerationCap(Component.pdp), new CANEncoder(Port.Sensors.rightEncoder), new VictorSP(PWM.rightDriveA), new VictorSP(PWM.rightDriveB));
		Component.flywheelMotor = new EncodedMotor("flywheel", new AccelerationCap(Component.pdp), new CANEncoder(Port.Sensors.flywheelEncoder), new VictorSP(PWM.flywheelA), new VictorSP(PWM.flywheelB));
		Component.bottomIntakeRoller = new Motor("bottomIntakeRoller", new AccelerationCap(Component.pdp), new CANTalon(CAN.bottomIntakeRoller));
		Component.topIntakeRoller = new Motor("topIntakeRoller", new AccelerationCap(Component.pdp), new CANTalon(CAN.topIntakeRoller));
		Component.defenseManipulator = new EncodedMotor("defenseManipulator", new AccelerationCap(Component.pdp), new CANEncoder(Port.Sensors.defenseManipulatorEncoder), new CANTalon(CAN.defenseManipulator));
		Component.rockNRollerMotor = new Motor("rockNRollerMotor", new AccelerationCap(Component.pdp), new Victor(Port.Motors.PWM.rockNRollerMotor));
		Component.hoodSolenoid = new Solenoid(Port.Motors.PWM.hoodSolenoid);
		Component.ultrasonicSensor = new CANSensor("Ultrasonic", Port.Sensors.ultrasonic);
		Component.chassis = new TankDrive("StrongholdChassis", Component.leftWheel, Component.rightWheel);
		Component.rockNRoller = new RockNRoller(Component.rockNRollerMotor);
		Component.hood = new Hood(Component.hoodSolenoid);
		Component.flywheel = new Flywheel(Component.flywheelMotor);
		Component.shooter = new Shooter(Component.rockNRoller, Component.hood, Component.flywheel, Component.ultrasonicSensor);
		HumanInput.Operator.stick = new CustomJoystick(Port.HumanInput.joystick);
		HumanInput.Driver.xbox = new CustomXbox(Port.HumanInput.xboxController);
		HumanInput.Driver.xbox.setDeadZone(RobotMap.Constant.HumanInput.XBOX_MINIMUM_THRESHOLD);
	}
}
