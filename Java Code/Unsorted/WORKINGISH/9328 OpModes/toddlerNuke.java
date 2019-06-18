package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

public class toddlerNuke extends OpMode {

    DcMotor motor_driveLeft;
    DcMotor motor_driveRight;
    DcMotor motor_lift;

    public toddlerNuke () {

    }

    @Override
    public void init () {

        motor_driveLeft = hardwareMap.dcMotor.get("motor_1");
        motor_driveRight = hardwareMap.dcMotor.get("motor_2");
        motor_lift = hardwareMap.dcMotor.get("motor_lift");
        motor_driveLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop () {

        float leftPower = -gamepad1.left_stick_y;
        float rightPower = -gamepad1.right_stick_y;
        float liftPower = -gamepad2.left_stick_y;

        rightPower = Range.clip(rightPower, -1, 1);
        leftPower = Range.clip(leftPower, -1, 1);
        liftPower = Range.clip(liftPower, -1, 1);

        leftPower = (float)scaleInput(leftPower);
        rightPower = (float)scaleInput(rightPower);
        liftPower = (float)scaleInput(liftPower);

        motor_driveLeft.setPower(leftPower);
        motor_driveRight.setPower(rightPower);
        motor_lift.setPower(liftPower);

    }

    @Override
    public void stop () {

    }

    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };
        int index = (int) (dVal * 16.0);
        if (index < 0) {
            index = -index;
        } else if (index > 16) {
            index = 16;
        }
        //used to be double dScale = 0.0
        double dScale;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }
        return dScale;
    }

}