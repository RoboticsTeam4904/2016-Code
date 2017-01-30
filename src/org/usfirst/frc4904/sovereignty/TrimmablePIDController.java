package org.usfirst.frc4904.sovereignty;


import org.usfirst.frc4904.standard.custom.motioncontrollers.CustomPIDController;
import org.usfirst.frc4904.standard.custom.sensors.PIDSensor;
import edu.wpi.first.wpilibj.PIDSource;

public class TrimmablePIDController extends CustomPIDController implements Trimmable {
	
	private double trimValue = 0.0;
	private double trimIncrement = 0.0;
	
	public TrimmablePIDController(double P, double I, double D, double F, PIDSensor sensor) {
		super(P, I, D, F, sensor);
	}
	
	public TrimmablePIDController(double P, double I, double D, double F, PIDSource source) {
		super(P, I, D, F, source);
	}
	
	public TrimmablePIDController(double P, double I, double D, PIDSensor sensor) {
		super(P, I, D, sensor);
	}
	
	public TrimmablePIDController(double P, double I, double D, PIDSource source) {
		super(P, I, D, source);
	}
	
	public TrimmablePIDController(PIDSensor sensor) {
		super(sensor);
	}
	
	public TrimmablePIDController(PIDSource source) {
		super(source);
	}
	
	@Override
	public void setSetpoint(double setpoint) {
		super.setSetpoint(setpoint + trimValue);
	}
	
	@Override
	public void setTrimIncrement(double trimIncrement) {
		this.trimIncrement = trimIncrement;
	}
	
	@Override
	public double getTrimIncrement() {
		return trimIncrement;
	}
	
	@Override
	public void setTrim(double trimValue) {
		this.trimValue = trimValue;
	}
	
	@Override
	public double getTrim() {
		return trimValue;
	}
}
