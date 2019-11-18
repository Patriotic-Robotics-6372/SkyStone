package org.firstinspires.ftc.robotcontroller;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Base Test")
public class baseTest extends OpMode {

    DcMotor backLeft, backRight, frontLeft, frontRight,
            lift,
            leftPivotIntake, rightPivotIntake;

    CRServo leftPinch, rightPinch;

    int leftSideStatus, rightSideStatus,
            liftStatus,
            intakeStatus,
            pivotStatus,
            strafeStatus;

    double strafeSpeed;

    @Override
    public void init() {
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        lift = hardwareMap.dcMotor.get("leftLift");
        leftPivotIntake = hardwareMap.dcMotor.get("leftPivot");
        rightPivotIntake = hardwareMap.dcMotor.get("rightPivot");

        leftPinch = hardwareMap.get(CRServo.class, "leftPinch");
        rightPinch = hardwareMap.get(CRServo.class, "rightPinch");

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        strafeSpeed = 1;
    }

    @Override
    public void loop() {

        double strafeSpeed = 1;

        if (Math.abs(gamepad1.right_stick_y) > .1) {
            frontRight.setPower(-gamepad1.right_stick_y);
            backRight.setPower(-gamepad1.right_stick_y);

            if (gamepad1.right_stick_y >= .1) {
                rightSideStatus = 1;
            } else if (gamepad1.right_stick_y <= -.1) {
                rightSideStatus = -1;
            } else {
                rightSideStatus = 0;
            }
            robotStatus();
        } else {
            frontRight.setPower(0);
            backRight.setPower(0);

            rightSideStatus = 0;
        }
        // tank drive left
        if (Math.abs(gamepad1.left_stick_y) > .1) {
            frontLeft.setPower(-gamepad1.left_stick_y);
            backLeft.setPower(-gamepad1.left_stick_y);

            if (gamepad1.left_stick_y >= .1) {
                leftSideStatus = 1;
            } else if (gamepad1.left_stick_y <= -.1) {
                leftSideStatus = -1;
            } else {
                leftSideStatus = 0;
            }
            robotStatus();
        } else {
            frontLeft.setPower(0);
            backLeft.setPower(0);

            leftSideStatus = 0;
        }

        // strafing left
        if (gamepad1.dpad_left) {
            frontLeft.setPower(-strafeSpeed);
            frontRight.setPower(strafeSpeed);
            backLeft.setPower(strafeSpeed);
            backRight.setPower(-strafeSpeed);

            strafeStatus = -1;
            robotStatus();

        } else {
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            strafeStatus = 0;
        }
        // strafing right
        if (gamepad1.dpad_right) {
            frontLeft.setPower(strafeSpeed);
            frontRight.setPower(-strafeSpeed);
            backLeft.setPower(-strafeSpeed);
            backRight.setPower(strafeSpeed);

            strafeStatus = 1;
            robotStatus();
        } else {
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            strafeStatus = 0;
        }

    }
    public void robotStatus() {
        switch (leftSideStatus) {
            case -1:
                telemetry.addData("leftSide: ", "Forwards: " + frontLeft.getPower());
                break;
            case 0:
                telemetry.addData("leftSide: ", "Neutral" + "0");
                break;
            case 1:
                telemetry.addData("leftSide: ", "Backwards: " + frontLeft.getPower());
                break;
            default:
                break;
        }

        switch (rightSideStatus) {
            case -1:
                telemetry.addData("rightSide: ", "Forwards: " + frontRight.getPower());
                break;
            case 0:
                telemetry.addData("rightSide: ", "Neutral");
                break;
            case 1:
                telemetry.addData("rightSide: ", "Backwards: " + frontRight.getPower());
                break;
            default:
                break;
        }

        switch (liftStatus) {
            case -1:
                telemetry.addData("lift: ", "Down: " + lift.getPower());
                break;
            case 0:
                telemetry.addData("lift: ", "Neutral");
                break;
            case 1:
                telemetry.addData("lift: ", "Up: " + lift.getPower());
            default:
                break;
        }

        switch (intakeStatus) {
            case -1:
                telemetry.addData("intake: ", "Open: " + leftPinch.getPower());
                break;
            case 0:
                telemetry.addData("intake: ", "Neutral");
                break;
            case 1:
                telemetry.addData("intake: ", "Close: " + leftPinch.getPower());
                break;
            default:
                break;
        }

        switch (pivotStatus) {
            case -1:
                telemetry.addData("pivot: ", "Lowered: " + leftPivotIntake.getPower());
                break;
            case 0:
                telemetry.addData("pivot: ", "Neutral");
                break;
            case 1:
                telemetry.addData("pivot: ", "Raised: " + leftPivotIntake.getPower());
            default:
                break;

        }

        switch (strafeStatus) {
            case -1:
                telemetry.addData("strafe: ", "Left: " + backLeft.getPower());
                break;
            case 0:
                telemetry.addData("strafe: ", "Neutral");
                break;
            case 1:
                telemetry.addData("strafe: ", "Right: " + backRight.getPower());
            default:
                break;
        }

        //telemetry.addData("leftSide: ", leftSideStatus);
        //telemetry.addData("rightSide: ", rightSideStatus);
        //telemetry.addData("lift: ", liftStatus);
        //telemetry.addData("intake: ", intakeStatus);
        //telemetry.addData("pivot: ", pivotStatus);
        //telemetry.addData("strafe: ", strafeStatus);
        /*telemetry.addData("pivotSpeed: ", pivotSpeed);
        telemetry.addData("strafeSpeed: ", strafeSpeed);
        telemetry.addData("strafeToggle: ", strafeToggle);
        telemetry.addData("strafeToggle2: ", strafeToggle2);*/
        updateTelemetry(telemetry);
        telemetry.update();
    }
}
