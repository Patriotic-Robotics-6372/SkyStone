package org.firstinspires.ftc.teamcode.NewSubsystem.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems.Robot;

@Autonomous (name = "rightRed", group = "Subsystem")
public class prAutonRightRed extends LinearOpMode {
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

            //pivot up
           prbot.pivot.up();

           // strafe right
            prbot.drive.setMaxPower(1);
            prbot.drive.strafeRight();
            sleep(500);
            prbot.pivot.stop();
            sleep(500);
            prbot.drive.stop();
            prbot.drive.setMaxPower(.8);

            //forward
            prbot.drive.forward();
            sleep(1200);
            prbot.drive.stop();

            // strafe left
            /*
            prbot.drive.strafeLeft();
            sleep(600);
            prbot.drive.stop();
             */

            //lift down
            prbot.lift.down();
            sleep(700);
            prbot.lift.stop();

            //go backwards
            /*
            prbot.drive.setMaxPower(.5);
            prbot.drive.backwards();
            sleep(2500);
            prbot.drive.stop();
            prbot.drive.setMaxPower(.8);
             */
            // turn right
            /*
            prbot.drive.setMaxPower(.5);
            prbot.drive.rotateRight();
            sleep(1000);
            prbot.drive.stop();
            prbot.drive.setMaxPower(.8);

            sleep(300);
            prbot.drive.setMaxPower(1);
            prbot.drive.strafeRight();
            sleep(3000);
            prbot.drive.stop();
            prbot.drive.setMaxPower(.8);

            // lift up
            prbot.lift.up();
            sleep(500);
            prbot.lift.stop();

            prbot.pivot.down();
            sleep(500);
            prbot.pivot.stop();

            prbot.drive.backwards();
            sleep(1500);
            prbot.drive.stop();
             */



            // pivot down
            prbot.pivot.down();
            sleep(500);
            prbot.pivot.stop();

            // strafe right
            prbot.drive.setMaxPower(1);
            prbot.drive.strafeRight();
            sleep(1500);

            // strafe left
            prbot.drive.strafeLeft();
            sleep(3000);
            prbot.drive.stop();



            break;

        }


    }
}
