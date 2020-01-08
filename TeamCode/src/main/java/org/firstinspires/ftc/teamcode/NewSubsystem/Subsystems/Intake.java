package org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Intake {

    private CRServo leftPinch, rightPinch;
    private double power;
    private enum Status{
        OPEN, CLOSE, NEUTRAL
    }
    private Status intakeStatus = Status.NEUTRAL;

    public Intake(CRServo lP, CRServo rP){
        leftPinch = lP;
        rightPinch = rP;

        init();
    }

    public void init(){
        leftPinch.setDirection(DcMotorSimple.Direction.FORWARD);
        rightPinch.setDirection(DcMotorSimple.Direction.REVERSE);

        stop();
    }

    public void setMaxPower(double power){
        this.power = power;
    }

    public double getMaxPower(){
        return power;
    }

    public void open(){
        leftPinch.setPower(power);
        rightPinch.setPower(-power);
        intakeStatus = Status.OPEN;
    }

    public void close(){
        leftPinch.setPower(-power);
        rightPinch.setPower(power);
        intakeStatus = Status.CLOSE;
    }

    public void stop(){
        leftPinch.setPower(0);
        rightPinch.setPower(0);
        intakeStatus = Status.NEUTRAL;
    }

    public Status getStatus(){
        return intakeStatus;
    }

    public double[] getSpeeds(){
        return new double[]{leftPinch.getPower(), rightPinch.getPower()};
    }
}
