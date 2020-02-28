package org.firstinspires.ftc.teamcode.Old.Deprecated.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class foo extends LinearOpMode {
    DcMotor frontLeft, frontRight, backLeft, backRight;
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        forward(.5);
    }

    public void forward(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }
}
