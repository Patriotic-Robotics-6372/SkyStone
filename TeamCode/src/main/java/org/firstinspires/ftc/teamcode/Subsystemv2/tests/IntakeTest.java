package org.firstinspires.ftc.teamcode.Subsystemv2.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.input.Controller;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.intake.Intake;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.telemetry.Telem;

/**
 * Date: 2/14/20
 * Author: Jacob Marinas
 * Test program to check intake functionality and data
 */
@TeleOp (name = "IntakeTest", group = "Test")
public class IntakeTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Intake intake = new Intake(hardwareMap.crservo.get("leftPinch"), hardwareMap.crservo.get("rightPinch"));
        Telem telem = new Telem(intake, telemetry);
        Controller gp1 = new Controller();
        intake.setMaxPower(1);
        waitForStart();
        while (opModeIsActive()) {
            gp1.getX().previous();
            gp1.getX().setState(gamepad1.x);
            gp1.getX().isPressed();
            if (gp1.getX().getToggle()) {
                intake.open();
            } else {
                intake.close();
            }
            telemetry.addData("xToggle", gp1.getX().getToggle());
            telem.addIntake();
            telem.update();
        }
    }
}
