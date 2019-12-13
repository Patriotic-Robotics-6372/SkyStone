package org.firstinspires.ftc.teamcode.Stable.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Stable.Hardware.PRRobot;

@TeleOp (name = "servoTest")
public class servoTest extends OpMode {
    PRRobot prBot = new PRRobot(1, .8, 1, .8, 1, 1);
    @Override
    public void init() {
        prBot.init(hardwareMap);
        prBot.useBrake(false);
        prBot.useEnc(false);
    }
    @Override
    public void loop() {
        if (gamepad1.x) {
            prBot.leftPinch.setPower(prBot.open);
            prBot.rightPinch.setPower(-prBot.open);
        } else if (gamepad1.y) {
            prBot.leftPinch.setPower(-prBot.close);
            prBot.rightPinch.setPower(prBot.close);
        } else {
            prBot.leftPinch.setPower(prBot.STOP);
            prBot.rightPinch.setPower(prBot.STOP);
        }
        telemetry.addData("leftPinch pow: ", prBot.leftPinch.getPower());
        telemetry.addData("rightPinch pow: ", prBot.rightPinch.getPower());
        updateTelemetry(telemetry);
        telemetry.update();
    }
}
