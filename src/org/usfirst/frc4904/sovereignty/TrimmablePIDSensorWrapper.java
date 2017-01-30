package org.usfirst.frc4904.sovereignty;


import org.usfirst.frc4904.standard.custom.sensors.InvalidSensorException;
import org.usfirst.frc4904.standard.custom.sensors.PIDSensor;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class TrimmablePIDSensorWrapper implements PIDSensor, Trimmable {
	
	private double trimIncrement = 0.0;
	private double trimValue = 0.0;
	private final PIDSensor sensor;
	
	public TrimmablePIDSensorWrapper(PIDSensor sensor) {
		this.sensor = sensor;
	}
	
	public TrimmablePIDSensorWrapper(PIDSource source) {
		this(new PIDSensor.PIDSourceWrapper(source));
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		sensor.setPIDSourceType(pidSource);
	}
	
	@Override
	public PIDSourceType getPIDSourceType() {
		return sensor.getPIDSourceType();
	}
	
	@Override
	public double pidGet() {
		return sensor.pidGet() + trimValue;
	}
	
	@Override
	public double pidGetSafely() throws InvalidSensorException {
		return sensor.pidGetSafely() + trimValue;
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
