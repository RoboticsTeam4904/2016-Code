package org.usfirst.frc4904.sovereignty;


import edu.wpi.first.wpilibj.command.Command;

public class TrimCommand extends Command {
	public static enum TrimDirection {
		UP(true), DOWN(false), LEFT(false), RIGHT(true);
		private boolean positive;
		
		private TrimDirection(boolean positive) {
			this.positive = positive;
		}
		
		public boolean getPositive() {
			return positive;
		}
	}
	private final Trimmable trimmable;
	private final boolean positive;
	
	public TrimCommand(Trimmable trimmable, TrimDirection direction) {
		this(trimmable, direction.getPositive());
	}
	
	public TrimCommand(Trimmable trimmable, boolean positive) {
		this.trimmable = trimmable;
		this.positive = positive;
	}
	
	@Override
	public void initialize() {
		trimmable.adjustTrim(positive);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
}
