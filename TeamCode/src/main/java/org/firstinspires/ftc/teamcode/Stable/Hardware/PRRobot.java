package org.firstinspires.ftc.teamcode.Stable.Hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/*
    author: Jacob Marinas
    date: 11/18/19 (last updated: 12/11/19)
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

    // encoders

    public int STOP, tickGoal, leftTickGoal, rightTickGoal;

    // speed modifiers

    public double baseSpeed, pivotIntakeSpeed, strafeSpeed, liftSpeed,
            leftSideSpeed, rightSideSpeed,
            pinchSpeed, 
            open, close,

    // easier naming conventions / double values

            fL, fR, bL, bR, L, lP, lPv,
            leftStick1, rightStick1,

    // conversion factor between encoder rotations in the motors : 1 inch

            TICKS_PER_IN, STRAFE_MOD;

    // truth variables

    public boolean controller1, dpadLeft1, dpadRight1, dpadUp1, dpadDown1,
            controller2, dpadUp2, dpadDown2, rightBumper2, leftBumper2, a2, b2, x2, y2;

    // enums

    public static enum Status {
        NEUTRAL, BACKWARDS, FORWARDS, DOWN, UP, OPEN, CLOSE, LEFT, RIGHT;
    }

    // Status objects

    public Status leftSideStatus, rightSideStatus,
            liftStatus,
            intakeStatus,
            pivotStatus,
            strafeStatus;

    // objects

    public ElapsedTime runtime = new ElapsedTime();

    public HardwareMap hwMap;

    public Telemetry telemetry;

    // constructor

    public PRRobot(double baseSpeed, double pivotIntakeSpeed, double strafeSpeed, double liftSpeed, double open, double close) {
        this.baseSpeed = baseSpeed;
        this.pivotIntakeSpeed = pivotIntakeSpeed;
        this.strafeSpeed = strafeSpeed;
        this.liftSpeed = liftSpeed;
        this.open = open;
        this.close = close;
    }

    public PRRobot(String mode) {
        switch (mode) {
            case "stable":
                this.baseSpeed = 1;
                this.pivotIntakeSpeed = .8;
                this.strafeSpeed = 1;
                this.liftSpeed = .8;
                this.pinchSpeed = .4;
                this.open = 1;
                this.close = -1;
                break;
            case "fast":
                this.baseSpeed = 1;
                this.pivotIntakeSpeed = .8;
                this.strafeSpeed = 1;
                this.liftSpeed = 1;
                this.pinchSpeed = .8;
                this.open = 1;
                this.close = -1;
                break;
            case "slow":
                this.baseSpeed = .5;
                this.pivotIntakeSpeed = .8;
                this.strafeSpeed = .5;
                this.liftSpeed = .5;
                this.pinchSpeed = .2;
                this.open = .5;
                this.close = .5;
                break;
        }
    }

    public PRRobot() {
        this.baseSpeed = 1;
        this.pivotIntakeSpeed = .8;
        this.strafeSpeed = 1;
        this.liftSpeed = .8;
        this.pinchSpeed = .4;
        this.open = 1;
        this.close = -1;
    }

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
        //leftPinch.setDirection(Servo.Direction.REVERSE);

        double TICKS_PER_IN = 1120/4*Math.PI;

        STOP = 0;
        stop();
    }

    public void start() {
        runtime.reset();
    }

    public void useBrake(boolean use){
        if (use) {
            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } else {
            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }

    public void useEnc(boolean use){
        if (use) {
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else {
            frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    // autonomous functions

    public void move(double leftPower, double rightPower, Status status) {
        switch(status) {
            case FORWARDS:
                frontLeft.setPower(leftPower);
                frontRight.setPower(rightPower);
                backLeft.setPower(leftPower);
                backRight.setPower(rightPower);
                break;
            case BACKWARDS:
                frontLeft.setPower(-leftPower);
                frontRight.setPower(-rightPower);
                backLeft.setPower(-leftPower);
                backRight.setPower(-rightPower);
                break;
            case NEUTRAL:
                stop();
        }
    }

    public void stop(){
        frontLeft.setPower(STOP);
        frontRight.setPower(STOP);
        backLeft.setPower(STOP);
        backRight.setPower(STOP);
    }

    public void pivotTurn(double leftPower, double rightPower, Status status){
        switch (status) {
            case LEFT:
                frontRight.setPower(-rightPower);
                frontLeft.setPower (leftPower);
                backRight.setPower(-rightPower);
                backLeft.setPower(rightPower);
                break;
            case RIGHT:
                frontRight.setPower(rightPower);
                frontLeft.setPower (-leftPower);
                backRight.setPower(rightPower);
                backLeft.setPower(-rightPower);
        }
    }

    public void pointTurn(double power, Status status){
        switch (status) {
            case RIGHT:
                frontRight.setPower(power);
                backRight.setPower(power);
                break;
            case LEFT:
                frontLeft.setPower(power);
                backLeft.setPower(power);
        }
    }

    public void pinch(Status status){
        switch(status) {
            case OPEN:
                leftPinch.setPower(pinchSpeed);
                rightPinch.setPower(pinchSpeed);
                break;
            case CLOSE:
                leftPinch.setPower(pinchSpeed);
                rightPinch.setPower(pinchSpeed);
                break;
            case NEUTRAL:
                leftPinch.setPower(STOP);
                rightPinch.setPower(STOP);
        }
    }

    public void pivotIntake(Status status) {
        switch (status) {
            case DOWN:
                leftPivot.setPower(.8);
                rightPivot.setPower(-.8);
                break;
            case UP:
                leftPivot.setPower(-.2);
                rightPivot.setPower(.2);
            case NEUTRAL:
                leftPivot.setPower(STOP);
                rightPivot.setPower(STOP);
        }
    }

    public void speedChange(double increase, int sign){
        baseSpeed += increase * sign;
        strafeSpeed += increase * sign;
    }

    public void robotStatus(Telemetry telemetry) {

        // used to check how the robot is doing

        fL = frontLeft.getPower();
        fR = frontRight.getPower();
        bL = backLeft.getPower();
        bR = backRight.getPower();
        L = lift.getPower();
        lP = leftPinch.getPower();
        lPv = leftPivot.getPower();

        switch (leftSideStatus) {
            case FORWARDS:
                telemetry.addData("leftSide: ", "Forwards: " + fL);
                break;
            case NEUTRAL:
                telemetry.addData("leftSide: ", "Neutral" + "0");
                break;
            case BACKWARDS:
                telemetry.addData("leftSide: ", "Backwards: " + fL);
                break;
        }
        switch (rightSideStatus) {
            case BACKWARDS:
                telemetry.addData("rightSide: ", "Backwards: " + fR);
                break;
            case NEUTRAL:
                telemetry.addData("rightSide: ", "Neutral");
                break;
            case FORWARDS:
                telemetry.addData("rightSide: ", "Forwards: " + fR);
                break;
        }
        switch (liftStatus) {
            case DOWN:
                telemetry.addData("lift: ", "Down: " + L);
                break;
            case NEUTRAL:
                telemetry.addData("lift: ", "Neutral");
                break;
            case UP:
                telemetry.addData("lift: ", "Up: " + L);
        }
        switch (intakeStatus) {
            case OPEN:
                telemetry.addData("intake: ", "Open: " + lP);
                break;
            case NEUTRAL:
                telemetry.addData("intake: ", "Neutral");
                break;
            case CLOSE:
                telemetry.addData("intake: ", "Close: " + lP);
                break;
        }
        switch (pivotStatus) {
            case DOWN:
                telemetry.addData("pivot: ", "Lowered: " + lPv);
                break;
            case NEUTRAL:
                telemetry.addData("pivot: ", "Neutral");
                break;
            case UP:
                telemetry.addData("pivot: ", "Raised: " + lPv);
        }
        switch (strafeStatus) {
            case LEFT:
                telemetry.addData("strafe: ", "Left: " + bL);
                break;
            case NEUTRAL:
                telemetry.addData("strafe: ",  "Neutral");
                break;
            case RIGHT:
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