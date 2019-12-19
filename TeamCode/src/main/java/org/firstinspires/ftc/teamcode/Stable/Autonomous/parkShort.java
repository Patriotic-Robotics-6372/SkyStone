package org.firstinspires.ftc.teamcode.Stable.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Stable.Hardware.PRRobot;
/*
    author: Jacob Marinas
    date: 12/5/19
    desc: simple park that goes under bridge from side
 */

@Autonomous (name = "parkShort", group = "Stable")
public class parkShort extends LinearOpMode {
    PRRobot prBot = new PRRobot("stable");
    @Override
    public void runOpMode() throws InterruptedException {
        prBot.init(hardwareMap);
        prBot.setTelemetry(telemetry);

        waitForStart();

        prBot.move(.6, .6, PRRobot.Status.FORWARDS);
        telemetry.update();
        sleep(3000);
        prBot.move(.6, .6, PRRobot.Status.BACKWARDS);
        telemetry.update();
        sleep(3000);
        prBot.move(.6, .6, PRRobot.Status.NEUTRAL);
        telemetry.update();
        sleep(3000);

    }
}
