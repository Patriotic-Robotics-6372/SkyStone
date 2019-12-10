package org.firstinspires.ftc.teamcode.CuttingEdge.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.CuttingEdge.Hardware.PRRobot;

public class redSquare7 extends LinearOpMode {
    PRRobot prBot = new PRRobot();
    @Override
    public void runOpMode() throws InterruptedException {
        prBot.init(hardwareMap);

        waitForStart();



    }

    public void driveDistance(double inches, double leftPower, double rightPower) {

        prBot.TICKS_PER_IN = 1120/(4*Math.PI);

        prBot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        prBot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // the encoders may not be set to zero. this line ensures that the encoder values start at 0

        prBot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        prBot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // tell the motors to use encoders

        prBot.leftTickGoal = (int) (prBot.TICKS_PER_IN * inches); // -1 because encoders "count backwards" on left side motors
        prBot.rightTickGoal = (int) (prBot.TICKS_PER_IN * inches); // using ratio of TICKS_PER_IN, multiplying it by how many inches we want. casting to int because encoders are only whole numbers

        prBot.frontRight.setTargetPosition(prBot.rightTickGoal);
        prBot.frontLeft.setTargetPosition(prBot.leftTickGoal);

        telemetry.addData("frontRight setTargetPosition: ", prBot.frontRight.getTargetPosition());
        telemetry.addData("frontLeft setTargetPosition: ", prBot.frontLeft.getTargetPosition());

        updateTelemetry(telemetry);

        telemetry.update();

        while (prBot.frontRight.isBusy() || prBot.frontLeft.isBusy()) {
            prBot.frontRight.setPower(rightPower);
            prBot.frontLeft.setPower(leftPower);
            prBot.backRight.setPower(prBot.frontRight.getPower());
            prBot.backLeft.setPower(prBot.frontLeft.getPower());

            telemetry.addData("frontRight setTargetPosition: ", prBot.frontRight.getTargetPosition());
            telemetry.addData("frontRight currentPosition: ", prBot.frontRight.getCurrentPosition());
            telemetry.addData("frontLeft setTargetPosition: ", prBot.frontLeft.getTargetPosition());
            telemetry.addData("frontLeft currentPosition: ", prBot.frontLeft.getCurrentPosition());
            telemetry.addData("frontRight getPower: ", prBot.frontRight.getPower());
            telemetry.addData("frontLeft getPower: ", prBot.frontLeft.getPower());
            telemetry.addData("backRight getPower: ", prBot.backRight.getPower());
            telemetry.addData("backLeft getPower: ", prBot.backLeft.getPower());

            updateTelemetry(telemetry);

            telemetry.update();
        }

        prBot.frontRight.setPower(0);
        prBot.frontLeft.setPower(0);
        prBot.backRight.setPower(0);
        prBot.backLeft.setPower(0);

    }

}
