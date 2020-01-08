package org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Pivot subsystem
 */

public class Pivot implements Constants{

    private DcMotor leftPivot, rightPivot;
    private double power = STOP;
    private Status pivotStatus = Status.NEUTRAL;

    public Pivot(DcMotor lP, DcMotor rP) {
        leftPivot = lP;
        rightPivot = rP;

        init();
    }

    public void init(){
        leftPivot.setDirection(DcMotorSimple.Direction.FORWARD);
        rightPivot.setDirection(DcMotorSimple.Direction.REVERSE);

        stop();
    }

    public void setMaxPower(double power){
        this.power = power;
    }

    public double getMaxPower(){
        return power;
    }

    public void up(){
        leftPivot.setPower(power);
        rightPivot.setPower(power);
        pivotStatus = Status.UP;
    }

    public void down(){
        leftPivot.setPower(-power);
        rightPivot.setPower(-power);
        pivotStatus = Status.DOWN;
    }

    public void stop(){
        leftPivot.setPower(STOP);
        rightPivot.setPower(STOP);
        pivotStatus = Status.NEUTRAL;
    }

    public Status getStatus(){
        return pivotStatus;
    }

    public double[] getSpeeds(){
        return new double[]{leftPivot.getPower(), rightPivot.getPower()};
    }
}
