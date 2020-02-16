package org.firstinspires.ftc.teamcode.Subsystemv2.subsys.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;

/**
 * Date: 2/11/20
 * Author: Jacob Marinas
 * The drivetrain subsystem
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

    /**
     * Sets the direction of the motors and sets their power to 0
     */
    private void init() {
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);

        power = STOP;
    }

    /**
     * @param use RUN_USING_ENCODER
     */
    public void useEncoders(boolean use) {
        if (use) {
            setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else {
            setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    /**
     * @param use ZeroPowerBehavior.BRAKE
     */
    public void useBrake(boolean use) {
        if (use) {
            setZPB(DcMotor.ZeroPowerBehavior.BRAKE);
        } else {
            setZPB(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }

    /**
     * @param runMode set to all motors
     */
    public void setRunMode(DcMotor.RunMode runMode) {
        frontRight.setMode(runMode);
        frontLeft.setMode(runMode);
        backRight.setMode(runMode);
        backLeft.setMode(runMode);
    }

    /**
     * @param zpb set to all motors
     */
    public void setZPB(DcMotor.ZeroPowerBehavior zpb) {
        frontLeft.setZeroPowerBehavior(zpb);
        frontRight.setZeroPowerBehavior(zpb);
        backLeft.setZeroPowerBehavior(zpb);
        backRight.setZeroPowerBehavior(zpb);
    }

    public void setRamp(boolean has) {

    }

    /**
     * Turns on throttle variable
     */
    public void throttleOn() {
        speedPercentage = THROTTLE_ON;
    }

    /**
     * Turns off throttle variable
     */
    public void throttleOff() {
        speedPercentage = THROTTLE_OFF;
    }

    /**
     * @param sP out of 100% of the scale of the max power
     */
    public void setSpeedPercentage(double sP) {
        speedPercentage = sP;
    }

    /**
     * @return the speedPercentage
     */
    public double getSpeedPercentage() {
        return speedPercentage;
    }

    /**
     * @param power out of 1 of the max power
     */
    public void setMaxPower(double power) {
        this.power = power;
    }

    /**
     * @param goalSpeed
     */
    public void setGoalSpeed(double goalSpeed) {
        this.goalSpeed = goalSpeed;
    }

    /**
     * @param fL power
     * @param fR power
     * @param bL power
     * @param bR power
     */
    public void setBase(double fL, double fR, double bL, double bR){
        setLeftSide(fL * speedPercentage, bL * speedPercentage);
        setRightSide(fR * speedPercentage, bR * speedPercentage);
    }

    /**
     * @param power to all motors
     */
    public void setBase(double power) {
        setLeftSide(power * speedPercentage, power * speedPercentage);
        setRightSide(power * speedPercentage, power * speedPercentage);
    }

    /**
     * @param fL power
     * @param bL power
     */
    public void setLeftSide(double fL, double bL) {
        frontLeft.setPower(fL * speedPercentage);
        backLeft.setPower(bL * speedPercentage);
    }

    /**
     * @param power to left side
     */
    public void setLeftSide(double power) {
        frontLeft.setPower(power * speedPercentage);
        backLeft.setPower(power * speedPercentage);
    }

    /**
     * @param fR power
     * @param bR power
     */
    public void setRightSide(double fR, double bR) {
        frontRight.setPower(fR * speedPercentage);
        backRight.setPower(bR * speedPercentage);
    }

    /**
     * @param power to right side
     */
    public void setRightSide(double power) {
        frontRight.setPower(power * speedPercentage);
        backRight.setPower(power * speedPercentage);
    }

    /**
     * Basic forward movement by power varaible
     */
    public void forward() {
        setBase(-power, -power, -power, -power);
    }

    /**
     * Basic backward movement by power variable
     */
    public void backward() {
        setBase(power, power, power, power);
    }

    /**
     * Basic left strafe movement by power variable
     */
    public void strafeLeft() {
        setBase(power, -power, -power, power);
    }

    /**
     * Basic right strafe movement by power variable
     */
    public void strafeRight() {
        setBase(-power, power, power, -power);
    }

    /**
     * @param status of subsysstem
     */
    public void setStatus(Status status) {
        this.baseStatus = status;
    }

    /**
     * @param speed of left pivot turn
     */
    public void pivotTurnLeft(double speed) {
        setLeftSide(speed);
        setRightSide(-speed);
        setStatus(Status.LEFT);
    }

    /**
     * @param speed of right pivot turn
     */
    public void pivotTurnRight(double speed) {
        setLeftSide(-speed);
        setRightSide(speed);
        setStatus(Status.RIGHT);
    }

    /**
     * Sets all motors to 0
     */
    public void stop() {
        setBase(STOP);
    }

    /**
     * Sets left side motors to 0
     */
    public void stopLeftSide() {
        setLeftSide(STOP);
    }

    /**
     * Sets right side motors to 0
     */
    public void stopRightSide() {
        setRightSide(STOP);
    }

    /**
     * Encoder method for movement. Inches determine the distance of each motor. Power determines how fast the motors will go.
     * @param fLInches
     * @param fRInches
     * @param bLInches
     * @param bRInches
     * @param fLPower
     * @param fRPower
     * @param bLPower
     * @param bRPower
     */
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

    /**
     * Encoder method for forward
     * @param inches to travel
     */
    public void forward(double inches) {
        drive(-inches, -inches, -inches, -inches, power, power, power, power);
        setStatus(Status.FORWARDS);
    }

    /**
     * Encoder method for backward
     * @param inches to travel
     */
    public void backward(double inches) {
        drive(inches, inches, inches, inches, power, power, power, power);
        setStatus(Status.BACKWARDS);
    }

    /**
     * Encoder method for strafe
     * @param leftInches to travel
     * @param rightInches to travel
     */
    public void strafe(double leftInches, double rightInches) {
        drive(leftInches, -rightInches, -leftInches, rightInches, power, power, power, power);
    }

    /**
     * Encoder method for left strafe
     * @param inches to travel
     */
    public void strafeLeft(double inches) {
        strafe(inches, inches);
        setStatus(Status.LEFT);
    }

    /**
     * Encoder method for right strafe
     * @param inches to travel
     */
    public void strafeRight(double inches) {
        strafe(-inches, -inches);
        setStatus(Status.RIGHT);
    }

    /**
     * @param fL target position
     * @param fR target position
     * @param bL target position
     * @param bR target position
     */
    private void setTargetPositions(int fL, int fR, int bL, int bR){
        frontRight.setTargetPosition(fR);
        frontLeft.setTargetPosition(fL);
        backRight.setTargetPosition(bR);
        backLeft.setTargetPosition(bL);
    }

    /**
     * @return true if any motor is running to position, false otherwise
     */
    private boolean anyBusy() {
        return frontLeft.isBusy() || frontRight.isBusy() || backLeft.isBusy() || backRight.isBusy();
    }

    /**
     * @return true if all motors are running to position, false otherwise
     */
    private boolean allBusy() {
        return frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy();
    }

    /**
     * @return power
     */
    public double getMaxPower() {
        return power;
    }

    /**
     * @return goalSpeed
     */
    public double getGoalSpeed() {
        return goalSpeed;
    }

    /**
     * @return currentSpeed
     */
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     * @return frontLeft motor position
     */
    public int getfLEncoder() {
        return frontLeft.getCurrentPosition();
    }

    /**
     * @return frontRight motor position
     */
    public int getfREncoder() {
        return frontRight.getCurrentPosition();
    }

    /**
     * @return backLeft motor position
     */
    public int getbLEncoder() {
        return backLeft.getCurrentPosition();
    }

    /**
     * @return backRight motor position
     */
    public int getbREncoder() {
        return backRight.getCurrentPosition();
    }

    /**
     * @return frontLeft tickGoal for encoder
     */
    public int getfLTickGoal() {
        return fLTickGoal;
    }

    /**
     * @return frontRight tickGoal for encoder
     */
    public int getfRTickGoal() {
        return fRTickGoal;
    }

    /**
     * @return backLeft tickGoal for encoder
     */
    public int getbLTickGoal() {
        return bLTickGoal;
    }

    /**
     * @return backRight tickGoal for encoder
     */
    public int getbRTickGoal() {
        return bRTickGoal;
    }

    /**
     * @return frontLeft motor
     */
    public DcMotor getFrontLeft() {
        return frontLeft;
    }

    /**
     * @return frontRight motor
     */
    public DcMotor getFrontRight() {
        return frontRight;
    }

    /**
     * @return backLeft motor
     */
    public DcMotor getBackLeft() {
        return backLeft;
    }

    /**
     * @return backRight motor
     */
    public DcMotor getBackRight() {
        return backRight;
    }

    /**
     * @return status of subsystem
     */
    public Status getStatus() {
        return baseStatus;
    }
}
