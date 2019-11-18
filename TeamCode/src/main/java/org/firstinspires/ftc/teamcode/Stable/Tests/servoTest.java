package org.firstinspires.ftc.teamcode.Stable.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name="Servo Test")
@Disabled
public class servoTest extends OpMode {

    Servo rightPinch;
    Servo leftPinch;

    int close, open;
    @Override
    public void init() {
        rightPinch = hardwareMap.servo.get("rightPinch");
        leftPinch = hardwareMap.servo.get("leftPinch");
    }

    @Override
    public void loop() {
        open = 180;
        close = 0;
        if (gamepad2.x) {
            rightPinch.setPosition(open);
            leftPinch.setPosition(close);

            telemetry.addData("servo", "open");
            updateTelemetry(telemetry);
            telemetry.update();
        }
        if (gamepad2.y) {
            rightPinch.setPosition(close);
            leftPinch.setPosition(open);

            telemetry.addData("servo", "close");
            updateTelemetry(telemetry);
            telemetry.update();
        }

    }
}
