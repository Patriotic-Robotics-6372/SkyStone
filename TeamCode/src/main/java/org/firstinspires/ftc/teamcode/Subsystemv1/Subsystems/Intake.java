package org.firstinspires.ftc.teamcode.Subsystemv1.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Intake subsystem
 */

public class Intake implements Constants {

    private CRServo leftPinch, rightPinch;
    private double power = STOP;
    private Status intakeStatus = Status.NEUTRAL;

    public Intake(CRServo lP, CRServo rP){
        leftPinch = lP;
        rightPinch = rP;

        init();
    }

    public void init(){
        leftPinch.setDirection(DcMotorSimple.Direction.REVERSE);
        rightPinch.setDirection(DcMotorSimple.Direction.FORWARD);

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
        rightPinch.setPower(power);
        intakeStatus = Status.OPEN;
    }

    public void close(){
        leftPinch.setPower(-power);
        rightPinch.setPower(-power);
        intakeStatus = Status.CLOSE;
    }

    public void stop(){
        leftPinch.setPower(STOP);
        rightPinch.setPower(STOP);
        intakeStatus = Status.NEUTRAL;
    }

    public Status getStatus(){
        return intakeStatus;
    }

    public double[] getSpeeds(){
        return new double[]{leftPinch.getPower(), rightPinch.getPower()};
    }
}
