package org.firstinspires.ftc.teamcode.CuttingEdge.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
@TeleOp(name="Driver Control Prototype")
public class TeleOpv6 extends OpMode {

    private DcMotor backLeft, backRight, frontLeft, frontRight,
            lift,
            leftPivotIntake, rightPivotIntake;

    private CRServo leftPinch, rightPinch;

    private short leftSideStatus, rightSideStatus,
            liftStatus,
            intakeStatus,
            pivotStatus,
            strafeStatus;

    private double baseSpeed, pivotIntakeSpeed, strafeSpeed, liftSpeed,
            leftSideSpeed, rightSideSpeed,
            open, close,
            fL, fR, bL, bR, L, lP, lPI,
            leftStick1, rightStick1;

    private ElapsedTime runtime = new ElapsedTime();

    private boolean controller1, dpadLeft1, dpadRight1, dpadUp1, dpadDown1, controller2, dpadUp2, dpadDown2, rightBumper2, leftBumper2, a2, b2, x2, y2;

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
        rightPivotIntake.setDirection(DcMotorSimple.Direction.REVERSE);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        telemetry.addData("Driver Control", "Robot is Ready");
        updateTelemetry(telemetry);
        telemetry.update();

        baseSpeed = 1;
        strafeSpeed = 1;
        pivotIntakeSpeed = 0.6;
        liftSpeed = 0.5;
        open = 0.4;
        close = -0.4;
    }

    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {

        leftStick1 = gamepad1.left_stick_y;
        rightStick1 = -gamepad1.right_stick_y;
        dpadLeft1 = gamepad1.dpad_left;
        dpadRight1 = gamepad1.dpad_right;
        dpadUp1 = gamepad1.dpad_up;
        dpadDown1 = gamepad1.dpad_down;
        dpadUp2 = gamepad2.dpad_up;
        dpadDown2 = gamepad2.dpad_down;
        a2 = gamepad2.a;
        b2 = gamepad2.b;
        x2 = gamepad2.x;
        y2 = gamepad2.y;
        rightBumper2 = gamepad2.right_bumper;
        leftBumper2 = gamepad2.left_bumper;
        controller1 = (leftStick1 != 0) || (rightStick1 != 0) || dpadLeft1 || dpadRight1 || dpadUp1 || dpadDown1;
        controller2 = dpadUp2 || dpadDown2 || a2 || b2 || rightBumper2 || leftBumper2 || x2 || y2;

        if (!controller1 && !controller2) {
            robotStatus();
        }

        // tank drive right
        if (Math.abs(rightStick1) > .1) {
            // speed modifier to find actual speed
            rightSideSpeed = rightStick1 * baseSpeed;
            // limiting speed to valid values
            if (rightSideSpeed > 1) {
                rightSideSpeed = 1;
            } else if (rightSideSpeed < -1) {
                rightSideSpeed = -1;
            }
            // set powers of right side
            frontRight.setPower(rightSideSpeed);
            backRight.setPower(rightSideSpeed);
            // set status of right side
            if (rightStick1 >= .1) {
                rightSideStatus = 1;
            } else if (rightStick1 <= -.1) {
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
        if (Math.abs(leftStick1) > .1) {
            if (leftSideSpeed > 1) {
                leftSideSpeed = 1;
            } else if (leftSideSpeed < -1) {
                leftSideSpeed = -1;
            }
            frontLeft.setPower(leftSideSpeed);
            backLeft.setPower(leftSideSpeed);
            if (leftStick1 >= .1) {
                leftSideStatus = 1;
            } else if (leftStick1 <= -.1) {
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
        if (dpadLeft1) {
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
        if (dpadRight1) {
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

        if (dpadUp1) {
            baseSpeed = 1;
            strafeSpeed = 1;
        }
        if (dpadDown1) {
            baseSpeed = 0.5;
            strafeSpeed = 0.5;
        }

        if (b2) {
            lift.setPower(liftSpeed);
            liftStatus = 1;
            robotStatus();
        } else {
            lift.setPower(0);
            liftStatus = 0;
        }
        //down lift
        if (a2) {
            lift.setPower(-liftSpeed);
            liftStatus = -1;
            robotStatus();
        } else {
            lift.setPower(0);
            liftStatus = 0;
        }

        if (x2) {
            liftSpeed = 0.5;
        }

        if (y2) {
            liftSpeed = 1;
        }

        if (rightBumper2) {
            leftPinch.setPower(close);
            rightPinch.setPower(open);
            intakeStatus = -1;
            robotStatus();
        } else {
            intakeStatus = 0;
        }
        if (leftBumper2) {
            leftPinch.setPower(open);
            rightPinch.setPower(close);
            intakeStatus = 1;
            robotStatus();
        } else {
            intakeStatus = 0;
        }

        //pivot intake up
        if (dpadUp2) {
            leftPivotIntake.setPower(pivotIntakeSpeed);
            rightPivotIntake.setPower(pivotIntakeSpeed);
            pivotStatus = 1;
            robotStatus();
        } else if (dpadDown2) {
            leftPivotIntake.setPower(-pivotIntakeSpeed);
            rightPivotIntake.setPower(-pivotIntakeSpeed);
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

        fL = frontLeft.getPower();
        fR = frontRight.getPower();
        bL = backLeft.getPower();
        bR = backRight.getPower();
        L = lift.getPower();
        lP = leftPinch.getPower();
        lPI = leftPivotIntake.getPower();

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
                telemetry.addData("rightSide: ", "Backwards: " + fR);
                break;
            case 0:
                telemetry.addData("rightSide: ", "Neutral");
                break;
            case 1:
                telemetry.addData("rightSide: ", "Forwards: " + fR);
                break;
        }
        switch (liftStatus) {
            case -1:
                telemetry.addData("lift: ", "Down: " + L);
                break;
            case 0:
                telemetry.addData("lift: ", "Neutral");
                break;
            case 1:
                telemetry.addData("lift: ", "Up: " + L);
        }
        switch (intakeStatus) {
            case -1:
                telemetry.addData("intake: ", "Open: " + lP);
                break;
            case 0:
                telemetry.addData("intake: ", "Neutral");
                break;
            case 1:
                telemetry.addData("intake: ", "Close: " + lP);
                break;
        }
        switch (pivotStatus) {
            case -1:
                telemetry.addData("pivot: ", "Lowered: " + lPI);
                break;
            case 0:
                telemetry.addData("pivot: ", "Neutral");
                break;
            case 1:
                telemetry.addData("pivot: ", "Raised: " + lPI);
        }
        switch (strafeStatus) {
            case -1:
                telemetry.addData("strafe: ", "Left: " + bL);
                break;
            case 0:
                telemetry.addData("strafe: ",  "Neutral");
                break;
            case 1:
                telemetry.addData("strafe: ", "Right: " + bR);
        }
        telemetry.addData("baseSpeed: ", baseSpeed);
        telemetry.addData("pivotSpeed: ", pivotIntakeSpeed);
        telemetry.addData("strafeSpeed: ", strafeSpeed);
        telemetry.addData("liftSpeed: ", liftSpeed);
        telemetry.addData("leftSideSpeed: ", leftSideSpeed);
        telemetry.addData("rightSideSpeed: ", rightSideSpeed);
        telemetry.addData("controller1: ", controller1);
        telemetry.addData("controller2: ", controller2);
        telemetry.addData("TimeElapsed: ", runtime);
        updateTelemetry(telemetry);
        telemetry.update();
    }
}
