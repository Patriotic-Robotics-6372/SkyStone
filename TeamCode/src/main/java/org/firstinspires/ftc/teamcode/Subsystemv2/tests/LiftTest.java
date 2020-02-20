package org.firstinspires.ftc.teamcode.Subsystemv2.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.input.Controller;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.lift.Lift;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.telemetry.Telem;

/**
 * Date: 2/14/20
 * Author: Jacob Marinas
 * Test program to check lift functionality and data. In particular, the lift levels.
 */
@TeleOp (name = "LiftTest", group = "Test")
public class LiftTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Lift lift = new Lift(hardwareMap.dcMotor.get("lift"));
        Telem telem = new Telem(lift, telemetry);
        lift.setMaxPower(1);
        lift.setTelemetry(telemetry);
        Controller gp1 = new Controller();
        waitForStart();
        while (opModeIsActive()) {
            gp1.updatePrevious();
            gp1.getDpadUp().setState(gamepad1.dpad_up);
            gp1.getDpadDown().setState(gamepad1.dpad_down);
            gp1.getDpadLeft().setState(gamepad1.dpad_left);
            gp1.getDpadRight().setState(gamepad1.dpad_right);
            if (gp1.getDpadUp().isPressed()) {
                lift.increaseLevel();
            } else if (gp1.getDpadDown().isPressed()) {
                lift.decreaseLevel();
            }
            if (gamepad1.dpad_left) {
                lift.setLevel(0);
            }
            if (gamepad1.dpad_right) {
                lift.setLevel(3);
            }

            lift.updateLevel();
            telem.addLift();
            telemetry.update();
        }
    }
}
