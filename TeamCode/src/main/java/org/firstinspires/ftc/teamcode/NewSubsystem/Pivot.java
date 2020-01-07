package org.firstinspires.ftc.teamcode.NewSubsystem;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Pivot {

    private DcMotor leftPivot, rightPivot;
    private double power;

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
    }

    public void down(){
        leftPivot.setPower(-power);
        rightPivot.setPower(power);
    }
}
