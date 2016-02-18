package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.RobotMap.Port.Motors.CAN;
import org.usfirst.frc4904.robot.RobotMap.Port.Motors.PWM;
import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;
import org.usfirst.frc4904.standard.custom.sensors.CANEncoder;
import org.usfirst.frc4904.standard.custom.sensors.PDP;
import org.usfirst.frc4904.standard.subsystems.chassis.TankDrive;
import org.usfirst.frc4904.standard.subsystems.motor.AccelMotor;
import org.usfirst.frc4904.standard.subsystems.motor.EncodedMotor;
import org.usfirst.frc4904.standard.subsystems.motor.MotorFactory;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Servo;
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
				public static final int leftDriveA = 0;
				public static final int leftDriveB = 1;
				public static final int rightDriveA = 2;
				public static final int rightDriveB = 3;
				public static final int flywheelA = 4;
				public static final int flywheelB = 5;
				public static final int rockerServo = 6;
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
		
		public static class RobotMetrics {
			public static final double WIDTH = 22.15;
			public static final double HEIGHT = 25.81;
			public static final double WHEEL_ENCODER_PPR = 256;
			public static final double WHEEL_DIAMETER = 8;
			public static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;
		}
		
		public static class Autonomous {
			public static final double DRIVE_SPEED = 0.5;
			public static final double DISTANCE_TO_LOW_BAR = 130;
		}
	}
}

public static class Component {
	public static EncodedMotor leftWheel;
	public static EncodedMotor rightWheel;
	public static EncodedMotor flywheel;
	public static AccelMotor bottomIntakeRoller;
	public static AccelMotor topIntakeRoller;
	public static EncodedMotor defenseManipulator; // His name is Tim.
	public static Servo rocker;
	public static TankDrive chassis;
	public static PDP pdp;
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
		Component.leftWheel = MotorFactory.getEncodedAccelMotorGroup("leftWheel", Component.pdp, new CANEncoder(Port.Sensors.leftEncoder), 0, 0, 0, 0, 0, 0, new VictorSP(PWM.leftDriveA), new VictorSP(PWM.leftDriveB));
		Component.rightWheel = MotorFactory.getEncodedAccelMotorGroup("rightWheel", Component.pdp, new CANEncoder(Port.Sensors.rightEncoder), 0, 0, 0, 0, 0, 0, new VictorSP(PWM.rightDriveA), new VictorSP(PWM.rightDriveB));
		Component.flywheel = MotorFactory.getEncodedAccelMotorGroup("flywheel", Component.pdp, new CANEncoder(Port.Sensors.flywheelEncoder), 0, 0, 0, 0, 0, 0, new VictorSP(PWM.flywheelA), new VictorSP(PWM.flywheelB));
		Component.bottomIntakeRoller = MotorFactory.getAccelMotorGroup("bottomIntakeRoller", Component.pdp, new CANTalon(CAN.bottomIntakeRoller));
		Component.topIntakeRoller = MotorFactory.getAccelMotorGroup("topIntakeRoller", Component.pdp, new CANTalon(CAN.topIntakeRoller));
		Component.defenseManipulator = MotorFactory.getEncodedAccelMotorGroup("defenseManipulator", Component.pdp, new CANEncoder(Port.Sensors.defenseManipulatorEncoder), 0, 0, 0, 0, 0, 0, new CANTalon(CAN.defenseManipulator));
		Component.chassis = new TankDrive("StrongholdChassis", Component.leftWheel, Component.rightWheel);
		HumanInput.Operator.stick = new CustomJoystick(Port.HumanInput.joystick);
		HumanInput.Driver.xbox = new CustomXbox(Port.HumanInput.xboxController);
		HumanInput.Driver.xbox.setDeadZone(RobotMap.Constant.HumanInput.XBOX_MINIMUM_THRESHOLD);
	}
}
