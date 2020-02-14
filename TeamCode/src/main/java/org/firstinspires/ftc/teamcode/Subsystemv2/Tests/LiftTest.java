package org.firstinspires.ftc.teamcode.Subsystemv2.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.lift.Lift;


@TeleOp (name = "LiftTest", group = "Test")
public class LiftTest extends LinearOpMode {
    boolean toggle = false;
    boolean toggle2 = true;
    @Override
    public void runOpMode() throws InterruptedException {
        Lift lift = new Lift(hardwareMap.dcMotor.get("lift"));
        lift.setMaxPower(.5);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.left_bumper && toggle2) {
                lift.updateLevel();
                toggle = true;
                toggle2 = false;
            }
            if (!gamepad1.left_bumper) {
                toggle = false;
            }
            if (!toggle) {
                toggle2 = true;
            }
            if (gamepad1.dpad_up) {
                lift.increaseLevel();
            }
            if (gamepad1.dpad_down) {
                lift.decreaseLevel();
            }
            if (gamepad1.a) {
                lift.setLevel(1);
            }
            if (gamepad1.b) {
                lift.setLevel(2);
            }
            if (gamepad1.x) {
                lift.setLevel(3);
            }
            if (gamepad1.y) {
                lift.setLevel(4);
            }
            if (gamepad1.dpad_left) {
                lift.setLevel(0);
            }
            if (gamepad1.dpad_right) {
                lift.setLevel(4);
            }
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
        }
    }
}
