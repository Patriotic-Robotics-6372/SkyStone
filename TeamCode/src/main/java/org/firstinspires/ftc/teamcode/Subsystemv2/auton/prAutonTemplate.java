package org.firstinspires.ftc.teamcode.Subsystemv2.auton;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Robot;

/**
 * Date: 2/14/20
 * Author: Jacob Marinas
 * Autonomous template
 */
public class prAutonTemplate extends LinearOpMode {
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
