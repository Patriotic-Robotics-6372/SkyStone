package org.firstinspires.ftc.teamcode.Deprecated.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Stable.Hardware.PRRobot;

@Disabled
@Autonomous (name = "Auto redSquare4")
public class redSquare4 extends LinearOpMode {
    PRRobot prBot = new PRRobot();
    @Override
    public void runOpMode() throws InterruptedException {
        prBot.init(hardwareMap);

        waitForStart();

        prBot.pivotIntake(PRRobot.Status.UP);
        prBot.pinch(PRRobot.Status.OPEN);

        prBot.move(.6, .6, PRRobot.Status.FORWARDS);
        sleep(1000);
        prBot.robotStatus(telemetry);

        prBot.move(.3, .3, PRRobot.Status.FORWARDS);
        prBot.pinch(PRRobot.Status.CLOSE);
        sleep(300);
        prBot.robotStatus(telemetry);

        prBot.move(.6, .6, PRRobot.Status.BACKWARDS);
        sleep(1200);

        prBot.pivotTurn(.3, .3, PRRobot.Status.LEFT);
        sleep(500);
    }
}
