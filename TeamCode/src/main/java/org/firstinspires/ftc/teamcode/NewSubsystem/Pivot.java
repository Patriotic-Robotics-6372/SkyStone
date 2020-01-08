package org.firstinspires.ftc.teamcode.NewSubsystem;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Pivot {

    private DcMotor leftPivot, rightPivot;
    private double power;
    private enum Status {
        UP, DOWN, NEUTRAL
    }
    private Status pivotStatus = Status.NEUTRAL;

    public Pivot(DcMotor lP, DcMotor rP) {
        leftPivot = lP;
        rightPivot = rP;
    }

    public void setPower(double power){
        this.power = power;
    }

    public double getPower(){
        return power;
    }
    public void up(){
        leftPivot.setPower(power);
        rightPivot.setPower(-power);
        pivotStatus = Status.UP;
    }

    public void down(){
        leftPivot.setPower(-power);
        rightPivot.setPower(power);
        pivotStatus = Status.DOWN;
    }

    public void stop(){
        leftPivot.setPower(0);
        rightPivot.setPower(0);
        pivotStatus = Status.NEUTRAL;
    }
}
