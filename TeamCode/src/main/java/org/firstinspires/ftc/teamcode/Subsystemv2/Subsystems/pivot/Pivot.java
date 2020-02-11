package org.firstinspires.ftc.teamcode.Subsystemv2.subsystems.pivot;

public class Pivot extends Constants {

    private final DcMotor leftPivot, rightPivot;
    private double power, currentSpeed, goalSpeed, speedRate;
    private Status pivotStatus = Status.NEUTRAL;

    public Pivot(DcMotor lP, DcMotor rP) {
        this.leftPivot = lP;
        this.rightPivot = rP;
        
        init();
    }

    private void init() {
        leftPivot.setDirection();
        rightPivot.setDirection();

        power = STOP;
    }

    public void setPivot(double lPPower, double rPPower) {
       leftPivot.setPower(lPPower);
       rightPivot.setPower(rPPower);
    }

    public
        pivotStatus = Status.UP;
    }

    public void down() {
        leftPivot.setPower(-power);
        rightPivot.setPower(-power);
    }

    public void stop() {
        leftPivot.
}
