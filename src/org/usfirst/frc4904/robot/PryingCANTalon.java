package org.usfirst.frc4904.robot;


import org.usfirst.frc4904.standard.LogKitten;
import edu.wpi.first.wpilibj.CANTalon;

public class PryingCANTalon extends CANTalon {
	public PryingCANTalon(int deviceNumber) {
		super(deviceNumber);
	}
	
	@Override
	public void set(double speed) {
		LogKitten.wtf("CANTalon set to " + speed);
		super.set(speed);
	}
}
