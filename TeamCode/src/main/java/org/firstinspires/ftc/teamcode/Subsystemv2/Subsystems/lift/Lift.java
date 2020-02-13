package org.firstinspires.ftc.teamcode.Subsystemv2.Subsystems.lift;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Subsystemv2.Subsystems.Constants;

public class Lift implements Constants {

    DcMotor lift;
    private double power, currentSpeed, goalSpeed, speedRate;
    private Status liftStatus = Status.NEUTRAL;
    private int tickGoal, currentLevel;

    public Lift(DcMotor l) {
        this.lift = l;
    }

    public void init(){
        lift.setDirection(DcMotorSimple.Direction.REVERSE);

        power = STOP;
        currentLevel = 0;
    }

    public void useEncoders(boolean use) {
        if (use) {
            lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else {
            lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void useBrake(boolean use) {
        if (use) {
            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } else {
            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }

    public void move(int ticks) {
        tickGoal = ticks;
        //lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setTargetPosition(tickGoal);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setPower(power);
        while (lift.isBusy()) {

        }
        stop();
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void up() {
        lift.setPower(power);
        setStatus(Status.UP);
    }

    public void down() {
        lift.setPower(-power);
        setStatus(Status.DOWN);
    }

    public void stop() {
        lift.setPower(STOP);
        setStatus(Status.NEUTRAL);
    }

    public void increaseLevel() {
        if (currentLevel + 1 <= MAX_LEVEL) {
            currentLevel++;
        }
    }

    public void decreaseLevel() {
        if (currentLevel - 1 >= MIN_LEVEL) {
            currentLevel--;
        }
    }

    public void setLevel(int level) {
        currentLevel = level;
    }

    public void updateLevel() {
        switch (currentLevel) {
            case 0:
                move(LEVEL_ONE);
                break;
            case 1:
                move(LEVEL_TWO);
                break;
            case 2:
                move(LEVEL_THREE);
                break;
            case 3:
                move(LEVEL_FOUR);
                break;
            default:
                break;
        }
    }

    public void setMaxPower(double power) {
        this.power = power;
    }

    public void setStatus(Status status) {
        this.liftStatus = status;
    }

    public double getMaxPower() {
        return power;
    }

    public Status getStatus() {
        return liftStatus;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public DcMotor getLift() {
        return lift;
    }

}
