package org.firstinspires.ftc.teamcode.CuttingEdge.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.CuttingEdge.Hardware.PRRobotv3;
import org.firstinspires.ftc.teamcode.Stable.Hardware.PRRobot;

@Disabled
@Autonomous (name = "auto redSquare11")
public class redSquare11 extends LinearOpMode {
    PRRobotv3 prBot = new PRRobotv3("stable");
    @Override
    public void runOpMode() throws InterruptedException {
        prBot.init(hardwareMap);
        prBot.useEnc(false);
        prBot.useBrake(true);
        prBot.setTelemetry(telemetry);
        waitForStart();
        //telemetry.update();
        prBot.move(.6, .6, PRRobotv3.Status.FORWARDS);
        telemetry.update();
        sleep(3000);
        prBot.pivotIntake(PRRobotv3.Status.NEUTRAL);
        telemetry.update();
        sleep(3000);
        telemetry.update();
    }
}
