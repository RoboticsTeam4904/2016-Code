package org.usfirst.frc4904.autonomous.strategies;


import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class OpenPortcullisDistance extends CommandGroup {
	Chassis chassis;
	
	public OpenPortcullisDistance(Chassis chassis, boolean usePID) {
		this.chassis = chassis;
	}
}
