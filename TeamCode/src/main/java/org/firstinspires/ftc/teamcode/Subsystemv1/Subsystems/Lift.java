package org.firstinspires.ftc.teamcode.Subsystemv1.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

//import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Lift subsystem
 */

public class Lift implements Constants {

    private DcMotor lift;
    private double power = STOP;
    private Status liftStatus = Status.NEUTRAL;
    //private int heightLevel = 0;

    public Lift(DcMotor l){
        lift = l;

        init();
    }

    public void init(){
        lift.setDirection(DcMotorSimple.Direction.FORWARD);

        stop();
    }
        /*sans*///BOONK SENSION yes
    public void setMaxPower(double power){
        this.power = power;
    }

    public double getMaxPower(){
        return power;
    }

    public void setBrake(boolean has) {
        if (has) {
            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } else {
            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }
    /*

    public void setEnc(boolean has){
        if (has) {
            lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        } else {
            lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

     */

    public void up(){
        lift.setPower(power);
        liftStatus = Status.UP;
    }

    public void down(){
        lift.setPower(-power);
        liftStatus = Status.DOWN;
    }
    /*

    public void upEnc(){
        if (checkForStop()){
            lift.setPower(-power);
        } else {
            lift.setPower(power);
        }
    }

    public void increaseHeight(){
        heightLevel++;
    }

    public void decreaseHeight(){
        heightLevel--;
    }

    public void setHeight(){
        switch (heightLevel){
            case 0:

        }
    }

    public void goToHeight(){

    }

    public void downEnc(){
        if (checkForStop()){
            lift.setPower(power);
        } else {
            lift.setPower(-power);
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
     */

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
