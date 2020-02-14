package org.firstinspires.ftc.teamcode.Subsystemv2.subsys.intake;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;

public class Intake implements Constants {

    private CRServo leftPinch, rightPinch;
    private double power, goalSpeed, currentSpeed, speedRate;
    private Status intakeStatus = Status.NEUTRAL;

    public Intake(CRServo lP, CRServo rP) {
        this.leftPinch = lP;
        this.rightPinch = rP;
    }

    public void init(){
        leftPinch.setDirection(DcMotorSimple.Direction.FORWARD);
        rightPinch.setDirection(DcMotorSimple.Direction.REVERSE);

        power = STOP;
    }

    public void setMaxPower(double power){
        this.power = power;
    }

    public double getMaxPower(){
        return power;
    }

    public void setIntake(double lP, double rP) {
        leftPinch.setPower(lP);
        rightPinch.setPower(rP);
    }

    public void open() {
        setIntake(power, power);
        setStatus(Status.OPEN);
    }

    public void close() {
        setIntake(-power, -power);
        setStatus(Status.CLOSE);
    }

    public void stop() {
        setIntake(STOP, STOP);
        setStatus(Status.NEUTRAL);
    }

    public void setStatus(Status status){
        this.intakeStatus = status;
    }

    public Status getStatus(){
        return intakeStatus;
    }

}
