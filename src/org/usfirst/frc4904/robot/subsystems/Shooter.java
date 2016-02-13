package org.usfirst.frc4904.robot.subsystems;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.custom.sensors.CANSensor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	public final RockNRoller rocker;
	public final Hood hood;
	public final Flywheel flywheel;
	public final CANSensor distanceSensor;
	protected final boolean useTheoreticalModel = false;
	private final HashMap<Double, Double> measuredSpeeds;

	public Shooter(RockNRoller rocker, Hood hood, Flywheel flywheel, CANSensor distanceSensor) {
		super("Shooter");
		this.rocker = rocker;
		this.hood = hood;
		this.flywheel = flywheel;
		this.distanceSensor = distanceSensor;
		measuredSpeeds = new HashMap<Double, Double>();
	}
	
	public double getDesiredFlywheelSpeed() {
		double distance = distanceSensor.read(0);
		double speed;
		if (useTheoreticalModel) {
			// TODO add theoretical model code
			speed = distance * 0.5; // Made up
		} else {
			double nearestDistance = nearestKey(distance, measuredSpeeds);
			speed = measuredSpeeds.get(nearestDistance);
		}
		return speed;
	}

	/**
	 * Find the nearest key to a given value in a HashMap with keys of type Double.
	 * Based off of http://stackoverflow.com/questions/519881/finding-closest-number-in-an-array
	 *
	 * @param value
	 * @param map
	 * @return The nearest value, or Double.NaNs if there is none (e.g. your HashMap is empty.)
	 */
	protected double nearestKey(double value, HashMap<Double, ?> map) {
		Set<Double> keys = map.keySet();
		double nearest = Double.NaN;
		double bestDistanceFoundYet = Double.POSITIVE_INFINITY;
		// We iterate on the array...s
		Iterator<Double> i = keys.iterator();
		while (i.hasNext()) {
			double key = i.next();
			// if we found the desired number, we return it.
			if (key == value) {
				return key;
			} else {
				// else, we consider the difference between the desired number and the current number in the array.
				double d = Math.abs(value - key);
				if (d < bestDistanceFoundYet) {
					// For the moment, this value is the nearest to the desired number...
					bestDistanceFoundYet = d; // Assign new best distance...
					nearest = key;
				}
			}
		}
		return nearest;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}
