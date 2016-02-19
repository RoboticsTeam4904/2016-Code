package org.usfirst.frc4904.robot.subsystems;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.usfirst.frc4904.standard.commands.Idle;
import org.usfirst.frc4904.standard.custom.sensors.CANSensor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	public final RockNRoller rockNRoller;
	public final Hood hood;
	public final Flywheel flywheel;
	public final CANSensor distanceSensor;
	private final HashMap<Double, Double> measuredSpeeds;
	
	public Shooter(RockNRoller rockNRoller, Hood hood, Flywheel flywheel, CANSensor distanceSensor) {
		super("Shooter");
		this.rockNRoller = rockNRoller;
		this.hood = hood;
		this.flywheel = flywheel;
		this.distanceSensor = distanceSensor;
		measuredSpeeds = new HashMap<Double, Double>();
	}
	
	public double getDesiredFlywheelSpeed() {
		double distance = distanceSensor.read(0);
		double nearestDistance = nearestKey(distance, measuredSpeeds);
		return measuredSpeeds.get(nearestDistance);
	}
	
	/**
	 * Find the nearest key to a given approximate key in a HashMap with keys of type Double.
	 * Based off of http://stackoverflow.com/questions/519881/finding-closest-number-in-an-array
	 *
	 * @param approximateKey
	 *        The approximate key. The key in the HashMap nearest to this will be returned.
	 * @param map
	 *        The HashMap to search the keys of. The keys must be of type Double.
	 * @return The nearest key, or Double.NaN if there is none (e.g. your HashMap is empty.)
	 */
	protected double nearestKey(double approximateKey, HashMap<Double, ?> map) {
		Set<Double> keys = map.keySet();
		double nearest = Double.NaN;
		double bestDistanceFoundYet = Double.POSITIVE_INFINITY;
		// We iterate on the array...s
		Iterator<Double> i = keys.iterator();
		while (i.hasNext()) {
			double key = i.next();
			// if we found the desired number, we return it.
			if (key == approximateKey) {
				return key;
			} else {
				// else, we consider the difference between the desired number and the current number in the array.
				double d = Math.abs(approximateKey - key);
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