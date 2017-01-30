package org.usfirst.frc4904.sovereignty;


public interface Trimmable {
	void setTrimIncrement(double increment);
	
	double getTrimIncrement();
	
	void setTrim(double trim);
	
	double getTrim();
	
	default void adjustTrim(boolean positive) {
		adjustTrim(positive ? getTrimIncrement() : -getTrimIncrement());
	};
	
	default void adjustTrim(double change) {
		setTrim(getTrim() + change);
	};
}
