package org.firstinspires.ftc.teamcode.Subsystemv2.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Robot;

/**
 * Date: 2/14/20
 * Author: Jacob Marinas
 * Autonomous template
 */
@Autonomous (name = "AutonLeftRedv2", group = "NewAuton")
public class prAutonLeftRed extends LinearOpMode {
    Robot prbot = new Robot();
    int autonState = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap, telemetry);
        prbot.setMode(Constants.Status.AUTO);
        prbot.getLift().setTelemetry(telemetry);
        waitForStart();
        while (opModeIsActive()) {

            prbot.getPivot().up();
            prbot.getIntake().open();

            sleep(800);

            prbot.getPivot().stop();

            //prbot.getLift().setLevel(-2);
            //prbot.getLift().updateLevel();
            //sleep(500);

            prbot.getDrivetrain().forward(25);
            sleep(500);
            prbot.getDrivetrain().setMaxPower(.25);
            prbot.getDrivetrain().forward(4);
            sleep(500);
            prbot.getTelem().addColorSensor();
            prbot.getTelem().update();
            if (prbot.getColorSensor().isSkystone()) {
                autonState = 1;
            }
            sleep(5000);
            telemetry.addData("autonState", autonState);
            telemetry.update();
            sleep(10000);
            /*
            prbot.getDrivetrain().strafeLeft(6);
            sleep(500);
            if (prbot.getColorSensor().isSkystone()) {
                autonState = 2;
            } else {
                autonState = 3;
            }
            sleep(500);
            prbot.getDrivetrain().strafeLeft(6);

            if (autonState == 1) {
                autonomousOne();
            } else if (autonState == 2) {
                autonomousTwo();
            } else if (autonState == 3) {
                autonomousThree();
            }
             */
        }
    }

    public void autonomousOne() {

    }

    public void autonomousTwo() {

    }

    public void autonomousThree() {

    }
}
