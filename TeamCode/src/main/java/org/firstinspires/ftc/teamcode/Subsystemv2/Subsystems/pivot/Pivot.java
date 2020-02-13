package org.firstinspires.ftc.teamcode.Subsystemv2.subsystems.pivot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsystems.Constants;

public class Pivot implements Constants {

    private final DcMotor leftPivot, rightPivot;
    private double power, currentSpeed, goalSpeed, speedRate;
    private Status pivotStatus = Status.NEUTRAL;

    public Pivot(DcMotor lP, DcMotor rP) {
        this.leftPivot = lP;
        this.rightPivot = rP;

        init();
    }

    private void init() {
        leftPivot.setDirection(DcMotorSimple.Direction.FORWARD);
        rightPivot.setDirection(DcMotorSimple.Direction.REVERSE);

        power = STOP;
    }

    public void setPivot(double lPPower, double rPPower) {
        leftPivot.setPower(lPPower);
        rightPivot.setPower(rPPower);
    }

    public void up() {
        leftPivot.setPower(power);
        rightPivot.setPower(power);
        setStatus(Status.UP);
    }

    public void down() {
        leftPivot.setPower(-power);
        rightPivot.setPower(-power);
        setStatus(Status.DOWN);
    }

    public void setStatus(Status status) {
        pivotStatus = status;
    }
}
