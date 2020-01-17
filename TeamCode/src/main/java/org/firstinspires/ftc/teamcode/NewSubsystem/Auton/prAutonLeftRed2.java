package org.firstinspires.ftc.teamcode.NewSubsystem.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems.Robot;

@Disabled
@Autonomous (name = "leftRed", group = "Subsystem")
public class prAutonLeftRed2 extends LinearOpMode {
    Robot prbot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap);
        prbot.telem.setTelemetry(telemetry);
        prbot.drive.setBrake(true);
        prbot.drive.setEnc(false);
        prbot.drive.setMaxPower(.8);
        prbot.intake.setMaxPower(1);
        prbot.lift.setMaxPower(.8);
        prbot.pivot.setMaxPower(.8);

        waitForStart();

        while (opModeIsActive()) {

            // setup: pivot up, intake open

            prbot.pivot.up();
            prbot.intake.open();
            prbot.lift.down();
            sleep(500);
            prbot.pivot.stop();
            prbot.lift.stop();

            // move forward

            prbot.drive.setMaxPower(.5);
            prbot.drive.forward();

            sleep(1600);


            // grab block
            prbot.drive.stop();
            prbot.intake.close();
            sleep(1000);

            // move backwards
            prbot.drive.backward();
            sleep(750);

            // turn right
            prbot.drive.stop();
            sleep(200);
            prbot.drive.rotateRight();
            prbot.pivot.down();
            sleep(1350);

            // move forwards
            prbot.drive.stop();
            sleep(200);
            prbot.drive.forward();
            prbot.pivot.stop();
            sleep(3000);

            // turn left
            prbot.drive.stop();
            sleep(200);
            prbot.drive.rotateLeft();
            sleep(600);
            prbot.drive.stop();

            // go forward
            prbot.drive.forward();
            sleep(700);
            prbot.drive.stop();

            // drop block
            prbot.intake.open();
            sleep(1000);
            prbot.intake.stop();

            // go backward
            prbot.drive.backward();
            sleep(700);
            prbot.drive.stop();

            // turn left
            prbot.drive.rotateLeft();
            sleep(1250);
            prbot.drive.stop();

            // move forward
            prbot.drive.forward();
            sleep(1600);
            prbot.drive.stop();

            //
            break;

        }


    }
}
