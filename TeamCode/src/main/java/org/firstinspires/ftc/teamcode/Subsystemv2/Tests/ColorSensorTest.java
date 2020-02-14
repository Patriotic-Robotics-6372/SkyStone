package org.firstinspires.ftc.teamcode.Subsystemv2.Tests;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.vision.SensorColor;

@TeleOp (name = "ColorSensorTest", group = "Test")
public class ColorSensorTest extends LinearOpMode {
    String block, test;

    @Override
    public void runOpMode() throws InterruptedException {
        SensorColor colorSensor = new SensorColor(hardwareMap.colorSensor.get("sensor_color"));
        float hsvValues[] = {0F,0F,0F};
        waitForStart();
        while (opModeIsActive()) {
            Color.RGBToHSV(colorSensor.getRed() * 8, colorSensor.getGreen() * 8, colorSensor.getBlue() * 8, hsvValues);
            if (gamepad1.y) {
                colorSensor.turnOnLED();
            }
            if (gamepad1.x) {
                colorSensor.turnOffLED();
            }
            if (colorSensor.isSkystone()) {
                block = "SkyStone";
            } else if (colorSensor.isBlock()) {
                block = "Block";
            } else {
                block = "Nothing";
            }
            if (colorSensor.getHue() < 50 && colorSensor.getHue() > 30) {
                test = "Block";
            } else {
                test = "No Block";
            }
            telemetry.addData("What can I see", block);
            telemetry.addData("Test", test);
            telemetry.addData("More R/G/B?", colorSensor.getColor());
            telemetry.addData("Red", colorSensor.getRed());
            telemetry.addData("Green", colorSensor.getGreen());
            telemetry.addData("Blue", colorSensor.getBlue());
            telemetry.addData("Hue", hsvValues[0]);
            telemetry.addData("Hue 2", colorSensor.getHue());
            telemetry.addData("Alpha", colorSensor.getAlpha());
            telemetry.addData("isSkystone", colorSensor.isSkystone());
            telemetry.addData("isBlock", colorSensor.isBlock());
            telemetry.addData("isNothing", colorSensor.isNothing());
            telemetry.update();
        }
    }
}
