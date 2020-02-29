package org.firstinspires.ftc.teamcode.Subsystemv1.Auton;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Subsystemv1.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.imu.IMU;

//@Disabled
@Autonomous (name = "prAuton leftRed")
public class prAutonLeftRed4 extends LinearOpMode {

    private BNO055IMU imu;

    private Orientation angles;
    private Acceleration gravity;

    Robot prbot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        //imu = hardwareMap.get(BNO055IMU.class, "imu 1");
        prbot.init(hardwareMap);
        prbot.telem.setTelemetry(telemetry);
        prbot.drive.setTelemetry(telemetry);
        prbot.drive.setBrake(true);
        prbot.drive.setEnc(true);
        prbot.lift.setBrake(false);
        prbot.drive.setMaxPower(.5);
        prbot.intake.setMaxPower(1);
        prbot.lift.setMaxPower(.8);
        prbot.pivot.setMaxPower(.5);

//        BNO055IMU.Parameters param = new BNO055IMU.Parameters();
//        param.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
//        param.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//        param.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
//        param.loggingEnabled      = true;
//        param.loggingTag          = "IMU";
//        param.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
//        imu.initialize(param);

        waitForStart();
        while (opModeIsActive()) {

            // setup

            /*
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            telemetry.addData("Heading", angles.firstAngle);
            telemetry.update();
            sleep(3000);
             */

            prbot.pivot.up();
            prbot.intake.open();
            prbot.lift.down();
            sleep(500);
            prbot.pivot.stop();
            //prbot.intake.stop();
            prbot.lift.stop();

            // strafe left
            prbot.drive.strafeLeft(3, 5);
            prbot.drive.stop();
            prbot.drive.moveRightSide(.2);
            sleep(500);
            prbot.drive.stopRightSide();
            prbot.drive.setMaxPower(1);
            sleep(500);

            // go forward towards block
            prbot.drive.forward(36, 5);
//            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//            telemetry.addData("Heading", angles.firstAngle);
//            telemetry.update();
//            sleep(10000);
            //grab block
            prbot.intake.close();
            sleep(1000);
            // pivot under bridge
            prbot.pivot.down();
            prbot.drive.backward(10, 5);
            // back out of 6 skystone area
            //prbot.drive.rightTurn();
            //prbot.drive.rotateRight(1.2, 1);
            prbot.drive.rotateRight(19, 3);
            // turn towards alliance bridge
            prbot.drive.setMaxPower(1);
            prbot.intake.close();
            prbot.drive.forward(75, 10);
            // rotate towards foundation
            prbot.drive.leftTurn();
            prbot.drive.setMaxPower(.4);
            prbot.intake.close();
            prbot.pivot.up();
            // lift up block
            prbot.lift.up();
            sleep(700);
            prbot.pivot.stop();
            // get up close to foundation
            prbot.drive.forward(10.5, 2);
            // place block
            prbot.lift.down();
            sleep(600);
            prbot.lift.stop();
            prbot.drive.strafeLeft(2, 5);
            prbot.drive.strafeRight(2, 5);
            sleep(500);
            prbot.intake.open();
            prbot.drive.strafeLeft(2, 5);
            prbot.drive.strafeRight(2, 5);
            sleep(1000);
            //prbot.intake.stop();
            // remove intake from foundation
            prbot.lift.up();
            sleep(600);
            prbot.intake.stop();
            prbot.lift.stop();
            sleep(400);
            // back out of foundation
            prbot.drive.backward(8.5, 2);
            //prbot.pivot.down();
            prbot.intake.close();
            sleep(1000);
            //prbot.pivot.stop();
            prbot.intake.stop();
            prbot.lift.down();
            sleep(400);
            prbot.lift.stop();
            prbot.pivot.down();
            sleep(500);
            prbot.pivot.stop();
            // turn towards foundation alliance to park
           // prbot.drive.leftTurn();
           // prbot.drive.rotateLeft(2.2, 1);
            prbot.drive.rotateLeft(20, 3);
            prbot.drive.setMaxPower(1);
            // drive distance to park under bridge
            prbot.drive.forward(34, 2);
            break;
        }
    }
}
