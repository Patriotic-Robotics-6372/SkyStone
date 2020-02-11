package org.firstinspires.ftc.teamcode.Subsystemv1.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystemv1.Subsystems.Robot;

/**
 * author: Jacob Marinas
 * date: 1/8/20
 * desc: Teleop using subsystems.
 */

@TeleOp(name = "TeleOp 1P", group = "Subsystem")
public class prTeleOpSolo extends OpMode {
    private Robot prbot = new Robot();

    @Override
    public void init() {
        prbot.init(hardwareMap);
        prbot.telem.setTelemetry(telemetry);
        prbot.drive.setEnc(false);
        prbot.drive.setBrake(false);
        prbot.drive.setMaxPower(1);
        prbot.intake.setMaxPower(1);
        prbot.lift.setMaxPower(.8);
        prbot.pivot.setMaxPower(.5);
    }

    @Override
    public void loop() {

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
        } else {
            prbot.drive.stop();
        }

        // Lift

        if (gamepad1.a){
            prbot.lift.up();
        } else if (gamepad1.y){
            prbot.lift.down();
        } else {
            prbot.lift.stop();
        }

        // Intake

        if (gamepad1.right_bumper){
            prbot.intake.close();
        } else if (gamepad1.left_bumper){
            prbot.intake.open();
        } else {
            prbot.intake.stop();
        }

        // Pivot

        if (gamepad1.dpad_up){
            prbot.pivot.up();
        } else if (gamepad1.dpad_down){
            prbot.pivot.down();
        } else {
            prbot.pivot.stop();
        }

        // Telemetry

        //prbot.telem.addAll();
        //prbot.telem.update();
    }
}