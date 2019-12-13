package org.firstinspires.ftc.teamcode.CuttingEdge.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.CuttingEdge.Hardware.PRRobotv2;
import org.firstinspires.ftc.teamcode.Stable.Hardware.PRRobot;

/*
    author: Jacob Marinas
    date: 12/12/19
    desc: testing encoders from class PRRobotv2
 */
@Autonomous (name = "encoderTestv2")
public class encoderTestv2 extends LinearOpMode {
    PRRobotv2 prBot = new PRRobotv2();
    double TICKS_PER_IN = 1120 / 4 * Math.PI;
    int tickGoal, wait;

    @Override
    public void runOpMode() throws InterruptedException {
        prBot.init(hardwareMap);
        prBot.useBrake(false);
        prBot.useEnc(true);
        //prBot.rightPivot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //prBot.leftPivot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        prBot.rightPivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        prBot.leftPivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        wait = 500;
        waitForStart();
        //prBot.leftPivot.setPower(.5);
        //prBot.rightPivot.setPower(.5);
        prBot.encoderTelemetry(telemetry, "pivot", false);
        sleep(wait);
        //encoder(2, .5, .5, "pivot");
        //encoder(6,.5,.5,"all");
        prBot.encoder(6, .5, .5, "all");
        prBot.encoder(18, .5, .5, "all");
        prBot.encoder(18, 1, 1, "all");
        prBot.encoder(6, .5, .5, "fR");
        prBot.encoder(6, .5, .5, "fL");
        prBot.encoder(6, .5, .5, "bR");
        prBot.encoder(6, .5, .5, "bL");
        //driveDistance(6,.5,.5,"all");
        //encoder(2, .5, .5, "pivot");
    }
}

