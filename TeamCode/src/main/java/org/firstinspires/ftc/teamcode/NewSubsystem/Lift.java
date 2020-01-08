package org.firstinspires.ftc.teamcode.NewSubsystem;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Lift {

    private DcMotor lift;
    private double power;
    private enum Status {
        UP, DOWN, NEUTRAL
    }
    private Status liftStatus = Status.NEUTRAL;

    public Lift(DcMotor l){
        lift = l;
    }

    public void setPower(double power){
        this.power = power;
    }

    public double getPower(){
        return power;
    }

    public void up(){
        lift.setPower(power);
        liftStatus = Status.UP;
    }

    public void down(){
        lift.setPower(power);
        liftStatus = Status.DOWN;
    }

    public void stop(){
        lift.setPower(0);
        liftStatus = Status.NEUTRAL;
    }
}