package org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Lift subsystem
 */

public class Lift implements Constants{

    private DcMotor lift;
    private double power = STOP;
    private Status liftStatus = Status.NEUTRAL;
    private Telemetry telemetry;

    public Lift(DcMotor l){
        lift = l;

        init();
    }

    public void init(){
        lift.setDirection(DcMotorSimple.Direction.REVERSE);

        stop();
    }
        /*sans*///BOONK SENSION yes
    public void setMaxPower(double power){
        this.power = power;
    }

    public double getMaxPower(){
        return power;
    }

    public void setTelemetry(Telemetry telem){
        this.telemetry = telem;
    }

    public void setEnc(boolean has){
        if (has) {
            lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        } else {
            lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void up(){
        lift.setPower(power);
        liftStatus = Status.UP;
    }

    public void down(){
        lift.setPower(-power);
        liftStatus = Status.DOWN;
    }

    public void upEnc(){
        if (!checkForStop()){
            lift.setPower(power);
        } else {
            lift.setPower(-power);
        }
    }

    public void downEnc(){
        if (!checkForStop()){
            lift.setPower(-power);
        } else {
            lift.setPower(power);
        }
    }

    public boolean checkForStop(){
        if (getLiftEncoder() > LIFT_MAX || getLiftEncoder() < LIFT_MIN) {
            return true;
        } else {
            return false;
        }
    }

    public int getLiftEncoder(){
        return lift.getCurrentPosition();
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
