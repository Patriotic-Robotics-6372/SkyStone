package org.firstinspires.ftc.teamcode.Old.Deprecated.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Old.Stable.Hardware.PRRobot;

@Disabled
@Autonomous (name = "Auto redSquare6")
public class redSquare6 extends LinearOpMode {
    PRRobot prBot = new PRRobot("stable");
    @Override
    public void runOpMode() throws InterruptedException {
        prBot.init(hardwareMap);

        waitForStart();

        //driveDistance(12, .3, .3, PRRobot.Status.FORWARDS);
        driveDistance(12, .3, .3);
        //driveDistance(12, .3, .3, PRRobot.Status.BACKWARDS);
        //prBot.robotStatus(telemetry);

    }

    public void driveDistance(double inches, double leftPower, double rightPower) {

        prBot.TICKS_PER_IN = 1120/(4*Math.PI);
        prBot.STRAFE_MOD = 1.2;

        prBot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        prBot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // the encoders may not be set to zero. this line ensures that the encoder values start at 0

        prBot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        prBot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // tell the motors to use encoders

        prBot.leftTickGoal = (int) (prBot.TICKS_PER_IN * inches); // -1 because encoders "count backwards" on left side motors
        prBot.rightTickGoal = (int) (prBot.TICKS_PER_IN * inches); // using ratio of TICKS_PER_IN, multiplying it by how many inches we want. casting to int because encoders are only whole numbers
/*
        switch (status) {
            case FORWARDS:
                break;
            case BACKWARDS:
                break;
            case LEFT:
                prBot.leftTickGoal *= prBot.STRAFE_MOD;
                prBot.rightTickGoal *= prBot.STRAFE_MOD;
                break;
            case RIGHT:
                prBot.leftTickGoal *= prBot.STRAFE_MOD;
                prBot.rightTickGoal *= prBot.STRAFE_MOD;
                break;
        }

 */

        prBot.frontRight.setTargetPosition(prBot.rightTickGoal);
        prBot.frontLeft.setTargetPosition(prBot.leftTickGoal);
        /*
        telemetry.addData("frontRight getMode: ", frontRight.getMode());
        telemetry.addData("frontLeft getMode: ", frontLeft.getMode()); // it should say something about encoders


         */
        //telemetry.update();

        //robotStatus(telemetry);

        prBot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        prBot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //prBot.robotStatus(telemetry);

        while (Math.abs(prBot.frontRight.getCurrentPosition()) < prBot.rightTickGoal || Math.abs(prBot.frontLeft.getCurrentPosition()) < prBot.leftTickGoal) {
/*
            switch (status) {
                case FORWARDS:
                    prBot.frontRight.setPower(rightPower);
                    prBot.frontLeft.setPower(leftPower);
                    prBot.backRight.setPower(rightPower);
                    prBot.backLeft.setPower(leftPower);
                    break;
                case BACKWARDS:
                    prBot.frontRight.setPower(-rightPower);
                    prBot.frontLeft.setPower(-leftPower);
                    prBot.backRight.setPower(prBot.frontRight.getPower());
                    prBot.backLeft.setPower(prBot.frontLeft.getPower());
                    break;
                case LEFT:
                    prBot.frontRight.setPower(rightPower);
                    prBot.frontLeft.setPower(-leftPower);
                    prBot.backRight.setPower(-rightPower);
                    prBot.backLeft.setPower(leftPower);
                    break;
                case RIGHT:
                    prBot.frontRight.setPower(-rightPower);
                    prBot.frontLeft.setPower(leftPower);
                    prBot.backRight.setPower(rightPower);
                    prBot.backLeft.setPower(-leftPower);
                    break;
            }

 */
            prBot.frontRight.setPower(rightPower);
            prBot.frontLeft.setPower(leftPower);
            prBot.backRight.setPower(rightPower);
            prBot.backLeft.setPower(leftPower);

            /*

            telemetry.addData("frontRight enc: ", frontRight.getCurrentPosition());
            telemetry.addData("frontLeft enc: ", frontLeft.getCurrentPosition());

             */

            //telemetry.update();

            //robotStatusU();
            //prBot.robotStatus(telemetry);
        }

        sleep(5000);

        prBot.frontRight.setPower(0);
        prBot.frontLeft.setPower(0);
        prBot.backRight.setPower(0);
        prBot.backLeft.setPower(0);

        //telemetry.update();

        //robotStatus(telemetry);

        //prBot.robotStatus(telemetry);

    }
}
