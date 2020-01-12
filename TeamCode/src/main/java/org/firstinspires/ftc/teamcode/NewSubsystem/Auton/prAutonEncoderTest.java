package org.firstinspires.ftc.teamcode.NewSubsystem.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems.Robot;

//@Disabled
@Autonomous (name = "Encoder Test")
public class prAutonEncoderTest extends LinearOpMode {
    Robot prbot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap);
        prbot.telem.setTelemetry(telemetry);
        prbot.drive.setTelemetry(telemetry);
        prbot.drive.setEnc(true);
        prbot.drive.setBrake(true);
        prbot.drive.setMaxPower(.8);
        prbot.intake.setMaxPower(1);
        prbot.lift.setMaxPower(.8);
        prbot.lift.setMaxPower(.5);
        waitForStart();
        while (opModeIsActive()){

            prbot.drive.forward(6); // should travel 6 inches in short time
            prbot.drive.setMaxPower(.4);
            prbot.drive.backward(6);
            /*
            prbot.drive.forward(6); // should travel same distance in longer time
            prbot.drive.setMaxPower(.2);
            prbot.drive.forward(6); // should travel same distance in longear time

            prbot.drive.setMaxPower(.8);
            prbot.drive.backward(6);
            prbot.drive.setMaxPower(.4);
            prbot.drive.backward(6);
            prbot.drive.setMaxPower(.2);
            prbot.drive.backward(6);

            prbot.drive.setMaxPower(.8);
            prbot.drive.rotateRight(2);
            prbot.drive.setMaxPower(.4);
            prbot.drive.rotateRight(2);
            prbot.drive.setMaxPower(.2);
            prbot.drive.rotateRight(2);

            prbot.drive.setMaxPower(.8);
            prbot.drive.rotateLeft(2);
            prbot.drive.setMaxPower(.4);
            prbot.drive.rotateLeft(2);
            prbot.drive.setMaxPower(.2);
            prbot.drive.rotateLeft(2);]


            */

            sleep(3000);
            break;
        }
    }
}
