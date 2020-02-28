package org.firstinspires.ftc.teamcode.Subsystemv2.subsys.lift;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;

/**
 * Date: 2/14/20
 * Author: Jacob Marinas
 * The lift subsystem. It can use encoders to go to an exact height we want it to go, for going up and down a level.
 */
public class Lift implements Constants {

    DcMotor lift;
    private double power, currentSpeed, goalSpeed, speedRate;
    private Status liftStatus = Status.NEUTRAL;
    private int tickGoal, currentLevel, currentTick;
    private Telemetry telem;

    public Lift(DcMotor l) {
        this.lift = l;
    }

    /**
     * Sets direction of lift motor and sets their power and currentLevel to 0
     */
    public void init(){
        lift.setDirection(DcMotorSimple.Direction.REVERSE);

        power = STOP;
        currentLevel = 0;
    }

    /**
     * @param telem of main program
     */
    public void setTelemetry(Telemetry telem) {
        this.telem = telem;
    }

    /**
     * @param use RUN_USING_ENCODER
     */
    public void useEncoders(boolean use) {
        if (use) {
            lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else {
            lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    /**
     * @param use ZeroPowerBehavior.BRAKE
     */
    public void useBrake(boolean use) {
        if (use) {
            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } else {
            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }

    /**
     * Encoder method for moving lift 
     * @param ticks to travel to
     */
    public void move(int ticks) {
        tickGoal = ticks;
        //lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setTargetPosition(tickGoal);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setPower(power);
        while (lift.isBusy()) {
            telem.addData("TickGoal", getTickGoal());
            telem.addData("CurrentPos", lift.getCurrentPosition());
            telem.addData("Speed", lift.getPower());
            telem.update();
        }
        stop();
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Basic up movement by power
     */
    public void up() {
        lift.setPower(power);
        setStatus(Status.UP);
    }

    /**
     * Basic down movement by power
     */
    public void down() {
        lift.setPower(-power);
        setStatus(Status.DOWN);
    }

    /**
     * Sets lift to 0
     */
    public void stop() {
        lift.setPower(STOP);
        setStatus(Status.NEUTRAL);
    }

    /**
     * Increases currentLevel by 1
     */
    public void increaseLevel() {
        if (currentLevel + 1 <= MAX_LEVEL) {
            currentLevel++;
        }
    }

    /**
     * Decreases currentLevel by 1
     */
    public void decreaseLevel() {
        if (currentLevel - 1 >= MIN_LEVEL) {
            currentLevel--;
        }
    }

    /**
     * @param level of currentLevel
     */
    public void setLevel(int level) {
        currentLevel = level;
    }

    /**
     * Updates position of lift motor
     */
    public void updateLevel() {
        switch (currentLevel) {
                case 0:
                    currentTick = LEVEL_ZERO;
                    break;
                case 1:
                    currentTick = LEVEL_ONE;
                    break;
                case 2:
                    currentTick = LEVEL_TWO;
                    break;
                case 3:
                    currentTick = LEVEL_THREE;
                    break;
                case 4:
                    currentTick = LEVEL_FOUR;
                    break;
                case 5:
                    currentTick = LEVEL_FIVE;
                    break;
                case -1:
                    currentTick = LEVEL_SUBONE;
                    break;
                case -2:
                    currentTick = LEVEL_SUBTWO;
                    break;
                default:
                    break;
            }
        if (lift.getCurrentPosition() > getCurrentTick() + 10 || lift.getCurrentPosition() < getCurrentTick() - 10) {
            move(currentTick);
        }
    }

    /**
     * @param power out of 1 of the max power
     */
    public void setMaxPower(double power) {
        this.power = power;
    }

    /**
     * @param status of subsystem
     */
    public void setStatus(Status status) {
        this.liftStatus = status;
    }

    /**
     * @return power
     */
    public double getMaxPower() {
        return power;
    }

    /**
     * @return status of subsystem
     */
    public Status getStatus() {
        return liftStatus;
    }

    /**
     * @return currentLevel
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * @return tickGoal of lift
     */
    public int getTickGoal() {
        return tickGoal;
    }

    /**
     * @return currentTick
     */
    public int getCurrentTick() {
        return currentTick;
    }

    /**
     * @return lift motor
     */
    public DcMotor getLift() {
        return lift;
    }

}
