package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
//I WROTE THIS WITHOUT ANY IDEA ON WHAT IT DOES PLZ HEALPDSHRWSHGKSEGNESJHGWGHSEHGRGHERSJGRJBGDRJGBRGBDJBDFKJ
//gg garry ni ce work fellow programmer u have earned senpais respect

public class fetusNuke extends OpMode {

    DcMotor motor_driveLeft;
    DcMotor motor_driveRight;
    DcMotor motor_ball;
    Servo servo_ballLeft;
    Servo servo_ballRight;

    public fetusNuke () {

    }

    @Override
    public void init () {

        motor_driveLeft = hardwareMap.dcMotor.get("motor_1");
        motor_driveRight = hardwareMap.dcMotor.get("motor_2");
        motor_ball = hardwareMap.dcMotor.get("motor_ball");
        motor_driveLeft.setDirection(DcMotor.Direction.REVERSE);
        servo_ballLeft = hardwareMap.servo.get("servo_1");
        servo_ballRight = hardwareMap.servo.get("servo_2");
        //servos suck
        double ballRightPosition = 0.0;
        double ballLeftPosition = 0.0;
    }

    @Override
    public void loop() {
        //Servos might be broken lol
        float leftPower = -gamepad1.left_stick_y;
        float rightPower = -gamepad1.right_stick_y;
        float ballWheelPower = gamepad2.right_stick_y;
        float ballLeftPosition = gamepad2.left_trigger;
        float ballRightPosition = gamepad2.right_trigger;

        leftPower = Range.clip(leftPower, -1, 1);
        rightPower = Range.clip(rightPower, -1, 1);
        ballLeftPosition = Range.clip(ballLeftPosition, -1, 1);
        ballRightPosition = Range.clip(ballRightPosition, -1, 1);
        ballWheelPower = Range.clip(ballWheelPower, -1, 1);

        leftPower = (float)scaleInput(leftPower);
        rightPower = (float)scaleInput(rightPower);
        ballWheelPower = (float)scaleInput(ballWheelPower);
        ballLeftPosition = (float)scaleInput(ballLeftPosition);
        ballRightPosition = (float)scaleInput(ballRightPosition);

        motor_driveLeft.setPower(leftPower);
        motor_driveRight.setPower(rightPower);
        motor_ball.setPower(ballWheelPower);
        servo_ballLeft.setPosition(ballLeftPosition);
        servo_ballRight.setPosition(ballRightPosition);

        telemetry.addData("leftPower", "%5.2f", leftPower);
        telemetry.addData("rightPower", "%5.2f", rightPower);
        telemetry.addData("ballWheelPower", "%5.2f", ballWheelPower);
        telemetry.addData("ballLeftPosition", "%5.2f", ballLeftPosition);
        telemetry.addData("ballRightPosition", "%5.2f", ballRightPosition);
        telemetry.addData("Text", "idkwhythisrobotisntdrivingatallmaybeyoubrokesomethingprogramminghastoberight");

    }

    @Override
    public void stop () {

    }
        //idkwtfisatnhissjhbsfdsvdvsf]ugy
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