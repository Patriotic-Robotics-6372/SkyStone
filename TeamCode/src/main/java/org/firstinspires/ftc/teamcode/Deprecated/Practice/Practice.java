package org.firstinspires.ftc.teamcode.Deprecated.Practice;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/*
   author: Chris Tolosa
   date: 9/19/19
   summary: TeleOp for Patriotic Robotics.
   forward/backward
   strafe
   pivot
   lift up/down
   foundation movers
   intake pinch

*/

@TeleOp (name="Practice")
public class Practice extends OpMode {



    DcMotor backLeft;
    DcMotor backRight;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor lift;

    Servo pinch;
    Servo leftT;
    Servo rightT;

    double backLeftPower;
    double backRightPower;
    double frontLeftPower;
    double frontRightPower;


    @Override
    public void init() {

        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        lift = hardwareMap.dcMotor.get("lift");

        pinch = hardwareMap.servo.get("pinch");
        leftT = hardwareMap.servo.get("leftT");
        rightT = hardwareMap.servo.get("rightT");

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        //change number to position we want
        pinch.setPosition(0);
        leftT.setPosition(0);
        rightT.setPosition(0);

        telemetry.addData("TeleOp", "Robot is Ready");
        telemetry.update();

    }

    @Override
    public void loop() {

        //change number to appropriate numbers
        int close = 0;
        int open = 90;
        int degrees = 0;

        //move forward and backward
        backLeft.setPower(-gamepad1.left_stick_y);
        frontLeft.setPower(-gamepad1.left_stick_y);
        backRight.setPower(-gamepad1.right_stick_y);
        frontRight.setPower(-gamepad1.right_stick_y);

        //strafe left
        if (gamepad1.left_stick_x < -.1 ){
            backLeft.setPower(gamepad1.left_stick_x);
            frontLeft.setPower(-gamepad1.left_stick_x);
            backRight.setPower(-gamepad1.left_stick_x);
            frontRight.setPower(gamepad1.left_stick_x);
        } else {
            backLeft.setPower(0);
            frontLeft.setPower(0);
            backRight.setPower(0);
            frontRight.setPower(0);
        }

        //strafe right
        if (gamepad1.left_stick_x > .1 ){
            backLeft.setPower(-gamepad1.left_stick_x);
            frontLeft.setPower(gamepad1.left_stick_x);
            backRight.setPower(gamepad1.left_stick_x);
            frontRight.setPower(-gamepad1.left_stick_x);
        } else {
            backLeft.setPower(0);
            frontLeft.setPower(0);
            backRight.setPower(0);
            frontRight.setPower(0);
        }

        //intake open
        if (gamepad1.right_bumper) {
            pinch.setPosition(open);
        } else {
            pinch.setPosition(close);
        }

        //up lift
        if (gamepad2.b) {
            lift.setPower(.5);
        } else {
            lift.setPower(0);
        }

        //down lift
        if (gamepad2.a){
            lift.setPower(-.5);
        } else {
            lift.setPower(0);
        }

        //pivot turn right
        if (gamepad1.dpad_right){
            backLeft.setPower(.5);
            frontLeft.setPower(.5);
            backRight.setPower(-.5);
            frontRight.setPower(-.5);
        } else {
            backLeft.setPower(0);
            frontLeft.setPower(0);
            backRight.setPower(0);
            frontRight.setPower(0);
        }

        //pivot turn left
        if (gamepad1.dpad_left){
            backLeft.setPower(-.5);
            frontLeft.setPower(-.5);
            backRight.setPower(.5);
            frontRight.setPower(.5);
        } else {
            backLeft.setPower(0);
            frontLeft.setPower(0);
            backRight.setPower(0);
            frontRight.setPower(0);
        }

        //change T's
        if (gamepad2.dpad_up){
            degrees += 90;
            if (degrees > 180) {
                degrees = 0;
            }
            leftT.setPosition(degrees);
        }
        if (gamepad2.dpad_up){
            degrees += 90;
            if (degrees > 180) {
                degrees = 0;
            }
            rightT.setPosition(degrees);
        }






    }
}
