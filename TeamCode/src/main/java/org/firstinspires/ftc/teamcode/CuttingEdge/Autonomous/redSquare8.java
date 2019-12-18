package org.firstinspires.ftc.teamcode.CuttingEdge.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Stable.Hardware.PRRobot;

@Disabled
@Autonomous (name = "Auto redSquare8")
public class redSquare8 extends LinearOpMode {
    PRRobot prBot = new PRRobot("stable");
    @Override
    public void runOpMode() throws InterruptedException {
        prBot.init(hardwareMap);
        prBot.useBrake(true);
        prBot.useEnc(true);

        waitForStart();

        encoder(6, .5, .5, "all");
        encoder(18, .5, .5, "all");
        encoder(18, 1, 1, "all");
        sleep(3000);

    }

    public void encoder(double inches, double rightPower, double leftPower, String motor) {
        int wait = 250;
        prBot.tickGoal = (int) (prBot.TICKS_PER_IN * inches);
        switch (motor) {
            case "fR":
                prBot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                prBot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                encoderTelemetry(motor, false);
                sleep(wait);
                prBot.frontRight.setTargetPosition(prBot.tickGoal);
                prBot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                encoderTelemetry(motor, false);
                sleep(wait);
                break;
            case "fL":
                prBot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                prBot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                encoderTelemetry(motor, false);
                sleep(wait);
                prBot.frontLeft.setTargetPosition(prBot.tickGoal);
                prBot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                encoderTelemetry(motor, false);
                sleep(wait);
                break;
            case "bR":
                prBot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                prBot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                encoderTelemetry(motor, false);
                sleep(wait);
                prBot.backRight.setTargetPosition(prBot.tickGoal);
                prBot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                encoderTelemetry(motor, false);
                sleep(wait);
                break;
            case "bL":
                prBot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                prBot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                encoderTelemetry(motor, false);
                sleep(wait);
                prBot.backLeft.setTargetPosition(prBot.tickGoal);
                prBot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                encoderTelemetry(motor, false);
                sleep(wait);
                break;
            case "all":
                prBot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                prBot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                prBot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                prBot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                encoderTelemetry(motor, true);
                sleep(wait);
                prBot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                prBot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                prBot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                prBot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                encoderTelemetry(motor, true);
                sleep(wait);
                prBot.frontRight.setTargetPosition(prBot.tickGoal);
                prBot.frontLeft.setTargetPosition(prBot.tickGoal);
                prBot.backRight.setTargetPosition(prBot.tickGoal);
                prBot.backLeft.setTargetPosition(prBot.tickGoal);
                prBot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                prBot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                prBot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                prBot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                encoderTelemetry(motor, true);
                sleep(wait);
                break;
            case "pivot":
                prBot.leftPivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                prBot.rightPivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                encoderTelemetry(motor, true);
                sleep(wait);
                prBot.leftPivot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                prBot.rightPivot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                encoderTelemetry(motor, true);
                sleep(wait);
                prBot.leftPivot.setTargetPosition(prBot.tickGoal);
                prBot.rightPivot.setTargetPosition(prBot.tickGoal);
                prBot.leftPivot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                prBot.rightPivot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                encoderTelemetry(motor, true);
                sleep(wait);
        }
        encoderTelemetry(motor, true);
        sleep(wait);
        while (anyBusy() && opModeIsActive()) {
            switch (motor) {
                case "pivot":
                    prBot.leftPivot.setPower(leftPower);
                    prBot.rightPivot.setPower(rightPower);
                    telemetry.addData("pivots", "yes");
                    break;
                default:
                    prBot.frontRight.setPower(rightPower);
                    prBot.frontLeft.setPower(leftPower);
                    prBot.backRight.setPower(rightPower);
                    prBot.backLeft.setPower(leftPower);
            }
            encoderTelemetry(motor, true);
        }
        prBot.stop();
        prBot.leftPivot.setPower(0);
        prBot.rightPivot.setPower(0);
        encoderTelemetry(motor, true);
        sleep(wait);
        encoderTelemetry(motor, true);
        sleep(wait);
    }

    public void pivot(String status) {
        switch (status) {
            case "up":

        }
    }

    public boolean anyBusy(){
        if (prBot.frontRight.isBusy() || prBot.frontLeft.isBusy() || prBot.backRight.isBusy() || prBot.backLeft.isBusy() || prBot.leftPivot.isBusy() || prBot.rightPivot.isBusy()) {
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
                case "all":
                    telemetry.addData("fR getCurPos: ", prBot.frontRight.getCurrentPosition());
                    telemetry.addData("fL getCurPos: ", prBot.frontLeft.getCurrentPosition());
                    telemetry.addData("bR getCurPos: ", prBot.backRight.getCurrentPosition());
                    telemetry.addData("bL getCurPos: ", prBot.backLeft.getCurrentPosition());
                    telemetry.addData("fR getTarPos: ", prBot.frontRight.getTargetPosition());
                    telemetry.addData("fL getTarPos: ", prBot.frontLeft.getTargetPosition());
                    telemetry.addData("bR getTarPos: ", prBot.backRight.getTargetPosition());
                    telemetry.addData("bL getTarPos: ", prBot.backLeft.getTargetPosition());
                    telemetry.addData("fR isBusy: ", prBot.frontRight.isBusy());
                    telemetry.addData("fL isBusy: ", prBot.frontLeft.isBusy());
                    telemetry.addData("bR isBusy: ", prBot.backRight.isBusy());
                    telemetry.addData("bL isBusy: ", prBot.backLeft.isBusy());
                case "pivot":
                    telemetry.addData("lP getCurPos: ", prBot.leftPivot.getCurrentPosition());
                    telemetry.addData("rP getCurPos: ", prBot.rightPivot.getCurrentPosition());
                    telemetry.addData("lP getTarPos: ", prBot.leftPivot.getTargetPosition());
                    telemetry.addData("rP getTarPos: ", prBot.rightPivot.getTargetPosition());
                    telemetry.addData("lP isBusy: ", prBot.leftPivot.isBusy());
                    telemetry.addData("rP isBusy: ", prBot.rightPivot.isBusy());
            }
        }
        telemetry.addData("fR getPow: ", prBot.frontRight.getPower());
        telemetry.addData("fL getPow: ", prBot.frontLeft.getPower());
        telemetry.addData("bR getPow: ", prBot.backRight.getPower());
        telemetry.addData("bL getPow: ", prBot.backLeft.getPower());
        telemetry.addData("lP getPow: ", prBot.leftPivot.getPower());
        telemetry.addData("rP getPow: ", prBot.rightPivot.getPower());
        telemetry.addData("fR getMode: ", prBot.frontRight.getMode());
        telemetry.addData("fL getMode: ", prBot.frontLeft.getMode());
        telemetry.addData("bR getMode: ", prBot.backRight.getMode());
        telemetry.addData("bL getMode: ", prBot.backLeft.getMode());
        telemetry.addData("lP getMode: ", prBot.leftPivot.getMode());
        telemetry.addData("rP getMode: ", prBot.rightPivot.getMode());
        telemetry.addData("runtime: ", getRuntime());
        updateTelemetry(telemetry);
        telemetry.update();
    }
}
