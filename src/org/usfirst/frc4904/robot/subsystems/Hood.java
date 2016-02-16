package org.usfirst.frc4904.robot.subsystems;


import org.usfirst.frc4904.standard.commands.Idle;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hood extends Subsystem {
	protected boolean isUp;
	protected final Solenoid down;
	protected final Solenoid up;
	
	public Hood(Solenoid down, Solenoid up) {
		super("Hood");
		this.down = down;
		this.up = up;
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
		down.set(!goUp);
		up.set(goUp);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Idle(this));
	}
}
