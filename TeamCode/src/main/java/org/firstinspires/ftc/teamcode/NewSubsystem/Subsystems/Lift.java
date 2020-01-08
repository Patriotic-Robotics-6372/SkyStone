package org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Lift subsystem
 */

public class Lift implements Constants{

    private DcMotor lift;
    private double power = STOP;
    private Status liftStatus = Status.NEUTRAL;

    public Lift(DcMotor l){
        lift = l;

        init();
    }

    public void init(){
        lift.setDirection(DcMotorSimple.Direction.FORWARD);

        stop();
    }

    public void setMaxPower(double power){
        this.power = power;
    }

    public double getMaxPower(){
        return power;
    }

    public void up(){
        lift.setPower(power);
        liftStatus = Status.UP;
    }

    public void down(){
        lift.setPower(-power);
        liftStatus = Status.DOWN;
    }

    public void stop(){
        lift.setPower(STOP);
        liftStatus = Status.NEUTRAL;
    }

    public Status getStatus(){
        return liftStatus;
    }

    public double getSpeed(){
        return lift.getPower();
    }
}
