package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.standard.custom.controllers.CustomJoystick;
import org.usfirst.frc4904.standard.custom.controllers.CustomXbox;
import org.usfirst.frc4904.standard.custom.sensors.PDP;
import org.usfirst.frc4904.standard.subsystems.chassis.TankDrive;
import org.usfirst.frc4904.standard.subsystems.motor.AccelMotor;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import org.usfirst.frc4904.standard.subsystems.motor.MotorGroup;
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
			public static final int leftDriveA = 0;
			public static final int leftDriveB = 1;
			public static final int rightDriveA = 2;
			public static final int rightDriveB = 3;
			public static final int flywheelA = 4;
			public static final int flywheelB = 5;
			public static final int intakeTopRoller = -1; // TODO
			public static final int intakeArm = -1; // TODO
			public static final int intakeBottomRoller = -1; // TODO
			public static final int climberWinchA = 6; // TODO
			public static final int climberWinchB = 7; // TODO
			public static final int rockerServo = 8;
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
	}
	
	public static class Component {
		private static class RawMotor {
			public static VictorSP leftDriveA;
			public static VictorSP leftDriveB;
			public static VictorSP rightDriveA;
			public static VictorSP rightDriveB;
			// Shoot
			public static VictorSP flywheelA;
			public static VictorSP flywheelB;
			// Intake
			public static Servo rockerServo;
			// Conveyor
			public static CANTalon intakeTopRoller;
			public static CANTalon intakeArm;
			public static CANTalon intakeBottomRoller;
			// Climb
			public static VictorSP climberWinchA;
			public static VictorSP climberWinchB;
		}
		
		public static class Motors {
			public static Motor leftWheelA;
			public static Motor leftWheelB;
			public static Motor rightWheelA;
			public static Motor rightWheelB;
			public static Motor leftWheel;
			public static Motor rightWheel;
			public static Motor shooterWheelA;
			public static Motor shooterWheelB;
			public static Motor shooterWheel;
			public static Motor spinRollers;
			public static Motor armLifter;
			public static Motor spinBottom;
			public static Motor climberWinchA;
			public static Motor climberWinchB;
			public static Motor climberWinch;
		}
		public static TankDrive chassis;
		public static PDP pdp;
	}
	
	public static class HumanInput {
		public static class Driver {
			public static CustomXbox xbox;
		}
		
		public static class Operator {
			// *** OPERATOR *** //
			// Initialize operator joystick
			public static CustomJoystick stick;
		}
	}
	
	// Initialize operator buttons
	public RobotMap() {
		Component.pdp = new PDP();
		Component.RawMotor.leftDriveA = new VictorSP(Port.Motors.leftDriveA);
		Component.RawMotor.leftDriveB = new VictorSP(Port.Motors.leftDriveB);
		Component.RawMotor.rightDriveA = new VictorSP(Port.Motors.rightDriveA);
		Component.RawMotor.rightDriveB = new VictorSP(Port.Motors.rightDriveB);
		Component.RawMotor.flywheelA = new VictorSP(Port.Motors.flywheelA);
		Component.RawMotor.flywheelB = new VictorSP(Port.Motors.flywheelB);
		Component.RawMotor.intakeTopRoller = new CANTalon(Port.Motors.intakeTopRoller);
		Component.RawMotor.intakeArm = new CANTalon(Port.Motors.intakeArm);
		Component.RawMotor.intakeBottomRoller = new CANTalon(Port.Motors.intakeBottomRoller);
		Component.RawMotor.climberWinchA = new VictorSP(Port.Motors.climberWinchA);
		Component.RawMotor.climberWinchB = new VictorSP(Port.Motors.climberWinchB);
		Component.RawMotor.rockerServo = new Servo(Port.Motors.rockerServo);
		Component.Motors.leftWheelA = new Motor("leftWheelA", Component.RawMotor.leftDriveA);
		Component.Motors.leftWheelB = new Motor("leftWheelB", Component.RawMotor.leftDriveB);
		Component.Motors.rightWheelA = new Motor("rightWheelA", Component.RawMotor.rightDriveA, true);
		Component.Motors.rightWheelB = new Motor("rightWheelB", Component.RawMotor.rightDriveB, true);
		Component.Motors.leftWheel = new AccelMotor("leftWheelAccel", new MotorGroup("leftWheel", Component.Motors.leftWheelA, Component.Motors.leftWheelB), Component.pdp);
		Component.Motors.rightWheel = new AccelMotor("rightWheelAccel", new MotorGroup("rightWheel", Component.Motors.rightWheelA, Component.Motors.rightWheelB), Component.pdp);
		Component.chassis = new TankDrive("StrongholdChassis", Component.Motors.leftWheel, Component.Motors.rightWheel);
		Component.Motors.shooterWheelA = new Motor("shooterWheelA", Component.RawMotor.flywheelA);
		Component.Motors.shooterWheelB = new Motor("shooterWheelB", Component.RawMotor.flywheelB);
		Component.Motors.shooterWheel = new Motor("shooterWheelAccel", new MotorGroup("shooterWheel", Component.Motors.shooterWheelA, Component.Motors.shooterWheelB)); // TODO fix motor type
		Component.Motors.climberWinchA = new Motor("climberWinchA", Component.RawMotor.climberWinchA);
		Component.Motors.climberWinchB = new Motor("climberWinchB", Component.RawMotor.climberWinchB);
		Component.Motors.climberWinch = new Motor("climberWinchAccel", new MotorGroup("climberWinch", Component.Motors.climberWinchA, Component.Motors.climberWinchB)); // TODO fix motor type
		HumanInput.Driver.xbox.setDeadZone(RobotMap.Constant.HumanInput.XBOX_MINIMUM_THRESHOLD);
	}
}
