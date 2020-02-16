package org.firstinspires.ftc.teamcode.Subsystemv2.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Date: 2/14/20
 * Author: Jacob Marinas
 * Test program for checking toggle logic
 */
@TeleOp (name = "ToggleTest", group = "Test")
public class ToggleTest extends LinearOpMode {
    boolean currentState, previousState, state;
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        while (opModeIsActive()) {
            currentState = gamepad1.x;
            if (currentState && (currentState != previousState)) {
                state = !state;
            }
            telemetry.addData("Toggle", state);
            telemetry.update();
        }
    }
}
