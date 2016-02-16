package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.robot.RobotMap.Port.Motors.CAN;
import org.usfirst.frc4904.robot.RobotMap.Port.Motors.PWM;
import org.usfirst.frc4904.robot.subsystems.Flywheel;
import org.usfirst.frc4904.robot.subsystems.Hood;
import org.usfirst.frc4904.robot.subsystems.Shooter;
import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;
import org.usfirst.frc4904.standard.custom.sensors.CANEncoder;
import org.usfirst.frc4904.standard.custom.sensors.PDP;
import org.usfirst.frc4904.standard.subsystems.chassis.TankDrive;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import org.usfirst.frc4904.standard.subsystems.motor.PositionEncodedMotor;
import org.usfirst.frc4904.standard.subsystems.motor.VelocityEncodedMotor;
import org.usfirst.frc4904.standard.subsystems.motor.speedmodifiers.AccelerationCap;
import edu.wpi.first.wpilibj.CANTalon;
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
				public static final int leftDriveA = 0;
				public static final int leftDriveB = 1;
				public static final int rightDriveA = 2;
				public static final int rightDriveB = 3;
				public static final int flywheelA = 4;
				public static final int flywheelB = 5;
			}
			
			public static class CAN {
				public static final int intakeRoller = 3;
				public static final int rockNRoller = 1;
				public static final int defenseManipulator = 2;
			}
			
			public static class PCM {
				public static final int hoodSolenoidDown = 0;
				public static final int hoodSolenoidUp = 1;
			}
		}
		
		public static class Sensors {
			public static final int leftEncoder = 602;
			public static final int rightEncoder = 603;
			public static final int flywheelEncoder = 604;
			public static final int defenseManipulatorEncoder = 605;
			public static final int intakeEncoder = 606;
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
		public static final double ROCKER_INTAKE_ANGLE = 0;
		public static final double ROCKER_SHOOT_ANGLE = 0.5;
		public static final int FLYWHEEL_PERCENT_TOLERANCE = 5; // 5% error
	}
	
	public static class Component {
		public static PDP pdp;
		public static PositionEncodedMotor leftWheel;
		public static PositionEncodedMotor rightWheel;
		public static VelocityEncodedMotor intakeRoller;
		public static Motor rockNRoller;
		public static VelocityEncodedMotor defenseManipulator; // His name is Tim.
		public static TankDrive chassis;
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
		// Chassis
		Component.leftWheel = new PositionEncodedMotor("leftWheel", new AccelerationCap(Component.pdp), new CANEncoder(Port.Sensors.leftEncoder), new VictorSP(PWM.leftDriveA), new VictorSP(PWM.leftDriveB));
		Component.leftWheel.disablePID();
		Component.rightWheel = new PositionEncodedMotor("rightWheel", new AccelerationCap(Component.pdp), new CANEncoder(Port.Sensors.rightEncoder), new VictorSP(PWM.rightDriveA), new VictorSP(PWM.rightDriveB));
		Component.rightWheel.disablePID();
		Component.chassis = new TankDrive("StrongholdChassis", Component.leftWheel, Component.rightWheel);
		// Intake
		Component.intakeRoller = new VelocityEncodedMotor("topIntakeRoller", new AccelerationCap(Component.pdp), new CANEncoder(Port.Sensors.intakeEncoder), new CANTalon(CAN.intakeRoller));
		Component.intakeRoller.disablePID();
		Component.rockNRoller = new Motor("rockNRoller", new AccelerationCap(Component.pdp), new CANTalon(CAN.rockNRoller));
		Component.defenseManipulator = new VelocityEncodedMotor("defenseManipulator", new AccelerationCap(Component.pdp), new CANEncoder(Port.Sensors.defenseManipulatorEncoder), new CANTalon(CAN.defenseManipulator));
		Component.defenseManipulator.disablePID();
		// Flywheel
		Component.flywheel = new Flywheel(new AccelerationCap(Component.pdp), new CANEncoder(Port.Sensors.flywheelEncoder), new VictorSP(PWM.flywheelA), new VictorSP(PWM.flywheelB));
		Component.flywheel.disablePID();
		Component.hood = new Hood(new Solenoid(Port.Motors.PCM.hoodSolenoidDown), new Solenoid(Port.Motors.PCM.hoodSolenoidUp));
		Component.shooter = new Shooter(Component.rockNRoller, Component.hood, Component.flywheel);
		// Human inputs
		HumanInput.Operator.stick = new CustomJoystick(Port.HumanInput.joystick);
		HumanInput.Driver.xbox = new CustomXbox(Port.HumanInput.xboxController);
		HumanInput.Driver.xbox.setDeadZone(RobotMap.Constant.HumanInput.XBOX_MINIMUM_THRESHOLD);
	}
}
