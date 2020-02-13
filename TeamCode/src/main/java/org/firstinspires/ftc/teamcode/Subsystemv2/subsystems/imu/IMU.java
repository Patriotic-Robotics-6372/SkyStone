package org.firstinspires.ftc.teamcode.Subsystemv2.Subsystems.imu;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

/**
 * Date: 2/11/20
 * Author: Jacob Marinas
 * The IMU subsystem
 */
public class IMU {

    BNO055IMU imu;

    Orientation angles;
    Acceleration gravity;
//
//    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//    parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
//    parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//    parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
//    parameters.loggingEnabled      = true;
//    parameters.loggingTag          = "IMU";
//    parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
}
