package org.usfirst.frc4904.robot;

import org.usfirst.frc4904.standard.custom.sensors.PDP;
import org.usfirst.frc4904.standard.subsystems.chassis.TankDrive;
import org.usfirst.frc4904.standard.subsystems.motor.AccelMotor;
import org.usfirst.frc4904.standard.subsystems.motor.Motor;
import org.usfirst.frc4904.standard.subsystems.motor.MotorGroup;

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
	
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	//TODO add encoders

	// PWM
	public static int LEFT_WHEEL_A_PORT = 0;
	public static int LEFT_WHEEL_B_PORT = 1;
	public static int RIGHT_WHEEL_A_PORT = 2;
	public static int RIGHT_WHEEL_B_PORT = 3;
	
	public static int SHOOTER_WHEEL_A_PORT = 4;
	public static int SHOOTER_WHEEL_B_PORT = 5;
	
	public static int SPIN_ROLLERS_PORT = -1; //TODO
	public static int ARM_LIFTER_PORT = -1; //TODO
	public static int SPIN_BOTTOM_PORT = -1; //TODO
	
	public static int CLIMBER_WINCH_A_PORT = 6; //TODO
	public static int CLIMBER_WINCH_B_PORT = 7; //TODO
	
	// Drive
	public static VictorSP LEFT_WHEEL_MOTOR_B;
	public static VictorSP RIGHT_WHEEL_MOTOR_A;
	public static VictorSP RIGHT_WHEEL_MOTOR_B;
	public static VictorSP LEFT_WHEEL_MOTOR_A;
	
	// Shoot
	public static VictorSP SHOOTER_WHEEL_MOTOR_A;
	public static VictorSP SHOOTER_WHEEL_MOTOR_B;
	
	// Conveyor
	public static CANTalon SPIN_ROLLERS_MOTOR;
	public static CANTalon ARM_LIFTER_MOTOR;
	public static CANTalon SPIN_BOTTOM_MOTOR;
	
	// Climb
	public static VictorSP CLIMBER_WINCH_MOTOR_A;
	public static VictorSP CLIMBER_WINCH_MOTOR_B;
	
	// Motors
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
	public static TankDrive chassis;
	public static PDP pdp;
	
	public RobotMap() {
		pdp = new PDP();
		LEFT_WHEEL_MOTOR_A = new VictorSP(LEFT_WHEEL_A_PORT);
		LEFT_WHEEL_MOTOR_B = new VictorSP(LEFT_WHEEL_B_PORT);
		RIGHT_WHEEL_MOTOR_A = new VictorSP(RIGHT_WHEEL_A_PORT);
		RIGHT_WHEEL_MOTOR_B = new VictorSP(RIGHT_WHEEL_B_PORT);
		SHOOTER_WHEEL_MOTOR_A = new VictorSP(SHOOTER_WHEEL_A_PORT);
		SHOOTER_WHEEL_MOTOR_B = new VictorSP(SHOOTER_WHEEL_B_PORT);
		SPIN_ROLLERS_MOTOR = new CANTalon(SPIN_ROLLERS_PORT);
		ARM_LIFTER_MOTOR = new CANTalon(ARM_LIFTER_PORT);
		SPIN_BOTTOM_MOTOR = new CANTalon(SPIN_BOTTOM_PORT);
		CLIMBER_WINCH_MOTOR_A = new VictorSP(CLIMBER_WINCH_A_PORT);
		CLIMBER_WINCH_MOTOR_B = new VictorSP(CLIMBER_WINCH_B_PORT);
		leftWheelA = new Motor("First left wheel", LEFT_WHEEL_MOTOR_A);
		leftWheelB = new Motor("Second left wheel", LEFT_WHEEL_MOTOR_B);
		rightWheelA = new Motor("First right wheel", RIGHT_WHEEL_MOTOR_A, true);
		rightWheelB = new Motor("Second right wheel", RIGHT_WHEEL_MOTOR_B, true);
		leftWheel = new AccelMotor("Left wheel accel", new MotorGroup("Left wheel", leftWheelA, leftWheelB), pdp);
		rightWheel = new AccelMotor("Right wheel accel", new MotorGroup("Right wheel", rightWheelA, rightWheelB), pdp);
		chassis = new TankDrive("StrongholdChassis", leftWheel, rightWheel);
		shooterWheelA = new Motor("First shooter wheel", SHOOTER_WHEEL_MOTOR_A);
		shooterWheelB = new Motor("Second shooter wheel", SHOOTER_WHEEL_MOTOR_B);
		shooterWheel = new Motor("Shooter wheel accel", new MotorGroup("Shooter wheel", shooterWheelA, shooterWheelB)); //TODO fix motor type
		climberWinchA = new Motor("First climber winch", CLIMBER_WINCH_MOTOR_A);
		climberWinchB = new Motor("Second climber winch", CLIMBER_WINCH_MOTOR_B);
		climberWinch = new Motor("climber winch accel", new MotorGroup("climber winch", climberWinchA, climberWinchB)); //TODO fix motor type
	}
}
