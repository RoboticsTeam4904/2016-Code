package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.RobotMap.Port.Motors.CAN;
import org.usfirst.frc4904.robot.RobotMap.Port.Motors.PWM;
import org.usfirst.frc4904.robot.subsystems.Flywheel;
import org.usfirst.frc4904.robot.subsystems.Hood;
import org.usfirst.frc4904.robot.subsystems.Shooter;
import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;
import org.usfirst.frc4904.standard.custom.sensors.PDP;
import org.usfirst.frc4904.standard.subsystems.chassis.TankDrive;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.AccelerationCap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Servo;
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
		public static class Motors {
			public static class PWM {
				public static final int leftDriveAMotor = 0;
				public static final int leftDriveB = 1;
				public static final int rightDriveA = 2;
				public static final int rightDriveB = 3;
				public static final int flywheelA = 4;
				public static final int flywheelB = 5;
				public static final int rockerServo = 6;
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
		}
		
		public static class HumanInput {
			public static final int joystick = 0;
			public static final int xboxController = 1;
		}
	}
	
	public static class Constant {
		public static class HumanInput {
			public static final double X_SPEED_SCALE = -1;
			public static final double Y_SPEED_SCALE = -1;
			public static final double TURN_SPEED_SCALE = -1;
			public static final double XBOX_MINIMUM_THRESHOLD = 0.1;
			public static final double SPEED_GAIN = 1;
			public static final double SPEED_EXP = 2;
			public static final double TURN_GAIN = 1;
			public static final double TURN_EXP = 2;
		}
		public static final int ROCKER_INTAKE_ANGLE = 0;
		public static final int ROCKER_SHOOT_ANGLE = 90;
		public static final int FLYWHEEL_PERCENT_TOLERANCE = 5; // 5% error
	}
	
	public static class Component {
		public static PDP pdp;
		public static Motor leftWheel;
		public static Motor rightWheel;
		public static Motor flywheelMotor;
		public static Motor bottomIntakeRoller;
		public static Motor topIntakeRoller;
		public static Motor defenseManipulator; // His name is Tim.
		public static Servo rockerServo;
		public static Solenoid hoodSolenoid;
		public static TankDrive chassis;
		// public static Rocker rocker;
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
		Component.leftWheel = new Motor("leftWheel", new AccelerationCap(Component.pdp), new VictorSP(PWM.leftDriveAMotor), new VictorSP(PWM.leftDriveB));
		Component.rightWheel = new Motor("rightWheel", new AccelerationCap(Component.pdp), new VictorSP(PWM.rightDriveA), new VictorSP(PWM.rightDriveB));
		Component.flywheelMotor = new Motor("flywheel", new AccelerationCap(Component.pdp), new VictorSP(PWM.flywheelA), new VictorSP(PWM.flywheelB));
		Component.bottomIntakeRoller = new Motor("bottomIntakeRoller", new AccelerationCap(Component.pdp), new CANTalon(CAN.bottomIntakeRoller));
		Component.topIntakeRoller = new Motor("topIntakeRoller", new AccelerationCap(Component.pdp), new CANTalon(CAN.topIntakeRoller));
		Component.defenseManipulator = new Motor("defenseManipulator", new AccelerationCap(Component.pdp), new CANTalon(CAN.defenseManipulator));
		Component.hoodSolenoid = new Solenoid(Port.Motors.PWM.hoodSolenoid);
		Component.rockerServo = new Servo(Port.Motors.PWM.rockerServo);
		Component.chassis = new TankDrive("StrongholdChassis", Component.leftWheel, Component.rightWheel);
		// Component.rocker = new Rocker(Component.rockerServo);
		Component.hood = new Hood(Component.hoodSolenoid);
		Component.flywheel = new Flywheel(/* Component.flywheelMotor */);
		// Component.shooter = new Shooter(Component.rocker, Component.hood, Component.flywheel);
		HumanInput.Operator.stick = new CustomJoystick(Port.HumanInput.joystick);
		HumanInput.Driver.xbox = new CustomXbox(Port.HumanInput.xboxController);
		HumanInput.Driver.xbox.setDeadZone(RobotMap.Constant.HumanInput.XBOX_MINIMUM_THRESHOLD);
	}
}
