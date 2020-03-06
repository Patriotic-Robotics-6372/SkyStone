package org.firstinspires.ftc.teamcode.Subsystemv2.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Robot;

/**
 * Date: 2/15/20
 * Author: Jacob Marinas
 * Autonomous that attempts to turn 90 degrees using imu values
 */
@Disabled
@Autonomous (name = "AutonTestIMU", group = "Test")
public class prAutonTestIMU extends LinearOpMode {
    Robot prbot = new Robot();
    float currentAngle, goalAngle;
    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap, telemetry);
        prbot.setMode(Constants.Status.AUTO);
        waitForStart();
        while (opModeIsActive()) {
            sleep(3000);
            currentAngle = prbot.getIMU().getAngles().firstAngle;
            telemetry.addData("Current Angle", currentAngle);
            telemetry.update();
            sleep(3000);
            currentAngle = prbot.getIMU().getAngles().firstAngle;
            goalAngle = currentAngle + 90;
            telemetry.addData("Current Angle", currentAngle);
            telemetry.addData("Goal Angle", goalAngle);
            telemetry.update();
            sleep(3000);
            while (currentAngle < goalAngle) {
                currentAngle = Float.valueOf(prbot.getIMU().getFirstAngle());
                prbot.getDrivetrain().pivotTurnLeft(.5);
                telemetry.addData("Current Angle", currentAngle);
                telemetry.addData("FirstAngle", prbot.getIMU().getFirstAngle());
                //prbot.getTelem().addDrivetrain();
                telemetry.update();
            }
            prbot.getDrivetrain().stop();
            telemetry.addData("Current Angle", currentAngle);
            prbot.getTelem().addDrivetrain();
            telemetry.update();
            sleep(3000);
        }
    }
}
