package org.firstinspires.ftc.teamcode.CuttingEdge.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.CuttingEdge.Hardware.PRRobot;

@Autonomous (name = "encoderTest")
public class encoderTest extends LinearOpMode {
    PRRobot prBot = new PRRobot();
    double TICKS_PER_IN = 1120/4*Math.PI;
    int tickGoal, wait;
    @Override
    public void runOpMode() throws InterruptedException {
        prBot.init(hardwareMap);
        prBot.useBrake(true);
        prBot.useEnc(true);
        wait = 2000;
        waitForStart();
        driveDistance(6, .5, .5, "fR");
        driveDistance(6, .5, .5, "bL");
    }

    public void driveDistance(double inches, double rightPower, double leftPower, String motor) {
        tickGoal = (int) (TICKS_PER_IN * inches);
        switch (motor) {
            case "fR":
                prBot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                //telemetry.addData("fR getMode: ", prBot.frontRight.getMode());
                //prBot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                encoderTelemetry(motor, false);
                sleep(wait);
                prBot.frontRight.setTargetPosition(tickGoal);
                prBot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                encoderTelemetry(motor, false);
                sleep(wait);
                break;
            case "fL":
                prBot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                //telemetry.addData("fL getMode: ", prBot.frontLeft.getMode());
                //prBot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                encoderTelemetry(motor, false);
                sleep(wait);
                prBot.frontLeft.setTargetPosition(tickGoal);
                prBot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                encoderTelemetry(motor, false);
                sleep(wait);
                break;
            case "bR":
                prBot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                //telemetry.addData("bR getMode: ", prBot.backRight.getMode());
                //prBot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                encoderTelemetry(motor, false);
                sleep(wait);
                prBot.backRight.setTargetPosition(tickGoal);
                prBot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                encoderTelemetry(motor, false);
                sleep(wait);
                break;
            case "bL":
                prBot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                //telemetry.addData("bL getMode: ", prBot.backLeft.getMode());
                //prBot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                encoderTelemetry(motor, false);
                sleep(wait);
                prBot.backLeft.setTargetPosition(tickGoal);
                prBot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                encoderTelemetry(motor, false);
                sleep(wait);
                break;
        }
        encoderTelemetry(motor, true);
        sleep(wait);
        while (anyBusy() && opModeIsActive()) {
            prBot.frontRight.setPower(rightPower);
            prBot.frontLeft.setPower(leftPower);
            prBot.backRight.setPower(rightPower);
            prBot.backLeft.setPower(leftPower);
            encoderTelemetry(motor, true);
        }
        prBot.frontRight.setPower(prBot.STOP);
        prBot.frontLeft.setPower(prBot.STOP);
        prBot.backRight.setPower(prBot.STOP);
        prBot.backLeft.setPower(prBot.STOP);
        encoderTelemetry(motor, true);
        sleep(wait);
    }

    public boolean anyBusy(){
        if (prBot.frontRight.isBusy() || prBot.frontLeft.isBusy() || prBot.backRight.isBusy() || prBot.backLeft.isBusy()) {
            return true;
        } else {
            return false;
        }
    }

    public void encoderTelemetry(String motor, boolean toggle) {
        if (toggle) {
            switch (motor) {
                case "fR":
                    telemetry.addData("fR getCurPos: ", prBot.frontRight.getCurrentPosition());
                    telemetry.addData("fR getTarPos: ", prBot.frontRight.getTargetPosition());
                    telemetry.addData("fR isBusy: ", prBot.frontRight.isBusy());
                case "fL":
                    telemetry.addData("fL getCurPos: ", prBot.frontLeft.getCurrentPosition());
                    telemetry.addData("fL getTarPos: ", prBot.frontLeft.getTargetPosition());
                    telemetry.addData("fL isBusy: ", prBot.frontLeft.isBusy());
                case "bR":
                    telemetry.addData("bR getCurPos: ", prBot.backRight.getCurrentPosition());
                    telemetry.addData("bR getTarPos: ", prBot.backRight.getTargetPosition());
                    telemetry.addData("bR isBusy: ", prBot.backRight.isBusy());
                case "bL":
                    telemetry.addData("bL getCurPos: ", prBot.backLeft.getCurrentPosition());
                    telemetry.addData("bL getTarPos: ", prBot.backLeft.getTargetPosition());
                    telemetry.addData("bL isBusy: ", prBot.backLeft.isBusy());
            }
        }
        /*
        telemetry.addData("fR isBusy: ", prBot.frontRight.isBusy());
        telemetry.addData("fL isBusy: ", prBot.frontLeft.isBusy());
        telemetry.addData("bR isBusy: ", prBot.backRight.isBusy());
        telemetry.addData("bL isBusy: ", prBot.backLeft.isBusy());
         */
        /*
        telemetry.addData("fR getCurPos: ", prBot.frontRight.getCurrentPosition());
        telemetry.addData("fL getCurPos: ", prBot.frontLeft.getCurrentPosition());
        telemetry.addData("bR getCurPos: ", prBot.backRight.getCurrentPosition());
        telemetry.addData("bL getCurPos: ", prBot.backLeft.getCurrentPosition());
         */
        telemetry.addData("fR getPow: ", prBot.frontRight.getPower());
        telemetry.addData("fL getPow: ", prBot.frontLeft.getPower());
        telemetry.addData("bR getPow: ", prBot.backRight.getPower());
        telemetry.addData("bL getPow: ", prBot.backLeft.getPower());
        telemetry.addData("fR getMode: ", prBot.frontRight.getMode());
        telemetry.addData("fL getMode: ", prBot.frontLeft.getMode());
        telemetry.addData("bR getMode: ", prBot.backRight.getMode());
        telemetry.addData("bL getMode: ", prBot.backLeft.getMode());
        telemetry.addData("runtime: ", getRuntime());
        updateTelemetry(telemetry);
        telemetry.update();
    }
}
