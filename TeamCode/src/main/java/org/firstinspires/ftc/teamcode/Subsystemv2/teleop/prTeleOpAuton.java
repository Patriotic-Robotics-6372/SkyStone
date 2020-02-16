package org.firstinspires.ftc.teamcode.Subsystemv2.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Robot;

/**
 * Date: 2/16/20
 * Author: Jacob Marinas
 * Teleop that uses automation for the lift.
 */
public class prTeleOpAuton extends LinearOpMode {
    Robot prbot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap, telemetry);
        prbot.setMode(Constants.Status.AUTO);
        waitForStart();
        while (opModeIsActive()) {

            prbot.getGp1().getX().previous();
            prbot.getGp1().getX().setState(gamepad1.x);
            prbot.getGp1().getX().isPressed();


        }
    }
}
