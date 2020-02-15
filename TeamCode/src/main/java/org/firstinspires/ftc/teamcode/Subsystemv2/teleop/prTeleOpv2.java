package org.firstinspires.ftc.teamcode.Subsystemv2.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Robot;

@TeleOp (name = "TeleOpv2")
public class prTeleOpv2 extends OpMode {
    Robot prbot = new Robot();
    @Override
    public void init() {
        prbot.init(hardwareMap, telemetry);
        prbot.setMode(Constants.Status.NORMAL);
    }

    @Override
    public void loop() {

        if (gamepad1.left_bumper) {
            prbot.getDrivetrain().throttleOn();
        } else {
            prbot.getDrivetrain().throttleOff();
        }

        if (Math.abs(gamepad1.left_stick_y) > .1 || Math.abs(gamepad1.right_stick_y) > .1) {
            prbot.getDrivetrain().setLeftSide(gamepad1.left_stick_y);
            prbot.getDrivetrain().setRightSide(gamepad1.right_stick_y);
        } else {
            prbot.getDrivetrain().stop();
        }

        if (gamepad1.dpad_left){
            prbot.getDrivetrain().strafeLeft();
        } else if (gamepad1.dpad_right){
            prbot.getDrivetrain().strafeRight();
        } else if (gamepad1.dpad_up){
            prbot.getDrivetrain().forward();
        } else if (gamepad1.dpad_down){
            prbot.getDrivetrain().backward();
        } else {
            prbot.getDrivetrain().stop();
        }

        // Lift

        if (gamepad2.a){
            prbot.getLift().up();
        } else if (gamepad2.y){
            prbot.getLift().down();
        } else {
            prbot.getLift().stop();
        }

        // Intake

        if (gamepad2.right_bumper){
            prbot.getIntake().close();
        } else if (gamepad2.left_bumper){
            prbot.getIntake().open();
        } else {
            prbot.getIntake().stop();
        }

        // Pivot

        if (gamepad2.dpad_down){
            prbot.getPivot().up();
        } else if (gamepad2.dpad_up){
            prbot.getPivot().down();
        } else {
            prbot.getPivot().stop();
        }

    }
}
