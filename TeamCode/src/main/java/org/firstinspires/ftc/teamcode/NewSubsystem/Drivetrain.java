package org.firstinspires.ftc.teamcode.NewSubsystem;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Drivetrain {

    private DcMotor frontRight, frontLeft, backRight, backLeft;
    private double power;
    private boolean hasEnc, hasBrake;
    private enum Status {
        FORWARDS, BACKWARDS, LEFT, RIGHT, NEUTRAL
    }
    private Status baseStatus = Status.NEUTRAL;

    public Drivetrain(DcMotor fR, DcMotor fL, DcMotor bR, DcMotor bL){
        frontRight = fR;
        frontLeft = fL;
        backRight = bR;
        backLeft = bL;
        
        init();
    }
    
    public void init(){
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
    }
    
    public void setEnc(boolean has){
        hasEnc = has;
        if (has){
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

    public boolean hasEnc(){
        return hasEnc;
    }

    public void setBrake(boolean has){
        hasBrake = has;
        if (has) {
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

    public boolean hasBrake(){
        return hasBrake;
    }

    public void setPower(double power){
        this.power = power;
    }

    public double getPower(){
        return power;
    }

    public void forward(){
        frontRight.setPower(power);
        frontLeft.setPower(power);
        backRight.setPower(power);
        backLeft.setPower(power);
        baseStatus = Status.FORWARDS;
    }
    
    public void backwards() {
        frontRight.setPower(power);
        frontLeft.setPower(power);
        backRight.setPower(power);
        backLeft.setPower(power);
        baseStatus = Status.BACKWARDS;
    }

    public void strafeLeft(){
        frontRight.setPower(-power);
        frontLeft.setPower(power);
        backRight.setPower(power);
        backLeft.setPower(-power);
        baseStatus = Status.LEFT;
    }

    public void strafeRight(){
        frontRight.setPower(power);
        frontLeft.setPower(-power);
        backRight.setPower(-power);
        backLeft.setPower(power);
        baseStatus = Status.RIGHT;
    }

    public void stop(){
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
        baseStatus = Status.NEUTRAL;
    }

    public Status getStatus(){
        return baseStatus;
    }
}
