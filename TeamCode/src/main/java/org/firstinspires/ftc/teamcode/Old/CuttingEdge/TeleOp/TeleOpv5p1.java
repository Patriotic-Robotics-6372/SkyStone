package org.firstinspires.ftc.teamcode.Old.CuttingEdge.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
@TeleOp(name="Driver Control Stable v2")
public class TeleOpv5p1 extends OpMode {

    DcMotor backLeft, backRight, frontLeft, frontRight,
            lift,
            leftPivotIntake, rightPivotIntake;

    CRServo leftPinch, rightPinch;

    int leftSideStatus, rightSideStatus,
            liftStatus,
            intakeStatus,
            pivotStatus,
            strafeStatus;

    double baseSpeed, pivotSpeed, strafeSpeed, liftSpeed,
            open, close,
            fL, fR;

    private ElapsedTime runtime = new ElapsedTime();

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
        pivotSpeed = 0.0;
        open = 0.4;
        close = -0.4;
        liftSpeed = 1;

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        leftPinch.setPower(open);
        rightPinch.setPower(close);

        telemetry.addData("Driver Control", "Robot is Ready");
        updateTelemetry(telemetry);
        telemetry.update();

    }

    @Override
    public void loop() {

        //change variables to actual numbers (we need to test it first)

        robotStatus();

        // tank drive right
        if (Math.abs(gamepad1.right_stick_y) > .1) {
            frontRight.setPower(-gamepad1.right_stick_y);
            backRight.setPower(-gamepad1.right_stick_y);
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

        if (gamepad2.b) {
            lift.setPower(liftSpeed);
            liftStatus = 1;
            robotStatus();
        } else {
            lift.setPower(0);
            liftStatus = 0;
        }
        //down lift
        if (gamepad2.a) {
            lift.setPower(-liftSpeed);
            liftStatus = -1;
            robotStatus();
        } else {
            lift.setPower(0);
            liftStatus = 0;
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

        if (gamepad2.right_bumper) {
            leftPinch.setPower(close);
            rightPinch.setPower(open);
            intakeStatus = 1;
        } else if (gamepad2.left_bumper) {
            leftPinch.setPower(open);
            rightPinch.setPower(close);
            intakeStatus = -1;
        } else {
            leftPinch.setPower(0);
            rightPinch.setPower(0);
            intakeStatus = 0;
        }
        //up lift

        //pivot intake up
        if (gamepad2.dpad_up) {
            leftPivotIntake.setPower(pivotSpeed);
            rightPivotIntake.setPower(-pivotSpeed);
            pivotStatus = 1;
            robotStatus();
        } else if (gamepad2.dpad_down) {
            leftPivotIntake.setPower(-pivotSpeed/2);
            rightPivotIntake.setPower(pivotSpeed/2);
            pivotStatus = -1;
            robotStatus();
        } else {
            leftPivotIntake.setPower(0);
            rightPivotIntake.setPower(0);
            pivotStatus = 0;
        }
    }


    /* variables for state of robot:
        / / / / -1 / / / / 0 / / / / 1 / / / /
        leftSide: backwards, neutral, forwards
        rightSide: backwards, neutral, forwards
        lift: down, neutral, up
        intake: open, neutral, closed
        pivot: down, neutral, up
        strafe: left, neutral, right
    */

    public void robotStatus() {

        telemetry.addData("TimeElapsed: ", runtime);

        fL = frontLeft.getPower();
        fR = frontRight.getPower();


        switch (leftSideStatus) {
            case -1:
                telemetry.addData("leftSide: ", "Forwards: " + fL);
                break;
            case 0:
                telemetry.addData("leftSide: ", "Neutral" + "0");
                break;
            case 1:
                telemetry.addData("leftSide: ", "Backwards: " + fL);
                break;
        }

        switch (rightSideStatus) {
            case -1:
                telemetry.addData("rightSide: ", "Forwards: " + fR);
                break;
            case 0:
                telemetry.addData("rightSide: ", "Neutral");
                break;
            case 1:
                telemetry.addData("rightSide: ", "Backwards: " + fR);
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
        }

        switch (strafeStatus) {
            case -1:
                telemetry.addData("strafe: ", "Left: " + backLeft.getPower());
                break;
            case 0:
                telemetry.addData("strafe: ",  "Neutral");
                break;
            case 1:
                telemetry.addData("strafe: ", "Right: " + backRight.getPower());
        }

        telemetry.addData("pivotSpeed: ", pivotSpeed);
        telemetry.addData("strafeSpeed: ", strafeSpeed);
        telemetry.addData("leftPinch: ", leftPinch.getPower());
        telemetry.addData("rightPinch: ", rightPinch.getPower());
        telemetry.addData("frontRight: ", rightPinch.getPower());
        telemetry.addData("frontLeft: ", rightPinch.getPower());
        telemetry.addData("backRight: ", rightPinch.getPower());
        telemetry.addData("backLeft: ", rightPinch.getPower());
        updateTelemetry(telemetry);
        telemetry.update();
    }
}
