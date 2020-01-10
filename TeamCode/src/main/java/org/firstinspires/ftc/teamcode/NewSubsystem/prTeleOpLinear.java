package org.firstinspires.ftc.teamcode.NewSubsystem;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "TeleOp Linear", group = "Subsystem")
public class prTeleOpLinear extends LinearOpMode {
    Robot prbot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap);
        prbot.telem.setTelemetry(telemetry);
        prbot.drive.setEnc(false);
        prbot.drive.setBrake(false);
        prbot.drive.setMaxPower(1);
        prbot.intake.setMaxPower(1);
        prbot.lift.setMaxPower(.8);
        prbot.pivot.setMaxPower(.5);

        waitForStart();

        while (opModeIsActive()) {

            // Base

            if (Math.abs(gamepad1.left_stick_y) > .1){
                prbot.drive.moveLeftSide(gamepad1.left_stick_y);
            } else {
                prbot.drive.stopLeftSide();
            }

            if (Math.abs(gamepad1.right_stick_y) > .1){
                prbot.drive.moveRightSide(gamepad1.right_stick_y);
            } else {
                prbot.drive.stopRightSide();
            }

            if (gamepad1.dpad_left){
                prbot.drive.strafeLeft();
            } else if (gamepad1.dpad_right){
                prbot.drive.strafeRight();
            } else if (gamepad1.dpad_up){
                prbot.drive.forward();
            } else if (gamepad1.dpad_down){
                prbot.drive.backward();
            } else {
                prbot.drive.stop();
            }

            // Lift

            if (gamepad2.a){
                prbot.lift.up();
            } else if (gamepad2.y){
                prbot.lift.down();
            } else {
                prbot.lift.stop();
            }

            // Intake

            if (gamepad2.right_bumper){
                prbot.intake.close();
            } else if (gamepad2.left_bumper){
                prbot.intake.open();
            } else {
                prbot.intake.stop();
            }

            // Pivot

            if (gamepad2.dpad_up){
                prbot.pivot.up();
            } else if (gamepad2.dpad_down){
                prbot.pivot.down();
            } else {
                prbot.pivot.stop();
            }

            // Telemetry

            //prbot.telem.addAll();
            //prbot.telem.update();
        }

    }
}
