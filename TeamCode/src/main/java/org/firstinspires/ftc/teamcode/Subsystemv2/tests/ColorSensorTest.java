package org.firstinspires.ftc.teamcode.Subsystemv2.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.telemetry.Telem;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.vision.SensorColor;

@TeleOp (name = "ColorSensorTest", group = "Test")
public class ColorSensorTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SensorColor colorSensor = new SensorColor(hardwareMap.colorSensor.get("sensor_color"));
        Telem telem = new Telem(colorSensor, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            telem.addColorSensor();
            telem.update();
        }
    }
}
