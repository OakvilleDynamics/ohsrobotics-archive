package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
///import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class infantNuke extends OpMode {

    DcMotor motor_driveLeft;
    DcMotor motor_driveRight;
///    DcMotor motor_winch;
    DcMotor motor_neatTrain;
///    DcMotor motor_launch;
///    DcMotor motor_launch2;
    //DcMotor motor_launch2;
///    Servo rightArm;
///    Servo leftArm;

   // Servo idkwhatthisoneisfor;

///    double leftArmPosition;
///    double leftArmDelta = 0.1;
///    double rightArmPosition;
///    double rightArmDelta = 0.1;

    //Wasn't here before
///    final static double ARM_MIN_RANGE  = 0.20;
///    final static double ARM_MAX_RANGE  = 0.90;

    public infantNuke () {

    }

    @Override
    public void init () {

        motor_driveLeft = hardwareMap.dcMotor.get("motor_driveLeft");
        motor_driveRight = hardwareMap.dcMotor.get("motor_driveRight");
        motor_driveLeft.setDirection(DcMotor.Direction.REVERSE);
///        motor_winch = hardwareMap.dcMotor.get("motor_winch");
        motor_neatTrain = hardwareMap.dcMotor.get("motor_neatTrain");
///        motor_launch = hardwareMap.dcMotor.get("motor_launch");
///        motor_launch2 = hardwareMap.dcMotor.get("motor_launch2");
///        motor_launch2.setDirection(DcMotor.Direction.REVERSE);


///        rightArm = hardwareMap.servo.get("servo_right");
///        leftArm = hardwareMap.servo.get("servo_left");

///        rightArmPosition = 0.2;
///        leftArmPosition = 0.2;

        // sound stuff :)

    }

    @Override
    public void loop() {

///        float leftDrivePower = -gamepad1.left_stick_y;
        float rightDrivePower = -gamepad1.right_stick_x;
        float neatTrainPower = -gamepad1.left_stick_y;
        //launch = 0 on baby
///        double launchPower;
///        double winchPower;

///        leftDrivePower  = Range.clip(leftDrivePower, -1, 1);
        rightDrivePower = Range.clip(rightDrivePower, -1, 1);
        neatTrainPower = Range.clip(neatTrainPower, -1, 1);

///        leftDrivePower  = (float)scaleInput(leftDrivePower);
        rightDrivePower = (float)scaleInput(rightDrivePower);
        neatTrainPower = (float)scaleInput(neatTrainPower);


///        if (gamepad2.a) {
///            launchPower = 1.0;
///        } else {
///            launchPower = 0.0;
///        }

///        if (gamepad1.left_bumper) {
///            winchPower = 1.0;
///        } else {
///            winchPower = 0.0;
///        }

///        if (gamepad2.a) {
            // if the A button is pushed on gamepad1, increment the position of
            // the arm servo.
///            rightArmPosition += rightArmDelta;
///        }

///        if (gamepad2.y) {
            // if the Y button is pushed on gamepad1, decrease the position of
            // the arm servo.
///            rightArmPosition -= rightArmDelta;
///        }

///        if (gamepad2.dpad_down) {
            // if the A button is pushed on gamepad1, increment the position of
            // the arm servo.
///            leftArmPosition += leftArmDelta;
///        }

///        if (gamepad2.dpad_up) {
            // if the Y button is pushed on gamepad1, decrease the position of
            // the arm servo.
///            leftArmPosition -= leftArmDelta;
///        }

///        if (gamepad2.right_trigger)


///        motor_driveLeft.setPower(leftDrivePower);
        motor_driveRight.setPower(rightDrivePower);
        motor_neatTrain.setPower(neatTrainPower);
///        motor_launch.setPower(launchPower);
///        motor_launch2.setPower(launchPower);
     //   motor_launch2.setPower(launchPower);
///        motor_winch.setPower(winchPower);

        //Wasn't here before
///        rightArmPosition = Range.clip(rightArmPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
///        leftArmPosition = Range.clip(leftArmPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);

        telemetry.addData("Text", "get rekt m8. remember, we r # 1. ;)");

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

    // song stuff :)
    /*private MediaPlayer mp = new MediaPlayer();
    private ToggleUtility mediaPlay = new ToggleUtility();
    private final String mediaPath = "/storage/emulated/0/Music";
    private final String mediaFile = "Never Gonna Give You Up.mp3";

    if(!mp.isPlaying())
            mp.start();

    else
    {
        mp.stop();

        try
        {
            mp = new MediaPlayer();
            mp.setDataSource(mediaPath + "/" + mediaFile);
            mp.prepare();
        }
        catch(Exception e)
        {
            telemetry.addData("Error" , "ahahahaha");
            telemetry.update();
        }
    }*/

}