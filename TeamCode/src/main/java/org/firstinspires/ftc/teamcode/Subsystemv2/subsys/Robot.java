package org.firstinspires.ftc.teamcode.Subsystemv2.subsys;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.imu.IMU;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.intake.Intake;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.lift.Lift;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.pivot.Pivot;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.telemetry.Telem;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.vision.SensorColor;

public class Robot {

    Drivetrain drive;
    IMU imu;
    Intake intake;
    Lift lift;
    Pivot pivot;
    SensorColor colorSensor;
    Telem telem;
    Telemetry telemetry;

    public void init(HardwareMap hwMap, Telemetry telemetry) {
        this.drive = new Drivetrain(hwMap.dcMotor.get("frontLeft"), hwMap.dcMotor.get("frontRight"), hwMap.dcMotor.get("backLeft"), hwMap.dcMotor.get("backRight"));
        this.imu = new IMU(hwMap.get(BNO055IMU.class, "imu"));
        this.intake = new Intake(hwMap.crservo.get("leftPinch"), hwMap.crservo.get("rightPinch"));
        this.lift = new Lift(hwMap.dcMotor.get("lift"));
        this.pivot = new Pivot(hwMap.dcMotor.get("leftPivot"), hwMap.dcMotor.get("rightPivot"));
        this.colorSensor = new SensorColor(hwMap.colorSensor.get("sensor_color"));
        this.telem = new Telem(this);
        this.telemetry = telemetry;
    }

    public void setMode(String mode) {
        switch (mode) {
            case "normal":
                break;
            case "speed":
                break;
        }
    }

    public Drivetrain getDrivetrain() {
        return drive;
    }

    public IMU getImu() {
        return imu;
    }

    public Intake getIntake() {
        return intake;
    }

    public Lift getLift() {
        return lift;
    }

    public Pivot getPivot() {
        return pivot;
    }

    public SensorColor getColorSensor() {
        return colorSensor;
    }

    public Telemetry getTelemetry() {
        return telemetry;
    }
}
