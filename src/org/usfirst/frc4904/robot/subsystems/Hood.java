package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.robot.commands.shooter.HoodUp;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hood extends Subsystem {
	protected boolean isUp;
	protected final DoubleSolenoid solenoid;
	
	public Hood(DoubleSolenoid solenoid) {
		super("Hood");
		this.solenoid = solenoid;
	}
	
	/**
	 * Gets whether the hood is currently set to up.
	 * Does not read the actual position - this is just the last requested position.
	 * 
	 * @return position
	 *         true if the the current desired hood position is up.
	 */
	public boolean isUp() {
		return isUp;
	}
	
	/**
	 * Gets whether the hood is currently set to down.
	 * Does not read the actual position - this is just the last requested position.
	 * 
	 * @return position
	 *         true if the the current desired hood position is down.
	 */
	public boolean isDown() {
		return !isUp;
	}
	
	/**
	 * Sets the desired hood position.
	 * True means up.
	 * 
	 * @param position
	 *        True means up
	 */
	public void setPosition(boolean goUp) {
		if (goUp) {
			solenoid.set(DoubleSolenoid.Value.kForward);
		} else {
			solenoid.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new HoodUp(this));
	}
}
