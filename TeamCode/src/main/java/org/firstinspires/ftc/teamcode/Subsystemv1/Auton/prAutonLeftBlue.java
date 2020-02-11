package org.firstinspires.ftc.teamcode.Subsystemv1.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystemv1.Subsystems.Robot;

@Disabled
@Autonomous (name = "leftBlue", group = "Subsystem")
public class prAutonLeftBlue extends LinearOpMode {
    Robot prbot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap);
        prbot.telem.setTelemetry(telemetry);
        prbot.drive.setEnc(false);
        prbot.drive.setBrake(true);
        prbot.drive.setMaxPower(.5);
        prbot.intake.setMaxPower(1);
        prbot.lift.setMaxPower(.8);
        prbot.pivot.setMaxPower(.8);
        waitForStart();

        while (opModeIsActive()) {

            prbot.pivot.up();
            prbot.intake.open();
            prbot.lift.up();
            sleep(1000);

            prbot.pivot.stop();
            prbot.lift.stop();
            prbot.intake.stop();
            prbot.drive.setMaxPower(1);
            prbot.drive.strafeLeft();
            sleep(500);

            prbot.drive.stop();
            sleep(100);

            prbot.drive.setMaxPower(.5);
            prbot.drive.forward();
            sleep(2000);

            prbot.lift.down();
            sleep(1300);

            prbot.drive.backward();
            sleep(2200);

            prbot.drive.stop();
            sleep(100);

            prbot.pivot.down();
            prbot.lift.up();
            sleep(500);

            prbot.lift.stop();

            prbot.drive.setMaxPower(1);
            prbot.drive.strafeRight();
            sleep(2000);

            prbot.drive.stop();

            break;

        }
    }
}
