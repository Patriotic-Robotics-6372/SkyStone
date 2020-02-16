package org.firstinspires.ftc.teamcode.Subsystemv2.subsys.pivot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;

/**
 * Date: 2/12/20
 * Author: Jacob Marinas
 * The pivot subsystem
 */
public class Pivot implements Constants {

    private final DcMotor leftPivot, rightPivot;
    private double power, currentSpeed, goalSpeed, speedRate;
    private Status pivotStatus = Status.NEUTRAL;

    public Pivot(DcMotor lP, DcMotor rP) {
        this.leftPivot = lP;
        this.rightPivot = rP;

        init();
    }

    /**
     * Sets direction of pivot motors and sets their power to 0
     */
    private void init() {
        leftPivot.setDirection(DcMotorSimple.Direction.FORWARD);
        rightPivot.setDirection(DcMotorSimple.Direction.REVERSE);

        power = STOP;
    }

    /**
     * Sets power of pivot motors
     * @param lPPower power
     * @param rPPower power
     */
    public void setPivot(double lPPower, double rPPower) {
        leftPivot.setPower(lPPower);
        rightPivot.setPower(rPPower);
    }

    /**
     * Move pivot up by power
     */
    public void up() {
        setPivot(power, power);
        setStatus(Status.UP);
    }

    /**
     * Move pivot down by power
     */
    public void down() {
        setPivot(-power, -power);
        setStatus(Status.DOWN);
    }

    /**
     * Sets power of pivot motors to 0
     */
    public void stop() {
        setPivot(STOP, STOP);
    }

    /**
     * @param power out of 1 of max power 
     */
    public void setMaxPower(double power) {
        this.power = power;
    }

    /**
     * @param status of subsystem
     */
    public void setStatus(Status status) {
        pivotStatus = status;
    }

    /**
     * @return status of subsystem
     */
    public Status getStatus() {
        return pivotStatus;
    }

    /**
     * @return left pivot motor
     */
    public DcMotor getLeftPivot() {
        return leftPivot;
    }

    /**
     * @return right pivot motor
     */
    public DcMotor getRightPivot() {
        return rightPivot;
    }
}
