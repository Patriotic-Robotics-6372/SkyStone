package org.firstinspires.ftc.teamcode.NewSubsystem;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.NewSubsystem.Robot;

@Autonomous (name = "leftRed", group = "Subsystem")
public class prAutonLeftRed extends LinearOpMode {
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

            // move forward

            prbot.drive.setMaxPower(.5);
            prbot.drive.forward();

            sleep(1600);


            // grab block
            prbot.lift.stop();
            prbot.drive.stop();
            prbot.intake.close();
            sleep(1000);

            // move backwards
            prbot.drive.backward();
            sleep(500);

            // turn right
            prbot.drive.stop();
            sleep(200);
            prbot.drive.rotateRight();
            prbot.pivot.down();
            sleep(1400);

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
            sleep(1200);

            //move forwards
            prbot.drive.forward();
            sleep(500);

            prbot.drive.stop();
            sleep(500);

            // pivot up
            prbot.pivot.up();
            sleep(500);
            prbot.pivot.stop();

            // lift up
            prbot.lift.up();
            sleep(1800);
            prbot.lift.stop();

            // get close to foundation
            prbot.drive.forward();
            sleep(500);
            prbot.drive.stop();

            // lower block onto foundation
            prbot.lift.down();
            sleep(1800);
            prbot.lift.stop();

            // let go of block
            prbot.intake.open();
            sleep(1500);
            prbot.intake.stop();

            // raise lift out of foundation
            prbot.lift.up();
            sleep(1000);
            prbot.lift.stop();

            // move away from lift
            prbot.drive.backward();
            sleep(1500);
            prbot.drive.stop();

            // turn towards bridge
            prbot.drive.rotateLeft();
            sleep(1200);

            prbot.drive.stop();

            // pivot down to fit under bridge
            prbot.pivot.down();
            sleep(500);

            prbot.drive.forward();
            sleep(1500);

            //reverse
            prbot.drive.backward();
            sleep(300);

            //turn left
            prbot.drive.rotateLeft();
            sleep(400);
            prbot.drive.stop();

            //forward and park
            prbot.pivot.down();
            sleep(500);
            prbot.pivot.stop();
            prbot.drive.forward();
            sleep(1500);
            prbot.drive.stop();

            break;

        }


    }
}
