package org.firstinspires.ftc.teamcode.CuttingEdge.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@Autonomous (name = "Auto redSquare2")
public class redSquare2 extends LinearOpMode {
    DcMotor frontRight, frontLeft, backRight, backLeft;
    private double TICKS_PER_IN = 1120/(4*Math.PI);
    int leftTickGoal, rightTickGoal, rightCurrentPos, leftCurrentPos;
    @Override
    public void runOpMode() throws InterruptedException {
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        driveDistance(6, .5,.5);
        sleep(10000);
    }

    public void driveDistance(double inches, double leftPower, double rightPower) {

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftTickGoal = frontLeft.getCurrentPosition() + (int) (TICKS_PER_IN * inches);
        //leftTickGoal *= -1;
        //rightTickGoal = frontRight.getCurrentPosition() + (int) (TICKS_PER_IN * inches);

        frontLeft.setTargetPosition(leftTickGoal);
        //frontRight.setTargetPosition(rightTickGoal);

        /*frontLeft.getCurrentPosition() > leftTickGoal ||*/

        leftCurrentPos = frontLeft.getCurrentPosition();

        rightCurrentPos = frontRight.getCurrentPosition();

        while (Math.abs(leftCurrentPos) < rightTickGoal) {

            //frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            rightCurrentPos = frontRight.getCurrentPosition();

            frontLeft.setPower(leftPower);
            frontRight.setPower(rightPower);
            backLeft.setPower(frontLeft.getPower());
            backRight.setPower(frontRight.getPower());

            telemetry.addData("frontLeftPos: ", frontLeft.getCurrentPosition());
            telemetry.addData("frontRightPos: ", frontRight.getCurrentPosition());
            telemetry.addData("rightCurrentPos", rightCurrentPos);
            telemetry.addData("leftTickGoal: ", leftTickGoal);
            telemetry.addData("rightTickGoal: ", rightTickGoal);

            telemetry.addData("frontLeft: ", frontLeft.getPower());
            telemetry.addData("frontRight: ", frontRight.getPower());
            telemetry.addData("backLeft: ", backLeft.getPower());
            telemetry.addData("backRight: ", backRight.getPower());
            telemetry.addData("stop: ", "false");
            updateTelemetry(telemetry);
            telemetry.update();
        }

        telemetry.addData("frontLeftPos: ", frontLeft.getCurrentPosition());
        telemetry.addData("frontRightPos: ", frontRight.getCurrentPosition());
        telemetry.addData("rightCurrentPos", rightCurrentPos);
        telemetry.addData("leftTickGoal: ", leftTickGoal);
        telemetry.addData("rightTickGoal: ", rightTickGoal);

        telemetry.addData("frontLeft: ", frontLeft.getPower());
        telemetry.addData("frontRight: ", frontRight.getPower());
        telemetry.addData("backLeft: ", backLeft.getPower());
        telemetry.addData("backRight: ", backRight.getPower());

        telemetry.addData("Left Enc ", frontLeft.getCurrentPosition());
        telemetry.addData("Right Enc ", frontRight.getCurrentPosition());
        telemetry.addData("stop: ", "true");
        updateTelemetry(telemetry);
        telemetry.update();

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
    }
}
