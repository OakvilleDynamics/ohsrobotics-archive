package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class cryingNuke extends OpMode {

    DcMotor motor_driveLeft;
    DcMotor motor_driveRight;
    DcMotor motor_lift;

    public cryingNuke() {

    }

    @Override
    public void init () {

        motor_driveLeft = hardwareMap.dcMotor.get("motor_lift");
        motor_driveRight = hardwareMap.dcMotor.get("motor_2");
        motor_driveLeft.setDirection(DcMotor.Direction.REVERSE);
        motor_lift =  hardwareMap.dcMotor.get("motor_1");

    }

    @Override
    public void loop() {

        double leftPower = 0.0;
        double rightPower = 0.0;

        if (this.time <= 2) {
            leftPower = 0.0;
            rightPower = 0.0;
        } else if (this.time > 2 && this.time <= 7) {
            leftPower = 0.7;
            rightPower = 0.7;
        //Dancing Movements
        /*
        } else if (this.time > 7 && this.time <= 9) {
            leftPower = 0.7;
            rightPower = -0.7;
        } else if (this.time > 9 && this.time <= 11) {
            leftPower = -0.7;
            rightPower = 0.7;
        } else if (this.time > 11 && this.time <= 13) {
            leftPower = 0.7;
            rightPower = -0.7;
        } else if (this.time > 13 && this.time <= 15) {
            leftPower = -0.7;
            rightPower = 0.7;
        } else if (this.time > 15 && this.time <= 17) {
            leftPower = 0.7;
            rightPower = -0.7;
        } else if (this.time > 17 && this.time <= 18) {
            leftPower = 0.7;
            rightPower = 0.7;
        } else if (this.time > 19 && this.time <= 20) {
            leftPower = -0.7;
            rightPower = -0.7;
        */
        } else {
            leftPower = 0.0;
            rightPower = 0.0;
        }

        motor_driveLeft.setPower(leftPower);
        motor_driveRight.setPower(rightPower);
///        motor_lift.setPower(leftPower);
        telemetry.addData("leftPower", "%5.2f", leftPower);
        telemetry.addData("rightPower", "%5.2f", rightPower);
    }

    @Override
    public void stop () {

    }

}