package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.autonomous.commands.ChassisSetDistance;
import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.commands.shooter.HoodDown;
import org.usfirst.frc4904.standard.custom.sensors.CANEncoder;
import org.usfirst.frc4904.standard.custom.sensors.CustomEncoder;
import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowbar extends CommandGroup {
	protected final CustomEncoder[] encoders;
	
	public CrossLowbar(Chassis chassis, boolean usePID) {
		encoders = new CustomEncoder[2];
		encoders[0] = new CANEncoder(RobotMap.Port.CAN.leftEncoder);
		encoders[1] = new CANEncoder(RobotMap.Port.CAN.rightEncoder);
		double tickDistance = (RobotMap.Constant.FieldMetric.DISTANCE_TO_LOW_BAR / RobotMap.Constant.RobotMetric.WHEEL_CIRCUMFERENCE) * RobotMap.Constant.RobotMetric.WHEEL_ENCODER_PPR;
		addSequential(new HoodDown(RobotMap.Component.hood));
		addSequential(new ChassisSetDistance(chassis, tickDistance, RobotMap.Constant.AutonomousMetric.DRIVE_SPEED, usePID, encoders));
	}
}
