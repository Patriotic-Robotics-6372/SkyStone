package org.firstinspires.ftc.teamcode.Stable.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/*
    author: Emilee Woodward
    date: 10/23/19
    desc: autonomous. robot should be faced to the bridge. parks under bridge.
 */

@Autonomous (name= "bridgeParkShort")
public class bridgeParkShort extends LinearOpMode {
    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backRight;
    public DcMotor backLeft;


    @Override
    public void runOpMode() throws InterruptedException {
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        goForward(1,1,800);
    }

    public void goForward (double leftPower, double rightPower,int milliseconds){
        frontRight.setPower(rightPower);
        frontLeft.setPower(leftPower);
        backRight.setPower(rightPower);
        backLeft.setPower(leftPower);
    }

    public void goBackward (double leftPower, double rightPower,int millisecond){
        frontRight.setPower(rightPower);
        frontLeft.setPower(leftPower);
        backRight.setPower(rightPower);
        backLeft.setPower(leftPower);
    }

    public void pivotRightTurn (double leftPower,double rightPower,int milliseconds){
        frontRight.setPower(-rightPower);
        frontLeft.setPower (leftPower);
        backRight.setPower(-rightPower);
        backLeft.setPower(rightPower);
        sleep(milliseconds);
    }

    public void pivotLeftTurn (double leftPower,double rightPower,int milliseconds){
        frontRight.setPower(rightPower);
        frontLeft.setPower (-leftPower);
        backRight.setPower (rightPower);
        backLeft.setPower (-leftPower);
        sleep(milliseconds);
    }

    public void pointRightTurn (double leftPower,int milliseconds){
        frontLeft.setPower(leftPower);
        backLeft.setPower(leftPower);
        sleep(milliseconds);
    }

    public void pointLeftTurn (double rightPower,int milliseconds){
        frontRight.setPower(rightPower);
        backRight.setPower(rightPower);
        sleep(milliseconds);
    }


}

