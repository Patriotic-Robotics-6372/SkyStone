package org.firstinspires.ftc.teamcode.Old.Stable;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class example extends OpMode {

    DcMotor frontRight, frontLeft, backRight, backLeft;
    double move, slide, spin;

    @Override
    public void init() {
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
    }

    @Override
    public void loop() {

        move = -gamepad1.left_stick_y;
        slide = gamepad1.left_stick_x;
        spin = gamepad1.right_stick_x;

        frontLeft.setPower(move + slide + spin);
        frontRight.setPower(move - slide - spin);
        backLeft.setPower(move - slide + spin);
        backRight.setPower(move + slide - spin);

    }

    public void move(double power) {
        frontRight.setPower(power);
        frontLeft.setPower(-power);
        backRight.setPower(power);
        backLeft.setPower(-power);
    }

    public void pivotTurn(double power) {
        frontRight.setPower(power);
        frontLeft.setPower(power);
        backRight.setPower(power);
        backLeft.setPower(power);
    }
    public void strafe(double power) {
        frontRight.setPower(power);
        frontLeft.setPower(-power);
        backRight.setPower(power);
        backLeft.setPower(-power);
    }

    public void stop() {
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
    }
}
