package org.firstinspires.ftc.teamcode.Subsystemv2.subsys.telemetry;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Robot;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.imu.IMU;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.input.Controller;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.intake.Intake;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.lift.Lift;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.pivot.Pivot;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.vision.SensorColor;

public class Telem {

    Robot robot;
    Drivetrain drive;
    IMU imu;
    Intake intake;
    Lift lift;
    Pivot pivot;
    SensorColor colorSensor;
    Controller gamepad_1;
    Controller gamepad_2;
    Telemetry telem;


    public Telem(Robot robot, Telemetry telem) {
        this.robot = robot;
        this.telem = telem;
        drive = robot.getDrivetrain();
        imu = robot.getIMU();
        intake = robot.getIntake();
        lift = robot.getLift();
        pivot = robot.getPivot();
        colorSensor = robot.getColorSensor();
    }

    public Telem(Drivetrain drive, Telemetry telem) {
        this.drive = drive;
        this.telem = telem;
    }

    public Telem(IMU imu, Telemetry telem) {
        this.imu = imu;
        this.telem = telem;
    }

    public Telem(Intake intake, Telemetry telem) {
        this.intake = intake;
        this.telem = telem;
    }

    public Telem(Lift lift, Telemetry telem) {
        this.lift = lift;
        this.telem = telem;
    }

    public Telem(Pivot pivot, Telemetry telem) {
        this.pivot = pivot;
        this.telem = telem;
    }

    public Telem(SensorColor colorSensor, Telemetry telem) {
        this.colorSensor = colorSensor;
        this.telem = telem;
    }

    public void addDrivetrain() {
        telem.addData("Status", drive.getStatus());
    }

    public void addIMU() {
        robot.getIMU().updateAngles();
        telem.addLine()
                .addData("Heading", robot.getIMU().getFirstAngle())
                .addData("Roll", robot.getIMU().getSecondAngle())
                .addData("Pitch", robot.getIMU().getThirdAngle());
    }

    public void addGamepad1() {

    }

    public void addGamepad2() {

    }

    public void addIntake() {
        telem.addData("Status", intake.getStatus());
    }

    public void addLift() {
        telem.addData("Status", lift.getStatus());
        telem.addData("Level", lift.getCurrentLevel());
        telem.addData("TickGoal", lift.getTickGoal());
    }

    public void addPivot() {
        telem.addData("Status", pivot.getStatus());
    }

    public void addColorSensor() {
        if (colorSensor.isSkystone()) {
            telem.addData("What I am seeing", "Skystone");
        } else if (colorSensor.isBlock()) {
            telem.addData("What I am seeing", "Block");
        } else if (colorSensor.isNothing()) {
            telem.addData("What I am seeing", "Nothing");
        }
        telem.addLine()
                .addData("Red", colorSensor.getRed())
                .addData("Green", colorSensor.getGreen())
                .addData("Blue", colorSensor.getBlue());
        telem.addData("Alpha", colorSensor.getAlpha());
        telem.addData("Hue", colorSensor.getHue());
        telem.addLine()
                .addData("isSkystone", colorSensor.isSkystone())
                .addData("isBlock", colorSensor.isBlock())
                .addData("isNothing", colorSensor.isNothing());
    }

    public void addAll() {
        addDrivetrain();
        addIMU();
        addGamepad1();
        addGamepad2();
        addIntake();
        addLift();
        addPivot();
        addColorSensor();
    }

    public void update() {
        telem.update();
    }
}
