package org.firstinspires.ftc.teamcode.NewSubsystem.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems.Robot;

//@Disabled
@Autonomous (name = "rightBlue3")
public class prAutonRightBlue3 extends LinearOpMode {
    Robot prbot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap);
        prbot.telem.setTelemetry(telemetry);
        prbot.drive.setTelemetry(telemetry);
        prbot.drive.setBrake(true);
        prbot.drive.setEnc(true);
        prbot.drive.setMaxPower(1);
        prbot.intake.setMaxPower(1);
        prbot.lift.setMaxPower(.8);
        prbot.pivot.setMaxPower(.5);
        waitForStart();
        while (opModeIsActive()) {

            // setup

            prbot.pivot.up();
            prbot.intake.open();
            prbot.lift.down();
            sleep(500);
            prbot.pivot.stop();
            //prbot.intake.stop();
            prbot.lift.stop();

            // go forward towards block
            prbot.drive.forward(34, 5);
            //grab block
            prbot.intake.close();
            sleep(1000);
            // pivot under bridge
            prbot.pivot.down();
            prbot.drive.backward(10, 5);
            // back out of 6 skystone area
            prbot.drive.leftTurn();
            // turn towards alliance bridge
            prbot.drive.setMaxPower(1);
            prbot.drive.forward(72, 10);
            // rotate towards foundation
            prbot.drive.rightTurn();
            prbot.drive.setMaxPower(.4);
            prbot.pivot.up();
            // lift up block
            prbot.lift.up();
            sleep(700);
            prbot.lift.stop();
            prbot.pivot.stop();
            // get up close to foundation
            prbot.drive.forward(8, 2);
            // place block
            prbot.lift.down();
            sleep(600);
            prbot.lift.stop();
            prbot.intake.open();
            sleep(1000);
            // remove intake from foundation
            prbot.lift.up();
            sleep(600);
            prbot.lift.stop();
            prbot.pivot.down();
            // back out of foundation
            prbot.drive.backward(8, 2);
            // turn towards foundation alliance to park
            prbot.drive.rightTurn();
            prbot.lift.down();
            sleep(300);
            prbot.lift.stop();
            prbot.drive.setMaxPower(1);
            // drive distance to park under bridge
            prbot.drive.forward(20, 2);
            break;
        }
    }
}
