package org.firstinspires.ftc.teamcode.Subsystemv2.subsys.imu;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Locale;

/**
 * Date: 2/11/20
 * Author: Jacob Marinas
 * The IMU subsystem
 */
public class IMU {

    BNO055IMU imu;

    Orientation angles;
    Acceleration gravity;

    public IMU(BNO055IMU imu) {
        this.imu = imu;
        BNO055IMU.Parameters param = new BNO055IMU.Parameters();
        param.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        param.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        param.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        param.loggingEnabled      = true;
        param.loggingTag          = "IMU";
        param.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
    }

    public void initialize(BNO055IMU.Parameters parameters) {
        imu.initialize(parameters);
    }

    public Orientation getAngles() {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return angles;
    }

    public void updateAngles() {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    }

    public String getFirstAngle() {
        return formatAngle(angles.angleUnit, angles.firstAngle);
    }

    public String getSecondAngle() {
        return formatAngle(angles.angleUnit, angles.secondAngle);
    }

    public String getThirdAngle() {
        return formatAngle(angles.angleUnit, angles.thirdAngle);
    }

    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
}
