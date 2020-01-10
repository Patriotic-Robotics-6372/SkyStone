package org.firstinspires.ftc.teamcode.NewSubsystem;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
@Autonomous (name = "rightBlue")
public class rightBlue extends LinearOpMode {
    Robot bot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);
        bot.telem.setTelemetry(telemetry);
        bot.drive.setEnc(false);
        bot.drive.setBrake(true);
        bot.drive.setMaxPower(1);
        bot.intake.setMaxPower(1);
        bot.lift.setMaxPower(.8);
        bot.pivot.setMaxPower(.8);
        waitForStart();

        while (opModeIsActive()) {
            bot.pivot.up();
            sleep(500);
            bot.lift.stop(); //pivots up


            bot.drive.setMaxPower(.5);

            bot.lift.down();
            sleep(1000);
            bot.lift.stop();


            bot.drive.forward();
            bot.intake.open();
            sleep(1500); // moves forward and opens intake
            bot.intake.stop();
            bot.drive.stop();

            bot.intake.close();
            sleep(1000); // grabs block

            bot.drive.backwards();
            sleep(750); //moves backwards
            bot.drive.stop();

            bot.pivot.down();
            sleep(1000); //pivot down
            bot.pivot.stop();

            bot.drive.rotateLeft();
            sleep(1000); //rotates left
            bot.drive.stop();

            bot.drive.forward();
            sleep(1000);
            bot.drive.stop();

            break;

        }


    }
}
