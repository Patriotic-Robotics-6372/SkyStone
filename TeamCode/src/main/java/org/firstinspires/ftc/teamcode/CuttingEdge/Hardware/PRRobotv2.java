package org.firstinspires.ftc.teamcode.CuttingEdge.Hardware;

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

public class PRRobotv2 {

    // defining motors and servos

    public DcMotor backLeft, backRight, frontLeft, frontRight,
                lift,
                leftPivot, rightPivot;

    public CRServo leftPinch, rightPinch;

    // encoders

    public int STOP, leftTickGoal, rightTickGoal, tickGoal;

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

    public enum Status {
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

    public PRRobotv2(double baseSpeed, double pivotIntakeSpeed, double strafeSpeed, double liftSpeed, double open, double close) {
        this.baseSpeed = baseSpeed;
        this.pivotIntakeSpeed = pivotIntakeSpeed;
        this.strafeSpeed = strafeSpeed;
        this.liftSpeed = liftSpeed;
        this.open = open;
        this.close = close;
    }

    public PRRobotv2(String mode) {
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

    public PRRobotv2() {
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

    public void turnPerpendicular(Status status) {
        switch (status) {
            case LEFT:
                //driveDistance(3, .3, 1, Status.FORWARDS);
                break;
            case RIGHT:
                //driveDistance(3, 1, .3, Status.FORWARDS);
        }
    }

    public void encoder(double inches, double rightPower, double leftPower, String motor) {
        tickGoal = (int) (TICKS_PER_IN * inches);
        switch (motor) {
            case "fR":
                frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                //encoderTelemetry(motor, false);
                //sleep(wait);
                frontRight.setTargetPosition(tickGoal);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //encoderTelemetry(motor, false);
                //sleep(wait);
                break;
            case "fL":
                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                //encoderTelemetry(motor, false);
                //sleep(wait);
                frontLeft.setTargetPosition(tickGoal);
                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //encoderTelemetry(motor, false);
                //sleep(wait);
                break;
            case "bR":
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                //encoderTelemetry(motor, false);
                //sleep(wait);
                backRight.setTargetPosition(tickGoal);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //encoderTelemetry(motor, false);
                //sleep(wait);
                break;
            case "bL":
                backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                //encoderTelemetry(motor, false);
                //sleep(wait);
                backLeft.setTargetPosition(tickGoal);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //encoderTelemetry(motor, false);
                //sleep(wait);
                break;
            case "all":
                frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                //encoderTelemetry(motor, true);
                //sleep(wait);
                frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                //encoderTelemetry(motor, true);
                //sleep(wait);
                frontRight.setTargetPosition(tickGoal);
                frontLeft.setTargetPosition(tickGoal);
                backRight.setTargetPosition(tickGoal);
                backLeft.setTargetPosition(tickGoal);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //encoderTelemetry(motor, true);
                //sleep(wait);
                break;
            case "pivot":
                leftPivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                rightPivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                //encoderTelemetry(motor, true);
                //sleep(wait);
                leftPivot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                rightPivot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                //encoderTelemetry(motor, true);
                //sleep(wait);
                leftPivot.setTargetPosition(tickGoal);
                rightPivot.setTargetPosition(tickGoal);
                leftPivot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightPivot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //encoderTelemetry(motor, true);
                //sleep(wait);
        }
        //encoderTelemetry(motor, true);
        //sleep(wait);
        while (anyBusy()) {
            switch (motor) {
                case "pivot":
                    leftPivot.setPower(leftPower);
                    rightPivot.setPower(rightPower);
                    telemetry.addData("pivots", "yes");
                    break;
                default:
                    frontRight.setPower(rightPower);
                    frontLeft.setPower(leftPower);
                    backRight.setPower(rightPower);
                    backLeft.setPower(leftPower);
            }
            //encoderTelemetry(motor, true);
        }
        stop();
        leftPivot.setPower(0);
        rightPivot.setPower(0);
        //encoderTelemetry(motor, true);
        //sleep(wait);
        //encoderTelemetry(motor, true);
        //sleep(wait);
    }

    public boolean anyBusy(){
        if (frontRight.isBusy() || frontLeft.isBusy() || backRight.isBusy() || backLeft.isBusy() || leftPivot.isBusy() || rightPivot.isBusy()) {
            return true;
        } else {
            return false;
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

    public void encoderTelemetry(Telemetry telemetry, String motor, boolean toggle) {
        if (toggle) {
            switch (motor) {
                case "fR":
                    telemetry.addData("fR getCurPos: ", frontRight.getCurrentPosition());
                    telemetry.addData("fR getTarPos: ", frontRight.getTargetPosition());
                    telemetry.addData("fR isBusy: ", frontRight.isBusy());
                case "fL":
                    telemetry.addData("fL getCurPos: ", frontLeft.getCurrentPosition());
                    telemetry.addData("fL getTarPos: ", frontLeft.getTargetPosition());
                    telemetry.addData("fL isBusy: ", frontLeft.isBusy());
                case "bR":
                    telemetry.addData("bR getCurPos: ", backRight.getCurrentPosition());
                    telemetry.addData("bR getTarPos: ", backRight.getTargetPosition());
                    telemetry.addData("bR isBusy: ", backRight.isBusy());
                case "bL":
                    telemetry.addData("bL getCurPos: ", backLeft.getCurrentPosition());
                    telemetry.addData("bL getTarPos: ", backLeft.getTargetPosition());
                    telemetry.addData("bL isBusy: ", backLeft.isBusy());
                case "all":
                    telemetry.addData("fR getCurPos: ", frontRight.getCurrentPosition());
                    telemetry.addData("fL getCurPos: ", frontLeft.getCurrentPosition());
                    telemetry.addData("bR getCurPos: ", backRight.getCurrentPosition());
                    telemetry.addData("bL getCurPos: ", backLeft.getCurrentPosition());
                    telemetry.addData("fR getTarPos: ", frontRight.getTargetPosition());
                    telemetry.addData("fL getTarPos: ", frontLeft.getTargetPosition());
                    telemetry.addData("bR getTarPos: ", backRight.getTargetPosition());
                    telemetry.addData("bL getTarPos: ", backLeft.getTargetPosition());
                    telemetry.addData("fR isBusy: ", frontRight.isBusy());
                    telemetry.addData("fL isBusy: ", frontLeft.isBusy());
                    telemetry.addData("bR isBusy: ", backRight.isBusy());
                    telemetry.addData("bL isBusy: ", backLeft.isBusy());
                case "pivot":
                    telemetry.addData("lP getCurPos: ", leftPivot.getCurrentPosition());
                    telemetry.addData("rP getCurPos: ", rightPivot.getCurrentPosition());
                    telemetry.addData("lP getTarPos: ", leftPivot.getTargetPosition());
                    telemetry.addData("rP getTarPos: ", rightPivot.getTargetPosition());
                    telemetry.addData("lP isBusy: ", leftPivot.isBusy());
                    telemetry.addData("rP isBusy: ", rightPivot.isBusy());
            }
        }
        /*
        telemetry.addData("fR isBusy: ", prBot.frontRight.isBusy());
        telemetry.addData("fL isBusy: ", prBot.frontLeft.isBusy());
        telemetry.addData("bR isBusy: ", prBot.backRight.isBusy());
        telemetry.addData("bL isBusy: ", prBot.backLeft.isBusy());
         */
        /*
        telemetry.addData("fR getCurPos: ", prBot.frontRight.getCurrentPosition());
        telemetry.addData("fL getCurPos: ", prBot.frontLeft.getCurrentPosition());
        telemetry.addData("bR getCurPos: ", prBot.backRight.getCurrentPosition());
        telemetry.addData("bL getCurPos: ", prBot.backLeft.getCurrentPosition());
         */
        telemetry.addData("fR getPow: ", frontRight.getPower());
        telemetry.addData("fL getPow: ", frontLeft.getPower());
        telemetry.addData("bR getPow: ", backRight.getPower());
        telemetry.addData("bL getPow: ", backLeft.getPower());
        telemetry.addData("lP getPow: ", leftPivot.getPower());
        telemetry.addData("rP getPow: ", rightPivot.getPower());
        telemetry.addData("fR getMode: ", frontRight.getMode());
        telemetry.addData("fL getMode: ", frontLeft.getMode());
        telemetry.addData("bR getMode: ", backRight.getMode());
        telemetry.addData("bL getMode: ", backLeft.getMode());
        telemetry.addData("lP getMode: ", leftPivot.getMode());
        telemetry.addData("rP getMode: ", rightPivot.getMode());
        telemetry.update();
    }
}