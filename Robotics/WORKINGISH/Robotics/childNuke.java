package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

//IDK WHAT THIS DOES SDKAFNSDKFGHSDKGHSDKLGHSDKGDSKHGSDKLHGSDKHFSDKLHGDSKLFSDKLGHSDKLG

public class childNuke extends OpMode {

    DcMotor motor_driveLeft;
    DcMotor motor_driveRight;

    public childNuke() {

    }

    @Override
    public void init () {
        motor_driveLeft = hardwareMap.dcMotor.get("motor_1");
        motor_driveRight = hardwareMap.dcMotor.get("motor_2");

    }

    @Override
    public void loop () {

        double leftPower = 0.0;
        double rightPower = 0.0;

        if (this.time <= 2) {
            leftPower = 0.0;
            rightPower = 0.0;
        } else if (this.time > 2 && this.time <= 7) {
            leftPower = 0.7;
            rightPower = 0.7;
        } else if (this.time > 7 && this.time <= 10) {
            leftPower = -0.5;
            rightPower = 0.5;
        }else if (this.time > 10 && this.time <= 13) {
            leftPower = 0.5;
            rightPower = -0.5;
        } else {
            leftPower = 0.0;
            rightPower = 0.0;
        }
        motor_driveLeft.setPower(leftPower);
        motor_driveLeft.setPower(rightPower);

        telemetry.addData("leftPower", "%5.2f", leftPower);
        telemetry.addData("rightPower", "%5.2f", rightPower);
        telemetry.addData("Text", "lolthisrobotisgoingtodieonthefieldandthereisnothingyoucandoaboutitlololololol");

    }

    @Override
    public void stop () {

    }
}
