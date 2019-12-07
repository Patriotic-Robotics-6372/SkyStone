package org.firstinspires.ftc.teamcode.CuttingEdge.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.CuttingEdge.Hardware.PRRobot;

@Disabled
@Autonomous (name = "Auto redSquare5")
public class redSquare5 extends LinearOpMode {
    PRRobot prBot = new PRRobot("stable");
    @Override
    public void runOpMode() throws InterruptedException {
        prBot.init(hardwareMap);

        waitForStart();

        //prBot.move(.3, .3, .3, PRRobot.Status.FORWARDS);
        prBot.robotStatus(telemetry);

    }
}
