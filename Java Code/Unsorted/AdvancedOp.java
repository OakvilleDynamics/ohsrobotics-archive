/* Copyright (c) 2014 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * TeleOp Mode
 * <p>
 * Enables control of the robot via the gamepad
 */
public class AdvancedOp extends OpMode {

	/*
	 * Note: the configuration of the servos is such that
	 * as the arm servo approaches 0, the arm position moves up (away from the floor).
	 * Also, as the claw servo approaches 0, the claw opens up (drops the game element).
	 */
    // TETRIX VALUES.
    final static double ARM_MIN_RANGE  = 0;
    final static double ARM_MAX_RANGE  = 1;
    final static double CLAW_MIN_RANGE  = 0;
    final static double CLAW_MAX_RANGE  = 1;
	final static double FLIP_MIN_RANGE = 0;
	final static double FLIP_MAX_RANGE = 1;
	final static double CLIMB_MIN_RANGE = 0;
	final static double CLIMB_MAX_RANGE = 1;
	// position of the arm servo.
	double armRightPosition;
	double armLeftPosition;
	double climbArmPosition;
	// amount to change the arm servo position.
	double armDelta = 0.02;

	// position of the claw servo
	double clawPosition;
	double flipPosition;
	
	// amount to change the claw servo position by
	double clawDelta = 0.3;
	double flipDelta = 0.3;

	DcMotor motorRight;
	DcMotor motorLeft;
	Servo claw;
	Servo armRight;
	Servo armLeft;
	Servo climbArm;
	Servo flip;

	/**
	 * Constructor
	 */
	public AdvancedOp() {
	}

	/*
	 * Code to run when the op mode is first enabled goes here
	 *
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
	 */
	@Override
	public void init() {
		/*
		 * Use the hardwareMap to get the dc motors and servos by name. Note
		 * that the names of the devices must match the names used when you
		 * configured your robot and created the configuration file.
		 */

		/*
		 * For the demo Tetrix K9 bot we assume the following,
		 *   There are two motors "motor_1" and "motor_2"
		 *   "motor_1" is on the right side of the bot.
		 *   "motor_2" is on the left side of the bot.
		 *
		 * We also assume that there are two servos "servo_1" and "servo_6"
		 *    "servo_1" controls the arm joint of the manipulator.
		 *    "servo_6" controls the claw joint of the manipulator.
		 */
		motorRight = hardwareMap.dcMotor.get("motor_1");
		motorLeft = hardwareMap.dcMotor.get("motor_2");
		motorLeft.setDirection(DcMotor.Direction.REVERSE);

		climbArm = hardwareMap.servo.get("servo_4"); //continous
		flip = hardwareMap.servo.get("servo_5");

		armLeft = hardwareMap.servo.get("servo_1");
		armRight = hardwareMap.servo.get("servo_3");
		claw = hardwareMap.servo.get("servo_2");

		// assign the starting position of the wrist and claw
		armRightPosition = 0;
		armLeftPosition = 180;
		clawPosition = 0;
		flipPosition = 0;
	}

	/*
	 * This method will be called repeatedly in a loop
	 *
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
	 */
	@Override
	public void loop() {

		/*
		 * Gamepad 1
		 *
		 * Gamepad 1 controls the motors via the left stick, and it controls the
		 * wrist/claw via the a,b, x, y buttons
		 */

        // tank drive
        // note that if y equal -1 then joystick is pushed all of the way forward.
        float left = -gamepad1.left_stick_y;
        float right = -gamepad1.right_stick_y;

		// clip the right/left values so that the values never exceed +/- 1
		right = Range.clip(right, -1, 1);
		left = Range.clip(left, -1, 1);

		// scale the joystick value to make it easier to control
		// the robot more precisely at slower speeds.
		right = (float)scaleInput(right);
		left =  (float)scaleInput(left);


		// write the values to the motors
		motorRight.setPower(right);
		motorLeft.setPower(left);

		
		float downClaw = -gamepad1.right_trigger;
		float upClaw = -gamepad1.left_trigger;
		float push = -gamepad2.left_trigger;
		float pull = -gamepad2.right_trigger;
		
        // update the position of the claw
        if (gamepad1.left_bumper) {
            clawPosition += clawDelta;
        }

        if (gamepad1.left_trigger > 0) {
            armRightPosition += armDelta*upClaw;
			armLeftPosition -= armDelta*upClaw;

        }

		if (gamepad1.right_trigger > 0) {
			armRightPosition -= armDelta*downClaw;
			armLeftPosition += armDelta*downClaw;

		}
		
		if (gamepad1.right_bumper) {
			clawPosition -= clawDelta;
		}
		
		/*
		if (gamepad2.left_trigger > 0) {
			climbArmPosition += armDelta*push;
		}
		
		if (gamepad2.right_trigger > 0) {
			climbArmPosition -= armDelta*pull;
		}
		*/
		
		while (gamepad2.left_trigger > 0) {
			climbArmPosition += armDelta*push;
		}
		while (gamepad2.right_trigger > 0) {
			climbArmPosition -= armDelta*pull;
		}
		if (gamepad2.left_trigger == 0 and gamepad2.right_trigger == 0) {
			climbArmPosition = 0.5;
		}		
		
		if (gamepad2.left_bumper) {
			flipPosition += flipDelta;
		}
		
		if gamepad2.right_bumper) {
			flipPosition -= flipDelta;
		}
		
		// clip the position values so that they never exceed their allowed range.
		armRightPosition = Range.clip(armRightPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
		armLeftPosition = Range.clip(armLeftPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
		clawPosition = Range.clip(clawPosition, CLAW_MIN_RANGE, CLAW_MAX_RANGE);
		flipPosition = Range.clip(flipPosition, FLIP_MIN_RANGE, FLIP_MAX_RANGE);
		climbArmPosition = Range.clip(climbArmPosition, CLIMB_MIN_RANGE, CLIMB_MAX_RANGE);
		
		// write position values to the wrist and claw servo
		armRight.setPosition(armRightPosition);
		armLeft.setPosition(armLeftPosition);
		claw.setPosition(clawPosition);
		climbArm.setPosition(climbArmPosition);
		flip.setPosition(flipPosition);
		/*
		 * Send telemetry data back to driver station. Note that if we are using
		 * a legacy NXT-compatible motor controller, then the getPower() method
		 * will return a null value. The legacy NXT-compatible motor controllers
		 * are currently write only.
		 */

		telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("armRight", "armRight:  " + String.format("%.2f", armRightPosition));
		telemetry.addData("armLeft", "armLeft:  " + String.format("%.2f", armLeftPosition));
		telemetry.addData("claw", "claw:  " + String.format("%.2f", clawPosition));
		telemetry.addData("left tgt pwr",  "left  pwr: " + String.format("%.2f", left));
		telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", right));
	}

	/*
	 * Code to run when the op mode is first disabled goes here
	 *
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
	 */
	@Override
	public void stop() {

	}

	/*
	 * This method scales the joystick input so for low joystick values, the
	 * scaled value is less than linear.  This is to make it easier to drive
	 * the robot more precisely at slower speeds.
	 */
	double scaleInput(double dVal)  {
		double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
				0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

		// get the corresponding index for the scaleInput array.
		int index = (int) (dVal * 16.0);

		// index should be positive.
		if (index < 0) {
			index = -index;
		}

		// index cannot exceed size of array minus 1.
		if (index > 16) {
			index = 16;
		}

		// get value from the array.
		double dScale = 0.0;
		if (dVal < 0) {
			dScale = -scaleArray[index];
		} else {
			dScale = scaleArray[index];
		}

		// return scaled value.
		return dScale;
	}

}
