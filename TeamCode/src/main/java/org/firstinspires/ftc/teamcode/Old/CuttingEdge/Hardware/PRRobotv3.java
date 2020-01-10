package org.firstinspires.ftc.teamcode.Old.CuttingEdge.Hardware;

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

public class PRRobotv3 {

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

    public Status leftSideStatus = Status.NEUTRAL;
    public Status rightSideStatus = Status.NEUTRAL;
    public Status liftStatus = Status.NEUTRAL;
    public Status intakeStatus = Status.NEUTRAL;
    public Status pivotStatus = Status.NEUTRAL;
    public Status strafeStatus = Status.NEUTRAL;

    // objects

    public ElapsedTime runtime = new ElapsedTime();

    public HardwareMap hwMap;

    public Telemetry telemetry;

    public Telemetry telem;

    // constructor

    public PRRobotv3(double baseSpeed, double pivotIntakeSpeed, double strafeSpeed, double liftSpeed, double open, double close) {
        this.baseSpeed = baseSpeed;
        this.pivotIntakeSpeed = pivotIntakeSpeed;
        this.strafeSpeed = strafeSpeed;
        this.liftSpeed = liftSpeed;
        this.open = open;
        this.close = close;
    }

    public PRRobotv3(String mode) {
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

    public PRRobotv3() {
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

    public boolean getController1(){
        return (leftStick1 != 0) || (rightStick1 != 0) || dpadLeft1 || dpadRight1 || dpadUp1 || dpadDown1;
    }

    public boolean getController2(){
        return dpadUp2 || dpadDown2 || a2 || b2 || rightBumper2 || leftBumper2 || x2 || y2;
    }

    // autonomous functions

    public void move(double leftPower, double rightPower, Status status) {
        switch(status) {
            case FORWARDS:
                frontLeft.setPower(leftPower);
                frontRight.setPower(rightPower);
                backLeft.setPower(leftPower);
                backRight.setPower(rightPower);
                telem.addData("move()", "FORWARDS");
                break;
            case BACKWARDS:
                frontLeft.setPower(-leftPower);
                frontRight.setPower(-rightPower);
                backLeft.setPower(-leftPower);
                backRight.setPower(-rightPower);
                telem.addData("move()", "BACKWARDS");
                break;
            case NEUTRAL:
                stop();
                telem.addData("move()", "NEUTRAL");
        }
    }

    public void stop(){
        frontLeft.setPower(STOP);
        frontRight.setPower(STOP);
        backLeft.setPower(STOP);
        backRight.setPower(STOP);
        telem.addData("stop()", "true");
    }

    public void pivotTurn(double leftPower, double rightPower, Status status){
        switch (status) {
            case LEFT:
                frontRight.setPower(-rightPower);
                frontLeft.setPower (leftPower);
                backRight.setPower(-rightPower);
                backLeft.setPower(rightPower);
                telem.addData("pivotTurn()", "LEFT");
                break;
            case RIGHT:
                frontRight.setPower(rightPower);
                frontLeft.setPower (-leftPower);
                backRight.setPower(rightPower);
                backLeft.setPower(-rightPower);
                telem.addData("pivotTurn()", "RIGHT");
        }
    }

    public void pointTurn(double power, Status status){
        switch (status) {
            case RIGHT:
                frontRight.setPower(power);
                backRight.setPower(power);
                telem.addData("pointTurn()", "RIGHT");
                break;
            case LEFT:
                frontLeft.setPower(power);
                backLeft.setPower(power);
                telem.addData("pointTurn()", "LEFT");
        }
    }

    public void pinch(Status status){
        switch(status) {
            case OPEN:
                leftPinch.setPower(pinchSpeed);
                rightPinch.setPower(pinchSpeed);
                telem.addData("pinch()", "OPEN");
                break;
            case CLOSE:
                leftPinch.setPower(pinchSpeed);
                rightPinch.setPower(pinchSpeed);
                telem.addData("pinch()", "CLOSE");
                break;
            case NEUTRAL:
                leftPinch.setPower(STOP);
                rightPinch.setPower(STOP);
                telem.addData("pinch()", "NEUTRAL");
        }
    }

    public void pivotIntake(Status status) {
        switch (status) {
            case DOWN:
                leftPivot.setPower(.8);
                rightPivot.setPower(-.8);
                telem.addData("pivotIntake()", "DOWN");
                break;
            case UP:
                leftPivot.setPower(-.2);
                rightPivot.setPower(.2);
                telem.addData("pivotIntake()", "UP");
                break;
            case NEUTRAL:
                leftPivot.setPower(STOP);
                rightPivot.setPower(STOP);
                telem.addData("pivotIntake()", "NEUTRAL");
                break;
        }
    }

    public void speedChange(double increase, int sign){
        baseSpeed += increase * sign;
        strafeSpeed += increase * sign;
    }

    public void setTelemetry(Telemetry telemetry) {
        this.telem = telemetry;
    }

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
            case FORWARDS:
                telem.addData("leftSide", "Forwards: " + fL);
                break;
            case NEUTRAL:
                telem.addData("leftSide", "Neutral" + "0");
                break;
            case BACKWARDS:
                telem.addData("leftSide", "Backwards: " + fL);
                break;
        }
        switch (rightSideStatus) {
            case BACKWARDS:
                telem.addData("rightSide", "Backwards: " + fR);
                break;
            case NEUTRAL:
                telem.addData("rightSide", "Neutral");
                break;
            case FORWARDS:
                telem.addData("rightSide", "Forwards: " + fR);
                break;
        }
        switch (liftStatus) {
            case DOWN:
                telem.addData("lift", "Down: " + L);
                break;
            case NEUTRAL:
                telem.addData("lift", "Neutral");
                break;
            case UP:
                telem.addData("lift", "Up: " + L);
        }
        switch (intakeStatus) {
            case OPEN:
                telem.addData("intake", "Open: " + lP);
                break;
            case NEUTRAL:
                telem.addData("intake", "Neutral");
                break;
            case CLOSE:
                telem.addData("intake", "Close: " + lP);
                break;
        }
        switch (pivotStatus) {
            case DOWN:
                telem.addData("pivot", "Lowered: " + lPv);
                break;
            case NEUTRAL:
                telem.addData("pivot", "Neutral");
                break;
            case UP:
                telem.addData("pivot", "Raised: " + lPv);
        }
        switch (strafeStatus) {
            case LEFT:
                telem.addData("strafe", "Left: " + bL);
                break;
            case NEUTRAL:
                telem.addData("strafe",  "Neutral");
                break;
            case RIGHT:
                telem.addData("strafe", "Right: " + bR);
        }
        telem.addData("baseSpeed", baseSpeed);
        telem.addData("pivotSpeed", pivotIntakeSpeed);
        telem.addData("strafeSpeed", strafeSpeed);
        telem.addData("liftSpeed", liftSpeed);
        telem.addData("leftSideSpeed", leftSideSpeed);
        telem.addData("rightSideSpeed", rightSideSpeed);
        telem.addData("controller1", controller1);
        telem.addData("controller2", controller2);
        telem.addData("TimeElapsed", runtime);
        telem.update();

    }
}