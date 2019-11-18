package org.firstinspires.ftc.teamcode.CuttingEdge.Hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/*
    author: Jacob Marinas
    date: 11/18/19
    desc: defines robot for use in autonomous and teleop; included is robotStatus() made by me
          needs parameters for baseSpeed, pivotIntakeSpeed, strafeSpeed, liftSpeed where double is between 0 and 1 (max speed)
          open and close, where int is between 180 and 0
 */

public class PRRobot {

    // defining motors and servos

    public DcMotor backLeft, backRight, frontLeft, frontRight,
                lift,
                leftPivot, rightPivot;

    public CRServo leftPinch, rightPinch;

    // status variables; tells state of component

    public int leftSideStatus, rightSideStatus,
            liftStatus,
            intakeStatus,
            pivotStatus,
            strafeStatus;

    // speed modifiers

    public double baseSpeed, pivotIntakeSpeed, strafeSpeed, liftSpeed,
            leftSideSpeed, rightSideSpeed,
            open, close,

    // easier naming conventions / double values

            fL, fR, bL, bR, L, lP, lPv,
            leftStick1, rightStick1;

    // easier naming conventions / truth values

    public boolean controller1, dpadLeft1, dpadRight1, dpadUp1, dpadDown1,
            controller2, dpadUp2, dpadDown2, rightBumper2, leftBumper2, a2, b2, x2, y2;

    // time

    public ElapsedTime runtime = new ElapsedTime();

    // constructor

    public PRRobot(double baseSpeed, double pivotIntakeSpeed, double strafeSpeed, double liftSpeed, double open, double close) {
        this.baseSpeed = baseSpeed;
        this.pivotIntakeSpeed = pivotIntakeSpeed;
        this.strafeSpeed = strafeSpeed;
        this.liftSpeed = liftSpeed;
        this.open = open;
        this.close = close;
    }

    public static Telemetry telemetry;

    public void init(HardwareMap hwMap){

        // init hardwaremap variables to phone

        backLeft = hwMap.dcMotor.get("backLeft");
        backRight = hwMap.dcMotor.get("backRight");
        frontLeft = hwMap.dcMotor.get("frontLeft");
        frontRight = hwMap.dcMotor.get("frontRight");
        lift = hwMap.dcMotor.get("leftLift");
        leftPivot = hwMap.dcMotor.get("leftPivot");
        rightPivot = hwMap.dcMotor.get("rightPivot");

        leftPinch = hwMap.crservo.get("leftPinch");
        rightPinch = hwMap.crservo.get("rightPinch");

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rightPivot.setDirection(DcMotorSimple.Direction.REVERSE);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

    }

    public void start() {
        runtime.reset();
    }

    // autonomous functions

    public void goForward (double leftPower, double rightPower){
        frontRight.setPower(rightPower);
        frontLeft.setPower(leftPower);
        backRight.setPower(rightPower);
        backLeft.setPower(leftPower);
    }

    public void goBackward (double leftPower, double rightPower){
        frontRight.setPower(rightPower);
        frontLeft.setPower(leftPower);
        backRight.setPower(rightPower);
        backLeft.setPower(leftPower);
    }

    public void pivotRightTurn (double leftPower, double rightPower){
        frontRight.setPower(-rightPower);
        frontLeft.setPower (leftPower);
        backRight.setPower(-rightPower);
        backLeft.setPower(rightPower);
    }

    public void pivotLeftTurn (double leftPower, double rightPower){
        frontRight.setPower(rightPower);
        frontLeft.setPower(-leftPower);
        backRight.setPower(rightPower);
        backLeft.setPower(-leftPower);
    }

    public void pointRightTurn (double leftPower){
        frontLeft.setPower(leftPower);
        backLeft.setPower(leftPower);
    }

    public void pointLeftTurn (double rightPower){
        frontRight.setPower(rightPower);
        backRight.setPower(rightPower);
    }

    public void noBrake(){
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void brake(){
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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

        // used to check how the robot is doing

        fL = frontLeft.getPower();
        fR = frontRight.getPower();
        bL = backLeft.getPower();
        bR = backRight.getPower();
        L = lift.getPower();
        lP = leftPinch.getPower();
        lPv = leftPivot.getPower();

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
                telemetry.addData("pivot: ", "Lowered: " + lPv);
                break;
            case 0:
                telemetry.addData("pivot: ", "Neutral");
                break;
            case 1:
                telemetry.addData("pivot: ", "Raised: " + lPv);
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
        telemetry.update();
    }
}
