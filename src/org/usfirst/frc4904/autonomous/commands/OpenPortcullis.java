package org.usfirst.frc4904.autonomous.commands;


import org.usfirst.frc4904.standard.subsystems.chassis.Chassis;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class OpenPortcullis extends CommandGroup {
	Chassis chassis;
	
	public OpenPortcullis(Chassis chassis, boolean usePID) {
		this.chassis = chassis;
	}
}
