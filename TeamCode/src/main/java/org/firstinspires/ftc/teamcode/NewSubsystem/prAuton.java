package org.firstinspires.ftc.teamcode.NewSubsystem;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class prAuton extends LinearOpMode {
    Robot prbot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap);
        prbot.drive.setEnc(true);
        prbot.drive.setBrake(true);
        prbot.drive.setMaxPower(1);
        prbot.lift.setMaxPower(.8);
        prbot.intake.setMaxPower(1);
        prbot.pivot.setMaxPower(.5);

        waitForStart();

        
    }
}
