package org.firstinspires.ftc.teamcode.NewSubsystem;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.NewSubsystem.Robot;

@Autonomous (name = "leftRed")
public class leftRed extends LinearOpMode {
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

            // setup: lift up, intake open

            prbot.pivot.up();
            prbot.intake.open();
            sleep(500);

            prbot.lift.stop();

            // move forward

            prbot.drive.forward();
            sleep(1500);

            // grab block
            prbot.drive.stop();
            prbot.intake.close();
            sleep(2000);

            // move backwards
            prbot.drive.backwards();
            sleep(1500);

            // turn right
            prbot.drive.stop();
            sleep(200);
            prbot.drive.rotateRight();
            prbot.pivot.down();
            sleep(400);

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
            sleep(400);

            // place block
            prbot.drive.stop();
            sleep(200);
            prbot.pivot.up();
            sleep(300);
            prbot.pivot.stop();
            prbot.lift.up();
            sleep(1000);
            prbot.lift.stop();
            prbot.drive.forward();
            sleep(500);
            prbot.drive.stop();
            prbot.lift.down();
            sleep(700);
            prbot.lift.stop();
            prbot.intake.open();
            sleep(1500);
            prbot.intake.stop();
            prbot.drive.backwards();
            sleep(500);
            prbot.drive.stop();

            break;

        }


    }
}
