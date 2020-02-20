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

/**
 * Date: 2/16/20
 * Author: Jacob Marinas
 * Telemetry statements can be called for each of the subsystems here.
 */
public class Telem {

    Robot robot;
    Drivetrain drive;
    IMU imu;
    Intake intake;
    Lift lift;
    Pivot pivot;
    SensorColor colorSensor;
    Controller gp1;
    Controller gp2;
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

    /**
     * Telemetry for drivetrain
     * | Status
     * | speedPercentage
     * | power of each motor
     */
    public void addDrivetrain() {
        telem.addData("drivetrainStatus", drive.getStatus());
        telem.addData("speedPercentage", drive.getSpeedPercentage());
        telem.addData("frontLeft", drive.getFrontLeft().getPower());
        telem.addData("frontRight", drive.getFrontRight().getPower());
        telem.addData("backLeft", drive.getBackLeft().getPower());
        telem.addData("backRight", drive.getBackRight().getPower());
    }

    /**
     * Telemetry for imu
     * | Angles for yaw, pitch, roll
     */
    public void addIMU() {
        imu.updateAngles();
        telem.addLine()
                .addData("Heading", imu.getFirstAngle())
                .addData("Roll", imu.getSecondAngle())
                .addData("Pitch", imu.getThirdAngle());
    }

    /**
     * Telemetry for gamepad1
     */
    public void addGamepad1() {

    }

    /**
     * Telemetry for gamepad2
     */
    public void addGamepad2() {

    }

    /**
     * Telemetry for intake
     * | Status
     * | power of each motor
     */
    public void addIntake() {
        telem.addData("intakeStatus", intake.getStatus());
        telem.addData("leftPinchPower", intake.getLeftPinch().getPower());
        telem.addData("rightPinchPower", intake.getRightPinch().getPower());
    }

    /**
     * Telemetry for lift
     * | Status
     * | Current Level
     * | TickGoal
     */
    public void addLift() {
        telem.addData("liftStatus", lift.getStatus());
        telem.addData("Level", lift.getCurrentLevel());
        telem.addData("TickGoal", lift.getTickGoal());
        telem.addData("CurrentPos", lift.getLift().getCurrentPosition());
        telem.addData("CurrentTick", lift.getCurrentTick());
    }

    /**
     * Telemetry for pivot
     * | Status
     */
    public void addPivot() {
        telem.addData("pivotStatus", pivot.getStatus());
    }

    /**
     * Telemetry for color sensor
     * | Checks if seeing Skystone, Block, Nothing
     * | RGB values
     * | Alpha
     * | Hue
     */
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

    /**
     * Telemetry for all subsystems
     */
    public void addAll() {
        addDrivetrain();
        addIMU();
        //addGamepad1();
        //addGamepad2();
        addIntake();
        addLift();
        addPivot();
        //addColorSensor();
    }

    /**
     * Updates telemetry onto phone
     */
    public void update() {
        telem.update();
    }
}
