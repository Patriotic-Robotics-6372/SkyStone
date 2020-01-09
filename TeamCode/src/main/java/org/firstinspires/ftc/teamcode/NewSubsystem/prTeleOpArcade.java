package org.firstinspires.ftc.teamcode.NewSubsystem;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

/**
 * author: Jacob Marinas
 * date: 1/8/20
 * desc: Teleop using subsystems.
 */

@TeleOp(name = "TeleOp Arcade")
public class prTeleOpArcade extends OpMode {
    private Robot prbot = new Robot();

    double xValue, yValue, leftPower, rightPower;

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

        if (Math.abs(gamepad1.left_stick_y) > .1 || Math.abs(gamepad1.left_stick_x) > .1) {
            yValue = gamepad1.left_stick_y * -1;
            xValue = gamepad1.left_stick_x * -1;
            leftPower = yValue - xValue;
            rightPower = yValue + xValue;
            prbot.drive.moveLeftSide(Range.clip(leftPower, -1.0, 1.0));
            prbot.drive.moveRightSide(Range.clip(rightPower, -1.0, 1.0));
        }

        if (gamepad1.dpad_left){
            prbot.drive.strafeLeft();
        } else if (gamepad1.dpad_right){
            prbot.drive.strafeRight();
        } else if (gamepad1.dpad_up){
            prbot.drive.forward();
        } else if (gamepad1.dpad_down){
            prbot.drive.backwards();
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
