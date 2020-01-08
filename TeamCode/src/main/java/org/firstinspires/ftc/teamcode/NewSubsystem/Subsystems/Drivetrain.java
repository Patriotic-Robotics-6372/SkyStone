package org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Drivetrain subsystem
 */

public class Drivetrain implements Constants {

    private DcMotor frontRight, frontLeft, backRight, backLeft;
    private double power = STOP;
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

        stop();
    }
    
    public void setEnc(boolean has){
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

    public Enum[] getModes(){
        return new Enum[]{frontRight.getMode(), frontLeft.getMode(), backRight.getMode(), backLeft.getMode()};
    }

    public void setBrake(boolean has){
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

    public Enum[] getZPB(){
        return new Enum[]{frontRight.getZeroPowerBehavior(), frontLeft.getZeroPowerBehavior(), backRight.getZeroPowerBehavior(), backLeft.getZeroPowerBehavior()};
    }

    public void setMaxPower(double power){
        this.power = power;
    }

    public double getMaxPower(){
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

    public void moveLeftSide(double pow){
        frontLeft.setPower(pow);
        backLeft.setPower(pow);
    }

    public void moveRightSide(double pow){
        frontRight.setPower(pow);
        backRight.setPower(pow);
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
        frontRight.setPower(STOP);
        frontLeft.setPower(STOP);
        backRight.setPower(STOP);
        backLeft.setPower(STOP);
        baseStatus = Status.NEUTRAL;
    }

    public void stopLeftSide(){
        frontLeft.setPower(STOP);
        backLeft.setPower(STOP);
    }

    public void stopRightSide(){
        frontRight.setPower(STOP);
        backRight.setPower(STOP);
    }

    public Status getStatus(){
        return baseStatus;
    }

    public double[] getSpeeds(){
        return new double[]{frontLeft.getPower(), frontRight.getPower(), backLeft.getPower(), backRight.getPower()};
    }
}
