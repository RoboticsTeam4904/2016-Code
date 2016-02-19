package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.shooter.HoodDown;
import org.usfirst.frc4904.robot.commands.shooter.HoodUp;
import org.usfirst.frc4904.robot.commands.shooter.Shoot;
import org.usfirst.frc4904.standard.custom.sensors.CANEncoder;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowbarAndShoot extends CommandGroup {
	protected final CustomEncoder[] encoders;
	
	public CrossLowbarAndShoot(Chassis chassis, boolean usePID) {
		encoders = new CustomEncoder[2];
		encoders[0] = new CANEncoder(RobotMap.Port.CAN.leftEncoder);
		encoders[1] = new CANEncoder(RobotMap.Port.CAN.rightEncoder);
		double tickDistance = (RobotMap.Constant.Autonomous.DISTANCE_TO_LOW_BAR / RobotMap.Constant.RobotMetrics.WHEEL_CIRCUMFERENCE) * RobotMap.Constant.RobotMetrics.WHEEL_ENCODER_PPR;
		addSequential(new HoodDown(RobotMap.Component.hood));
		addSequential(new ChassisSetDistance(chassis, tickDistance, RobotMap.Constant.Autonomous.DRIVE_SPEED, usePID, encoders));
		addSequential(new HoodUp(RobotMap.Component.hood));
		// TODO: Align with goal
		addSequential(new Shoot(RobotMap.Component.shooter));
	}
}
