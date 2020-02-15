package org.firstinspires.ftc.teamcode.Subsystemv2.tests;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.imu.IMU;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.telemetry.Telem;

public class IMUTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        IMU imu = new IMU(hardwareMap.get(BNO055IMU.class, "imu"));
        imu.initialize(parameters);

        Telem telem = new Telem(imu, telemetry);

        waitForStart();
        while (opModeIsActive()) {
            telem.addIMU();
            telem.update();
        }
    }
}
