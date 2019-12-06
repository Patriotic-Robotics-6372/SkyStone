package org.firstinspires.ftc.teamcode.CuttingEdge.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
