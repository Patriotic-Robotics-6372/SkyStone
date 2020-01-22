package org.firstinspires.ftc.teamcode.NewSubsystem.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems.Robot;

@Disabled
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
        prbot.drive.setMaxPower(.4);
        prbot.intake.setMaxPower(1);
        prbot.lift.setMaxPower(.8);
        prbot.lift.setMaxPower(.5);
        waitForStart();
        while (opModeIsActive()){

            prbot.drive.forward(6, 5);
            prbot.drive.backward(6, 5);
            prbot.drive.setMaxPower(.2);
            prbot.drive.forward(6, 5);
            prbot.drive.backward(6, 5);

            sleep(3000);

            prbot.drive.runWithoutEncoders();

            prbot.drive.setMaxPower(.4);
            prbot.drive.forward();
            sleep(2000);
            prbot.drive.backward();
            sleep(2000);
            prbot.drive.setMaxPower(.2);
            prbot.drive.forward();
            sleep(2000);
            prbot.drive.backward();
            sleep(2000);

            break;
        }
    }
}
