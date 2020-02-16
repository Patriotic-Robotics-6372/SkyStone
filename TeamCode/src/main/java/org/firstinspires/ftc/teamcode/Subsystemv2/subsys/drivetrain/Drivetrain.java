package org.firstinspires.ftc.teamcode.Subsystemv2.subsys.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;

/**
 * Date: 2/11/20
 * Author: Jacob Marinas
 * The drivetrain subsystem.
 */
public class Drivetrain implements Constants {

    private final DcMotor frontLeft, frontRight, backLeft, backRight;
    private double power, currentSpeed, goalSpeed, speedRate, speedPercentage;
    private Status baseStatus = Status.NEUTRAL;
    private int fLTickGoal, fRTickGoal, bLTickGoal, bRTickGoal;

    public Drivetrain(DcMotor fL, DcMotor fR, DcMotor bL, DcMotor bR) {
        this.frontLeft = fL;
        this.frontRight = fR;
        this.backLeft = bL;
        this.backRight = bR;

        init();
    }

    private void init() {
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);

        power = STOP;
    }

    public void useEncoders(boolean use) {
        if (use) {
            setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else {
            setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void useBrake(boolean use) {
        if (use) {
            setZPB(DcMotor.ZeroPowerBehavior.BRAKE);
        } else {
            setZPB(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }

    public void setRunMode(DcMotor.RunMode runMode) {
        frontRight.setMode(runMode);
        frontLeft.setMode(runMode);
        backRight.setMode(runMode);
        backLeft.setMode(runMode);
    }

    public void setZPB(DcMotor.ZeroPowerBehavior zpb) {
        frontLeft.setZeroPowerBehavior(zpb);
        frontRight.setZeroPowerBehavior(zpb);
        backLeft.setZeroPowerBehavior(zpb);
        backRight.setZeroPowerBehavior(zpb);
    }

    public void throttleOn() {
        speedPercentage = THROTTLE_ON;
    }

    public void throttleOff() {
        speedPercentage = THROTTLE_OFF;
    }

    public void setSpeedPercentage(double sP) {
        speedPercentage = sP;
    }

    public double getSpeedPercentage() {
        return speedPercentage;
    }

    public void setMaxPower(double power) {
        this.power = power;
    }

    public void setGoalSpeed(double goalSpeed) {
        this.goalSpeed = goalSpeed;
    }

    public void setBase(double fL, double fR, double bL, double bR){
        setLeftSide(fL * speedPercentage, bL * speedPercentage);
        setRightSide(fR * speedPercentage, bR * speedPercentage);
    }

    public void setBase(double power) {
        setLeftSide(power * speedPercentage, power * speedPercentage);
        setRightSide(power * speedPercentage, power * speedPercentage);
    }

    public void setLeftSide(double fL, double bL) {
        frontLeft.setPower(fL * speedPercentage);
        backLeft.setPower(bL * speedPercentage);
    }

    public void setLeftSide(double power) {
        frontLeft.setPower(power * speedPercentage);
        backLeft.setPower(power * speedPercentage);
    }

    public void setRightSide(double fR, double bR) {
        frontRight.setPower(fR * speedPercentage);
        backRight.setPower(bR * speedPercentage);
    }

    public void setRightSide(double power) {
        frontRight.setPower(power * speedPercentage);
        backRight.setPower(power * speedPercentage);
    }

    public void forward() {
        setBase(-power, -power, -power, -power);
    }

    public void backward() {
        setBase(power, power, power, power);
    }

    public void strafeLeft() {
        setBase(power, -power, -power, power);
    }

    public void strafeRight() {
        setBase(-power, power, power, -power);
    }

    public void setStatus(Status status) {
        this.baseStatus = status;
    }

    public void pivotTurnLeft(double speed) {
        setLeftSide(speed);
        setRightSide(-speed);
        setStatus(Status.LEFT);
    }

    public void pivotTurnRight(double speed) {
        setLeftSide(-speed);
        setRightSide(speed);
        setStatus(Status.RIGHT);
    }

    public void stop() {
        setBase(STOP);
    }

    public void stopLeftSide() {
        setLeftSide(STOP);
    }

    public void stopRightSide() {
        setRightSide(STOP);
    }

    public void drive(double fLInches, double fRInches, double bLInches, double bRInches,
                      double fLPower, double fRPower, double bLPower, double bRPower) {
        fLTickGoal = (int) (TICKS_PER_IN * fLInches);
        fRTickGoal = (int) (TICKS_PER_IN * fRInches);
        bLTickGoal = (int) (TICKS_PER_IN * bLInches);
        bRTickGoal = (int) (TICKS_PER_IN * bRInches);
        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setTargetPositions(fLTickGoal, fRTickGoal, bLTickGoal, bRTickGoal);
        setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
        setBase(fLPower, fRPower, bLPower, bRPower);
        while (allBusy()) {

        }
        stop();
        setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void forward(double inches) {
        drive(-inches, -inches, -inches, -inches, power, power, power, power);
        setStatus(Status.FORWARDS);
    }

    public void backward(double inches) {
        drive(inches, inches, inches, inches, power, power, power, power);
        setStatus(Status.BACKWARDS);
    }

    public void strafe(double leftInches, double rightInches) {
        drive(leftInches, -rightInches, -leftInches, rightInches, power, power, power, power);
    }

    public void strafeLeft(double inches) {
        strafe(inches, inches);
        setStatus(Status.LEFT);
    }

    public void strafeRight(double inches) {
        strafe(-inches, -inches);
        setStatus(Status.RIGHT);
    }

    private void setTargetPositions(int fL, int fR, int bL, int bR){
        frontRight.setTargetPosition(fR);
        frontLeft.setTargetPosition(fL);
        backRight.setTargetPosition(bR);
        backLeft.setTargetPosition(bL);
    }

    private boolean anyBusy() {
        return frontLeft.isBusy() || frontRight.isBusy() || backLeft.isBusy() || backRight.isBusy();
    }

    private boolean allBusy() {
        return frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy();
    }

    public double getMaxPower() {
        return power;
    }

    public double getGoalSpeed() {
        return goalSpeed;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public int getfLEncoder() {
        return frontLeft.getCurrentPosition();
    }

    public int getfREncoder() {
        return frontRight.getCurrentPosition();
    }

    public int getbLEncoder() {
        return backLeft.getCurrentPosition();
    }

    public int getbREncoder() {
        return backRight.getCurrentPosition();
    }

    public int getfLTickGoal() {
        return fLTickGoal;
    }

    public int getfRTickGoal() {
        return fRTickGoal;
    }

    public int getbLTickGoal() {
        return bLTickGoal;
    }

    public int getbRTickGoal() {
        return bRTickGoal;
    }

    public DcMotor getFrontLeft() {
        return frontLeft;
    }

    public DcMotor getFrontRight() {
        return frontRight;
    }

    public DcMotor getBackLeft() {
        return backLeft;
    }

    public DcMotor getBackRight() {
        return backRight;
    }

    public Status getStatus() {
        return baseStatus;
    }
}
