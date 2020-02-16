package org.firstinspires.ftc.teamcode.Subsystemv2.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.input.DigitalButton;

/**
 * Date: 2/14/20
 * Author: Jacob Marinas
 * Test program to check toggle logic through the DigitalButton class
 */
@TeleOp (name = "ToggleTestButton", group = "Test")
public class ToggleTestButton extends LinearOpMode {
    boolean press, hold, release;
    @Override
    public void runOpMode() throws InterruptedException {
        DigitalButton buttonX = new DigitalButton();
        waitForStart();
        press = false;
        hold = false;
        release = false;
        while (opModeIsActive()) {
            buttonX.previous();
            buttonX.setState(gamepad1.x);
            if (buttonX.isPressed()) {
                press = !press;
            }
            if (buttonX.isHeld()) {
                hold = true;
            } else {
                hold = false;
            }
            if (buttonX.isReleased()) {
                release = !release;
            }
            telemetry.addData("Press", press);
            telemetry.addData("Hold", hold);
            telemetry.addData("Release", release);
            telemetry.addData("CurrentState", buttonX.getCurrentState());
            telemetry.addData("PreviousState", buttonX.getPreviousState());
            telemetry.update();
        }
    }
}
