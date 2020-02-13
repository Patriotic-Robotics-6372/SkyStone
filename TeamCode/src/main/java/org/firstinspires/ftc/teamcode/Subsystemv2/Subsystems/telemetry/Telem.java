package org.firstinspires.ftc.teamcode.Subsystemv2.subsystems.telemetry;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsystems.Robot;

public class Telem {

    Robot robot;
    Telemetry telem;

    public Telem(Robot robot) {
        this.robot = robot;
        telem = robot.getTelemetry();
    }

    public void addColorSensor() {
        telem.addData("Red", robot.getColorSensor().getRed());
        telem.addData("Green", robot.getColorSensor().getGreen());
        telem.addData("Blue", robot.getColorSensor().getBlue());
        telem.addData("Alpha", robot.getColorSensor().getAlpha());
    }
}
