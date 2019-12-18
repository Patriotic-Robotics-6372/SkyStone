package org.firstinspires.ftc.teamcode.CuttingEdge.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Stable.Hardware.PRRobot;

@Disabled
@Autonomous (name = "auto redSquare7")
public class redSquare7 extends LinearOpMode {
    PRRobot prBot = new PRRobot();
    int wait;
    @Override
    public void runOpMode() throws InterruptedException {
        prBot.init(hardwareMap);
        prBot.useBrake(true);

        wait = 2000;

        waitForStart();

        driveDistance(12, .5, .5);

        driveDistance(12, 1, 1);

    }

    public void driveDistance(double inches, double leftPower, double rightPower) {

        prBot.TICKS_PER_IN = 1120/(4*Math.PI);

        //prBot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //prBot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // the encoders may not be set to zero. this line ensures that the encoder values start at

        prBot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //prBot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //prBot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //prBot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // tell the motors to use encoders

        prBot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //prBot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        prBot.leftTickGoal = (int) (prBot.TICKS_PER_IN * inches); // -1 because encoders "count backwards" on left side motors
        prBot.rightTickGoal = (int) (prBot.TICKS_PER_IN * inches); // using ratio of TICKS_PER_IN, multiplying it by how many inches we want. casting to int because encoders are only whole numbers

        //prBot.frontRight.setTargetPosition(prBot.rightTickGoal);
        //prBot.frontLeft.setTargetPosition(prBot.leftTickGoal);

        prBot.backRight.setTargetPosition(prBot.rightTickGoal);
        //prBot.backLeft.setTargetPosition(prBot.leftTickGoal);
        /*
        telemetry.addData("frontRight getTargetPosition: ", prBot.frontRight.getTargetPosition());
        telemetry.addData("frontRight currentPosition: ", prBot.frontLeft.getCurrentPosition());
        telemetry.addData("frontLeft getTargetPosition: ", prBot.frontLeft.getTargetPosition());
        telemetry.addData("frontLeft currentPosition: ", prBot.frontLeft.getCurrentPosition());

         */

        telemetry.addData("backRight getTargetPosition: ", prBot.backRight.getTargetPosition());
        telemetry.addData("backRight currentPosition: ", prBot.backRight.getCurrentPosition());
        telemetry.addData("backLeft getTargetPosition: ", prBot.backLeft.getTargetPosition());
        telemetry.addData("backLeft currentPosition: ", prBot.backLeft.getCurrentPosition());

        updateTelemetry(telemetry);

        telemetry.update();

        sleep(wait);

        //prBot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //prBot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        prBot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //prBot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (prBot.backRight.isBusy()/* || prBot.backLeft.isBusy()*/) {
            prBot.frontRight.setPower(rightPower);
            prBot.frontLeft.setPower(leftPower);
            prBot.backRight.setPower(rightPower);
            prBot.backLeft.setPower(leftPower);

            telemetry.addData("backRight setTargetPosition: ", prBot.backRight.getTargetPosition());
            telemetry.addData("backRight currentPosition: ", prBot.backRight.getCurrentPosition());
            //telemetry.addData("backLeft setTargetPosition: ", prBot.backLeft.getTargetPosition());
            //telemetry.addData("backLeft currentPosition: ", prBot.backLeft.getCurrentPosition());
            telemetry.addData("backRight getPower: ", prBot.backRight.getPower());
            //telemetry.addData("backLeft getPower: ", prBot.backLeft.getPower());
            telemetry.addData("frontRight getPower: ", prBot.backRight.getPower());
            //telemetry.addData("frontLeft getPower: ", prBot.frontLeft.getPower());

            updateTelemetry(telemetry);

            telemetry.update();
        }

        /*

        telemetry.addData("frontRight setTargetPosition: ", prBot.frontRight.getTargetPosition());
        telemetry.addData("frontRight currentPosition: ", prBot.frontRight.getCurrentPosition());
        telemetry.addData("frontLeft setTargetPosition: ", prBot.frontLeft.getTargetPosition());
        telemetry.addData("frontLeft currentPosition: ", prBot.frontLeft.getCurrentPosition());
        telemetry.addData("frontRight getPower: ", prBot.frontRight.getPower());
        telemetry.addData("frontLeft getPower: ", prBot.frontLeft.getPower());
        telemetry.addData("backRight getPower: ", prBot.backRight.getPower());
        telemetry.addData("backLeft getPower: ", prBot.backLeft.getPower());

        */

        updateTelemetry(telemetry);

        telemetry.update();

        prBot.frontRight.setPower(0);
        prBot.frontLeft.setPower(0);
        prBot.backRight.setPower(0);
        prBot.backLeft.setPower(0);

        sleep(wait);

    }

}
