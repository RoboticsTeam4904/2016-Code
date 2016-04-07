package org.usfirst.frc4904.robot.commands;


import org.usfirst.frc4904.robot.RobotMap;
import org.usfirst.frc4904.robot.subsystems.RockNRoller.RockerState;
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
	public InnieControl() {
		super(RobotMap.Component.innie, RobotMap.HumanInput.Operator.stick, Controller.Y_AXIS, false);
		requires(RobotMap.Component.tim);
	}
	
	@Override
	protected void execute() {
		double speed = controller.getAxis(axis);
		boolean isDirectionIntake = (speed >= 0) && !invert;
		if (isDirectionIntake) {
			if (RobotMap.Component.shooter.isBallLoaded() && !RobotMap.Component.shooter.ballLoadOverride) {
				RobotMap.Component.tim.setPosition(Tim.TimState.DEFAULT);
				motor.set(RobotMap.Constant.INNIE_BALL_HOLD_SPEED);
			} else {
				RobotMap.Component.tim.setPosition(Tim.TimState.INTAKE); // not ideal, but difficult to run TimSet conditionally within this command
				super.execute(); // run Innie from joystick (a la MotorControl)
			}
		} else { // outtaking
			if (speed > RobotMap.Constant.HumanInput.OPERATOR_Y_OUTTAKE_UPPER_THRESHOLD) { // if not past threshold
				stopMotors();
				return;
			}
			// do outtake:
			RobotMap.Component.rockNRoller.set(RockerState.OUTTAKE);
			if (!invert) {
				RobotMap.Component.innie.set(RobotMap.Constant.OUTTAKE_MOTOR_SPEED);
			} else {
				RobotMap.Component.innie.set(-1.0f * RobotMap.Constant.OUTTAKE_MOTOR_SPEED);
			}
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