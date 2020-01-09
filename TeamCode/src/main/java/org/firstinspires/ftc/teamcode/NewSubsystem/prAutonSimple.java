package org.firstinspires.ftc.teamcode.NewSubsystem;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class prAutonSimple extends LinearOpMode {
    Robot prbot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap);
        prbot.telem.setTelemetry(telemetry);
        prbot.drive.setEnc(false);
        prbot.drive.setBrake(true);
        prbot.drive.setMaxPower(.8);
        prbot.intake.setMaxPower(1);
        prbot.lift.setMaxPower(.8);
        prbot.pivot.setMaxPower(.5);
        waitForStart();
        while (opModeIsActive()) {
            prbot.drive.forward();
            sleep(1800);
            break;
        }
    }
}
