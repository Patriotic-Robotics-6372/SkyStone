package org.firstinspires.ftc.teamcode.CuttingEdge.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.CuttingEdge.Hardware.PRRobot;

@Disabled
@Autonomous(name = "Auto redSquare")
public class redSquare extends LinearOpMode {
    PRRobot prBot =
            new PRRobot("stable");
    private double TICKS_PER_IN = 1120/(4*Math.PI);
    int leftTickGoal, rightTickGoal;
    @Override
    public void runOpMode() throws InterruptedException {
        prBot.init(hardwareMap);

        //prBot.brake();
        prBot.start();

        waitForStart();

        driveDistance(6, .5,.5);


    }

    public void driveDistance(double inches, double leftPower, double rightPower) {

        prBot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        prBot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftTickGoal = prBot.frontLeft.getCurrentPosition() + (int) (TICKS_PER_IN * inches);
        rightTickGoal = prBot.frontRight.getCurrentPosition() + (int) (TICKS_PER_IN * inches);

        while (Math.abs(prBot.frontLeft.getCurrentPosition()) < leftTickGoal ||
                Math.abs(prBot.frontRight.getCurrentPosition()) < rightTickGoal) {

            prBot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            prBot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            prBot.frontLeft.setPower(leftPower);
            prBot.frontRight.setPower(rightPower);
            prBot.backLeft.setPower(prBot.frontLeft.getPower());
            prBot.backRight.setPower(prBot.frontRight.getPower());
        }

        telemetry.addData("Left Enc ", prBot.frontLeft.getCurrentPosition());
        telemetry.addData("Right Enc ", prBot.frontRight.getCurrentPosition());
        updateTelemetry(telemetry);
        telemetry.update();

    }


}
