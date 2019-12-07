package org.firstinspires.ftc.teamcode.Stable.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
/*
    author: Jacob Marinas
    date: 12/5/19
    desc: simple park that goes under bridge from side
 */

import org.firstinspires.ftc.teamcode.CuttingEdge.Hardware.PRRobot;
@Autonomous (name = "parkShort")
public class parkShort extends LinearOpMode {
    PRRobot prBot = new PRRobot("stable");
    @Override
    public void runOpMode() throws InterruptedException {
        prBot.init(hardwareMap);

        waitForStart();

        prBot.move(.6, .6, PRRobot.Status.FORWARDS);
        sleep(800);
        //prBot.robotStatus(telemetry);

    }
}
