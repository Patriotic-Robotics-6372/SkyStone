package org.firstinspires.ftc.teamcode.Subsystemv2.subsys;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.imu.IMU;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.input.Controller;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.intake.Intake;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.lift.Lift;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.pivot.Pivot;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.telemetry.Telem;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.vision.SensorColor;

/**
 * Date: 2/15/20
 * Author: Jacob Marinas
 * The container class to hold all subsystems.
 */
public class Robot {

    private Drivetrain drive;
    private IMU imu;
    private Intake intake;
    private Lift lift;
    private Pivot pivot;
    private SensorColor colorSensor;
    private Telem telem;
    private Controller gp1;
    private Controller gp2;
    private Telemetry telemetry;

    public void init(HardwareMap hwMap, Telemetry telemetry) {
        this.drive = new Drivetrain(hwMap.dcMotor.get("frontLeft"), hwMap.dcMotor.get("frontRight"), hwMap.dcMotor.get("backLeft"), hwMap.dcMotor.get("backRight"));
        this.imu = new IMU(hwMap.get(BNO055IMU.class, "imu 1"));
        this.intake = new Intake(hwMap.crservo.get("leftPinch"), hwMap.crservo.get("rightPinch"));
        this.lift = new Lift(hwMap.dcMotor.get("lift"));
        this.pivot = new Pivot(hwMap.dcMotor.get("leftPivot"), hwMap.dcMotor.get("rightPivot"));
        this.colorSensor = new SensorColor(hwMap.colorSensor.get("sensor_color"));
        this.telem = new Telem(this, telemetry);
        this.gp1 = new Controller();
        this.gp2 = new Controller();
        this.telemetry = telemetry;
    }

    /**
     * Sets parameters for each of the subsystems using presets
     * @param mode to set
     */
    public void setMode(Constants.Status mode) {
        switch (mode) {
            case NORMAL:
                drive.setMaxPower(1);
                drive.setSpeedPercentage(1);
                drive.useBrake(false);
                drive.useEncoders(false);
                intake.setMaxPower(1);
                lift.setMaxPower(.8);
                lift.useBrake(true);
                lift.useEncoders(false);
                pivot.setMaxPower(.5);
                break;
            case AUTO:
                drive.setMaxPower(1);
                drive.setSpeedPercentage(1);
                drive.useBrake(true);
                drive.useEncoders(true);
                intake.setMaxPower(1);
                lift.setMaxPower(.8);
                lift.useBrake(true);
                lift.useEncoders(true);
                pivot.setMaxPower(.5);
                break;
        }
    }

    /**
     * @return drivetrain subsystem
     */
    public Drivetrain getDrivetrain() {
        return drive;
    }

    /**
     * @return imu subsystem
     */
    public IMU getIMU() {
        return imu;
    }

    /**
     * @return intake subsytem
     */
    public Intake getIntake() {
        return intake;
    }

    /**
     * @return lift subsystem
     */
    public Lift getLift() {
        return lift;
    }

    /**
     * @return pivot subsystem
     */
    public Pivot getPivot() {
        return pivot;
    }

    /**
     * @return color sensor subsystem
     */
    public SensorColor getColorSensor() {
        return colorSensor;
    }

    /**
     * @return telem subsystem
     */
    public Telem getTelem() {
        return telem;
    }

    /**
     * @return gamepad1 controller class
     */
    public Controller getGp1() {
        return gp1;
    }

    /**
     * @return gamepad2 controller class
     */
    public Controller getGp2() {
        return gp2;
    }

    /**
     * @return telemetry used in main program
     */
    public Telemetry getTelemetry() {
        return telemetry;
    }
}
