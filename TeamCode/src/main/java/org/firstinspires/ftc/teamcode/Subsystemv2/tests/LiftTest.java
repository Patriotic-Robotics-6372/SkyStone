package org.firstinspires.ftc.teamcode.Subsystemv2.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.input.Controller;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.lift.Lift;

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
        lift.setMaxPower(.15);
        lift.setTelemetry(telemetry);
        Controller gp1 = new Controller();
        waitForStart();
        while (opModeIsActive()) {
            gp1.updatePrevious();
            gp1.getA().setState(gamepad1.a);
            gp1.getY().setState(gamepad1.y);
            if (gp1.getY().isPressed()) {
                lift.increaseLevel();
            } else if (gp1.getA().isPressed()) {
                lift.decreaseLevel();
            }
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
        }
    }
}
