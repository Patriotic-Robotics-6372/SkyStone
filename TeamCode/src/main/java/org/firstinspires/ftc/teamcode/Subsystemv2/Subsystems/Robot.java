package org.firstinspires.ftc.teamcode.Subsystemv2.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystemv2.Subsystems.drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystemv2.Subsystems.imu.IMU;
import org.firstinspires.ftc.teamcode.Subsystemv2.Subsystems.intake.Intake;
import org.firstinspires.ftc.teamcode.Subsystemv2.Subsystems.lift.Lift;
import org.firstinspires.ftc.teamcode.Subsystemv2.Subsystems.pivot.Pivot;
import org.firstinspires.ftc.teamcode.Subsystemv2.Subsystems.telemetry.Telem;
import org.firstinspires.ftc.teamcode.Subsystemv2.Subsystems.vision.ColorSensor;

public class Robot {

    Drivetrain drive;
    IMU imu;
    Intake intake;
    Lift lift;
    Pivot pivot;
    ColorSensor colorSensor;
    Telem telem;

    public Robot(HardwareMap hwMap) {
//        this.drive = new Drivetrain(hwMap.dcMotor.get("frontLeft"), hwMap.dcMotor.get("frontRight"), hwMap.dcMotor.get("backLeft"), hwMap.dcMotor.get("backRight"));
//        this.imu = new IMU();
//        this.intake = new Intake();
//        this.lift = new Lift();
//        this.pivot = new Pivot();
//        this.colorSensor = new ColorSensor();
//        this.telem = new Telem(this);
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

    public ColorSensor getColorSensor() {
        return colorSensor;
    }
}
