package org.firstinspires.ftc.teamcode.NewSubsystem;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.NewSubsystem.Robot;

@Autonomous (name = "rightRed")
public class rightRed extends LinearOpMode {
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
           sleep(500);
           prbot.pivot.stop();

           //lift up
            prbot.lift.up();
            sleep(300);
            prbot.lift.stop();

            //lift down
            prbot.lift.down();
            sleep(300);
            prbot.lift.stop();

            //rotate right
            prbot.drive.rotateRight();

            break;

        }


    }
}
