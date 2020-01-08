package org.firstinspires.ftc.teamcode.NewSubsystem;

import com.qualcomm.robotcore.hardware.CRServo;

public class Intake {

    private CRServo leftPinch, rightPinch;
    private double power;

    public Intake(CRServo lP, CRServo rP){
        leftPinch = lP;
        rightPinch = rP;
    }

    public void setPower(double power){
        this.power = power;
    }

    public double getPower(){
        return power;
    }

    public void open(){
        leftPinch.setPower(power);
        rightPinch.setPower(-power);
    }

    public void close(){
        leftPinch.setPower(-power);
        rightPinch.setPower(power);
    }


}
