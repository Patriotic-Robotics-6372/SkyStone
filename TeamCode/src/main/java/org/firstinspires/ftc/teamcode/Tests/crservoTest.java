package org.firstinspires.ftc.robotcontroller;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name="CRServo Test")
@Disabled
public class crservoTest extends OpMode {

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
        if (gamepad2.left_bumper) {
            rightPinch.setPosition(open);
            leftPinch.setPosition(close);

            telemetry.addData("servo", "open");
            updateTelemetry(telemetry);
            telemetry.update();
        }
        if (gamepad2.right_bumper) {
            rightPinch.setPosition(close);
            leftPinch.setPosition(open);

            telemetry.addData("servo", "close");
            updateTelemetry(telemetry);
            telemetry.update();
        }

    }
}
