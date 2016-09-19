package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.RockNRoller.RockerState;
import org.usfirst.frc4904.standard.commands.motor.MotorControl;
import org.usfirst.frc4904.standard.custom.controllers.Controller;

/**
 * Continously control the intake roller with a controller,
 * 
 * @see MotorControl
 * 
 */
public class InnieControl extends MotorControl {
	protected final TimIntake timIntake;
	
	public InnieControl() {
		super(RobotMap.Component.innie, RobotMap.HumanInput.Operator.stick, Controller.Y_AXIS, 1.0);
		timIntake = new TimIntake();
	}
	
	@Override
	protected void execute() {
		double speed = controller.getAxis(axis);
		boolean isDirectionIntake = (speed >= 0) && (scale >= 0);
		if (isDirectionIntake) {
			super.execute(); // run Innie from joystick (a la MotorControl)
			if (speed > RobotMap.Constant.HumanInput.TIM_DOWN_INTAKE_SPEED_THRESHOLD) {
				timIntake.start();
			} else {
				timIntake.cancel();
			}
		} else { // outtaking
			timIntake.cancel();
			if (speed > RobotMap.Constant.HumanInput.OPERATOR_Y_OUTTAKE_UPPER_THRESHOLD) { // if not past threshold
				stopMotors();
				return;
			}
			// do outtake:
			RobotMap.Component.rockNRoller.set(RockerState.OUTTAKE);
			RobotMap.Component.innie.set(scale * RobotMap.Constant.OUTTAKE_MOTOR_SPEED);
		}
	}
	
	private void stopMotors() {
		motor.set(0);
		RobotMap.Component.rockNRoller.set(RockerState.IDLE);
	}
	
	@Override
	protected void interrupted() {
		stopMotors();
	}
}