package org.firstinspires.ftc.teamcode.Subsystemv2.subsys.intake;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;

/**
 * Date: 2/12/20
 * Author: Jacob Marinas
 * The intake subsystem
 */
public class Intake implements Constants {

    private CRServo leftPinch, rightPinch;
    private double power, goalSpeed, currentSpeed, speedRate;
    private Status intakeStatus = Status.NEUTRAL;

    public Intake(CRServo lP, CRServo rP) {
        this.leftPinch = lP;
        this.rightPinch = rP;
    }

    /**
     * Sets directions of crservos and sets their power to 0
     */
    public void init(){
        leftPinch.setDirection(DcMotorSimple.Direction.FORWARD);
        rightPinch.setDirection(DcMotorSimple.Direction.FORWARD);

        power = STOP;
    }

    /**
     * @param power out of one of the maz power
     */
    public void setMaxPower(double power){
        this.power = power;
    }

    /**
     * @return power
     */
    public double getMaxPower(){
        return power;
    }

    /**
     * Sets power of crservos
     * @param lP power
     * @param rP power
     */
    public void setIntake(double lP, double rP) {
        leftPinch.setPower(lP);
        rightPinch.setPower(rP);
    }

    /**
     * Opens intake by power
     */
    public void open() {
        setIntake(power, -power);
        setStatus(Status.OPEN);
    }

    /**
     * Closes intake by power
     */
    public void close() {
        setIntake(-power, power);
        setStatus(Status.CLOSE);
    }

    /**
     * Stops intake
     */
    public void stop() {
        setIntake(STOP, STOP);
        setStatus(Status.NEUTRAL);
    }

    /**
     * @param status of subsystem
     */
    public void setStatus(Status status){
        this.intakeStatus = status;
    }

    /**
     * @return status of subsystem
     */
    public Status getStatus(){
        return intakeStatus;
    }

    /**
     * @return left pinch crservo
     */
    public CRServo getLeftPinch() {
        return leftPinch;
    }

    /**
     * @return right pinch crservo
     */
    public CRServo getRightPinch() {
        return rightPinch;
    }

}
