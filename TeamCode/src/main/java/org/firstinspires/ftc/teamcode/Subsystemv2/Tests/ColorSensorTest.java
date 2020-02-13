package org.firstinspires.ftc.teamcode.Subsystemv2.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsystems.vision.SensorColor;

public class ColorSensorTest extends LinearOpMode {
    String block;
    @Override
    public void runOpMode() throws InterruptedException {
        SensorColor colorSensor = new SensorColor(hardwareMap.colorSensor.get("sensor_color"));
        waitForStart();
        while (opModeIsActive()) {
            if (colorSensor.isSkystone()) {
                block = "SkyStone";
            } else if (colorSensor.isBlock()) {
                block = "Block";
            } else {
                block = "Nothing";
            }
            telemetry.addData("What can I see", block);
        }
    }
}
