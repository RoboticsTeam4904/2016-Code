package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.Tim;
import org.usfirst.frc4904.standard.commands.motor.MotorControl;
import org.usfirst.frc4904.standard.custom.controllers.Controller;

/**
 * Continously control the intake roller with a controller,
 * 
 * @see MotorControl
 * 
 */
public class InnieControl extends MotorControl {
	protected final TimSpin timSpin;
	protected final TimSet timSet;
	protected final Outtake outtake;
	
	public InnieControl() {
		super(RobotMap.Component.innie, RobotMap.HumanInput.Operator.stick, Controller.Y_AXIS, 1.0);
		timSpin = new TimSpin(true);
		timSet = new TimSet(Tim.TimState.FULL_DOWN, false);
		outtake = new Outtake();
	}
	
	@Override
	protected void execute() {
		double speed = controller.getAxis(axis);
		boolean isDirectionIntake = (speed >= 0) && (scale >= 0);
		if (isDirectionIntake) {
			if (outtake.isRunning()) {
				outtake.cancel();
			}
			super.execute(); // run Innie from joystick (a la MotorControl)
			if (speed > RobotMap.Constant.HumanInput.TIM_DOWN_INTAKE_SPEED_THRESHOLD) {
				timSpin.start();
				if (((TimSet) RobotMap.Component.tim.getCurrentCommand()).getState().equals(Tim.TimState.FULL_UP)) {
					timSet.start();
				}
			} else {
				timSpin.cancel();
				if (timSet.isRunning()) {
					timSet.cancel();
				}
			}
		} else { // outtaking
			timSpin.cancel();
			if (timSet.isRunning()) {
				timSet.cancel();
			}
			if (speed > RobotMap.Constant.HumanInput.OPERATOR_Y_OUTTAKE_UPPER_THRESHOLD) { // if not past threshold
				if (outtake.isRunning()) {
					outtake.cancel();
				}
				return;
			}
			outtake.start();
		}
	}
	
	@Override
	protected void interrupted() {
		motor.set(0);
	}
}