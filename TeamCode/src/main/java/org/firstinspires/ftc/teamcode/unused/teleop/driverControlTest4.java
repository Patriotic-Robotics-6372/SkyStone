package org.firstinspires.ftc.teamcode.unused.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Driver Control Tests")
public class driverControlTest4 extends OpMode {

    DcMotor backLeft;
    DcMotor backRight;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor lift;
    DcMotor leftPivotIntake;
    DcMotor rightPivotIntake;

    Servo leftPinch;
    Servo rightPinch;

    @Override
    public void init() {
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        lift = hardwareMap.dcMotor.get("leftLift");
        leftPivotIntake = hardwareMap.dcMotor.get("leftPivot");
        rightPivotIntake = hardwareMap.dcMotor.get("rightPivot");

        leftPinch = hardwareMap.servo.get("leftPinch");
        rightPinch = hardwareMap.servo.get("rightPinch");

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        /*leftPinch.setPosition(0);
        rightPinch.setPosition(180);*/

        telemetry.addData("Driver Control", "Robot is Ready");
        updateTelemetry(telemetry);
        telemetry.update();

    }

    @Override
    public void loop() {

        //change variables to actual numbers (we need to test it first)
        int degrees = 0;
        int open = 180;
        int close = 0;

        telemetry.addData("right_stick_y: ", gamepad1.right_stick_y);
        telemetry.addData("left_stick_y: ", gamepad1.left_stick_y);
        telemetry.addData("Intake: ", "open");
        updateTelemetry(telemetry);
        telemetry.update();

        // tank drive right
        if (Math.abs(gamepad1.right_stick_y) > .1) {
            frontRight.setPower(-gamepad1.right_stick_y);
            backRight.setPower(-gamepad1.right_stick_y);

            telemetry.addData("right_stick_y: ", gamepad1.right_stick_y);
            telemetry.addData("left_stick_y: ", gamepad1.left_stick_y);
            updateTelemetry(telemetry);
            telemetry.update();

        } else {
            frontRight.setPower(0);
            backRight.setPower(0);
        }
        // tank drive left
        if (Math.abs(gamepad1.left_stick_y) > .1) {
            frontLeft.setPower(-gamepad1.left_stick_y);
            backLeft.setPower(-gamepad1.left_stick_y);

            telemetry.addData("right_stick_y: ", gamepad1.right_stick_y);
            telemetry.addData("left_stick_y: ", gamepad1.left_stick_y);
            updateTelemetry(telemetry);
            telemetry.update();

        } else {
            frontLeft.setPower(0);
            backLeft.setPower(0);
        }

        if (gamepad1.dpad_left) {
            frontLeft.setPower(-.8);
            frontRight.setPower(.8);
            backLeft.setPower(.8);
            backRight.setPower(-.8);

            telemetry.addData("Strafing: ", "Left");
            updateTelemetry(telemetry);
            telemetry.update();

        } else {
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
        }

        if (gamepad1.dpad_right) {
            frontLeft.setPower(.8);
            frontRight.setPower(-.8);
            backLeft.setPower(-.8);
            backRight.setPower(.8);

            telemetry.addData("Strafing: ", "Left");
            updateTelemetry(telemetry);
            telemetry.update();
        } else {
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
        }
        /*
        //strafe left
        if (gamepad1.dpad_left){
            //backLeft.setPower(.5);
            frontLeft.setPower(.5);
            backRight.setPower(.5);
            frontRight.setPower(.5);
        } else {
            //backLeft.setPower(0);
            frontLeft.setPower(0);
            backRight.setPower(0);
            frontRight.setPower(0);
        }

        //strafe right
        if (gamepad1.dpad_right){
            backLeft.setPower(.5);
            frontLeft.setPower(.5);
            backRight.setPower(.5);
            frontRight.setPower(.5);
        } else {
            backLeft.setPower(0);
            frontLeft.setPower(0);
            backRight.setPower(0);
            frontRight.setPower(0);
        }
        */
        //intake open
        if (gamepad2.right_bumper) {
            leftPinch.setPosition(close);
            rightPinch.setPosition(open);

            telemetry.addData("Intake: ", "closed");
            updateTelemetry(telemetry);
            telemetry.update();

        }
        if (gamepad2.left_bumper) {
            leftPinch.setPosition(open);
            rightPinch.setPosition(close);

            telemetry.addData("Intake: ", "open");
            updateTelemetry(telemetry);
            telemetry.update();

        }
        //up lift
        if (gamepad2.b) {
            lift.setPower(.5);

            telemetry.addData("Lifting: ", "Up");
            updateTelemetry(telemetry);
            telemetry.update();

        } else {
            lift.setPower(0);
        }
        //down lift
        if (gamepad2.a){
            lift.setPower(-.5);

            telemetry.addData("Lifting: ", "Down");
            updateTelemetry(telemetry);
            telemetry.update();

        } else {
            lift.setPower(0);
        }
        /*
        //pivot turn right
        if (gamepad1.right_trigger > .1){
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
        if (gamepad1.left_trigger > .1){
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
         */
        //pivot intake up
        if (gamepad2.dpad_up) {
            leftPivotIntake.setPower(-.6);
            rightPivotIntake.setPower(.6);

            telemetry.addData("Pivoting: ", "Up");
            updateTelemetry(telemetry);
            telemetry.update();
        } else if (gamepad2.dpad_down){
            leftPivotIntake.setPower(.6);
            rightPivotIntake.setPower(-.6);

            telemetry.addData("Pivoting: ", "Down");
            updateTelemetry(telemetry);
            telemetry.update();
        } else {
            leftPivotIntake.setPower(0);
            rightPivotIntake.setPower(0);
        }
    }
}
