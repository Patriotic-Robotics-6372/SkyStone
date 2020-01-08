package org.firstinspires.ftc.teamcode.NewSubsystem;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Drivetrain {

    private DcMotor frontRight, frontLeft, backRight, backLeft;
    private double power;

    public Drivetrain(DcMotor fR, DcMotor fL, DcMotor bR, DcMotor bL){
        frontRight = fR;
        frontLeft = fL;
        backRight = bR;
        backLeft = bL;
        
        init();
    }
    
    public void init(){
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARDS);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARDS);
    }
    
    public void hasEnc(bool has){
        if (has){
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        } else {
            frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
            frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
            backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
            backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        }
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
    }
    
    public void backwards(){
        frontRight.setPower(power);
        frontLeft.setPower(power);
        backRight.setPower(power);
        backLeft.setPower(power);
}
