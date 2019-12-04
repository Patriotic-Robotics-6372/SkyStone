package org.firstinspires.ftc.teamcode.CuttingEdge.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous (name = "Auto redSquare3")
public class redSquare3 extends LinearOpMode {
    DcMotor frontRight, frontLeft, backRight, backLeft, leftPivot, rightPivot, lift;
    CRServo leftPinch, rightPinch;

    @Override
    public void runOpMode() throws InterruptedException {
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        leftPivot = hardwareMap.dcMotor.get("leftPivot");
        rightPivot = hardwareMap.dcMotor.get("rightPivot");

        leftPinch = hardwareMap.crservo.get("leftPinch");
        rightPinch = hardwareMap.crservo.get("rightPinch");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        leftPinch.setPower(-0.4);
        rightPinch.setPower(0.4);

        waitForStart();

        leftPivot.setPower(.8);
        rightPivot.setPower(-.8);

        frontRight.setPower(0.6);
        frontLeft.setPower(0.6);
        backRight.setPower(0.6);
        backLeft.setPower(0.6);

        sleep(1000);

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);

        leftPinch.setPower(0.4);
        rightPinch.setPower(-0.4);

        sleep(500);

        frontRight.setPower(0.3);
        frontLeft.setPower(0.3);
        backRight.setPower(0.3);
        backLeft.setPower(0.3);

        sleep(500);

        frontRight.setPower(-0.6);
        frontLeft.setPower(-0.6);
        backRight.setPower(-0.6);
        backLeft.setPower(-0.6);

        sleep(1000);

        frontRight.setPower(0.3);
        frontLeft.setPower(-0.3);
        backRight.setPower(0.3);
        backLeft.setPower(-0.3);

        sleep(500);

        leftPivot.setPower(-.2);
        rightPivot.setPower(.2);

        frontRight.setPower(0.6);
        frontLeft.setPower(0.6);
        backRight.setPower(0.6);
        backLeft.setPower(0.6);

        sleep(2600);

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);

        sleep(2000);








    }
}
