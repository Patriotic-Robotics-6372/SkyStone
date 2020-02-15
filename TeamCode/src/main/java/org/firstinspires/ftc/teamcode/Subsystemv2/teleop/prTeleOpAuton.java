package org.firstinspires.ftc.teamcode.Subsystemv2.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Robot;

public class prTeleOpAuton extends LinearOpMode {
    Robot prbot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap, telemetry);
        prbot.setMode(Constants.Status.AUTO);
        waitForStart();
        while (opModeIsActive()) {

        }
    }
}
