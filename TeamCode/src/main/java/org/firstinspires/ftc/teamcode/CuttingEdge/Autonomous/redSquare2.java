package org.firstinspires.ftc.teamcode.CuttingEdge.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous (name = "Auto redSquare2")
public class redSquare2 extends LinearOpMode {
    DcMotor frontRight, frontLeft, backRight, backLeft;
    private double TICKS_PER_IN = 1120/(4*Math.PI);
    int leftTickGoal, rightTickGoal;
    @Override
    public void runOpMode() throws InterruptedException {
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        driveDistance(6, .5,.5);
    }

    public void driveDistance(double inches, double leftPower, double rightPower) {

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftTickGoal = frontLeft.getCurrentPosition() + (int) (TICKS_PER_IN * inches);
        rightTickGoal = frontRight.getCurrentPosition() + (int) (TICKS_PER_IN * inches);

        frontLeft.setTargetPosition(leftTickGoal);
        frontRight.setTargetPosition(rightTickGoal);

        while (Math.abs(frontLeft.getCurrentPosition()) < leftTickGoal ||
                Math.abs(frontRight.getCurrentPosition()) < rightTickGoal) {

            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            frontLeft.setPower(leftPower);
            frontRight.setPower(rightPower);
            backLeft.setPower(frontLeft.getPower());
            backRight.setPower(frontRight.getPower());
        }

        telemetry.addData("Left Enc ", frontLeft.getCurrentPosition());
        telemetry.addData("Right Enc ", frontRight.getCurrentPosition());
        updateTelemetry(telemetry);
        telemetry.update();

        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
    }
}
