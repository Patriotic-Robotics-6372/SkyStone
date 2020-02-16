package org.firstinspires.ftc.teamcode.Subsystemv2.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.input.Controller;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.input.DigitalButton;

/**
 * Date: 2/14/20
 * Author: Jacob Marinas
 * Test program to check toggle logic through the Controller class
 */
@TeleOp (name = "ToggleTestController", group = "Test")
public class ToggleTestController extends LinearOpMode {
    boolean press, hold, release;
    @Override
    public void runOpMode() throws InterruptedException {
        Controller gp1 = new Controller();
        waitForStart();
        press = false;
        hold = false;
        release = false;
        while (opModeIsActive()) {
            gp1.getX().previous();
            gp1.getX().setState(gamepad1.x);
            if (gp1.getX().isPressed()) {
                press = !press;
            }
            if (gp1.getX().isHeld()) {
                hold = true;
            } else {
                hold = false;
            }
            if (gp1.getX().isReleased()) {
                release = !release;
            }
            telemetry.addData("Press", press);
            telemetry.addData("Hold", hold);
            telemetry.addData("Release", release);
            telemetry.addData("CurrentState", gp1.getX().getCurrentState());
            telemetry.addData("PreviousState", gp1.getX().getPreviousState());
            telemetry.update();
        }
    }
}
