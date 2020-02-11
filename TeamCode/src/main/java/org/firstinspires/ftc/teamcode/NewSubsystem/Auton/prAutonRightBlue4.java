package org.firstinspires.ftc.teamcode.NewSubsystem.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems.Robot;

//@Disabled
@Autonomous (name = "prAuton rightBlue")
public class prAutonRightBlue4 extends LinearOpMode {
    Robot prbot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap);
        prbot.telem.setTelemetry(telemetry);
        prbot.drive.setTelemetry(telemetry);
        prbot.drive.setBrake(true);
        prbot.drive.setEnc(true);
        prbot.drive.setMaxPower(.5);
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

            // strafe left
            prbot.drive.strafeRight(3, 5);
            prbot.drive.stop();
            prbot.drive.moveLeftSide(.2);
            sleep(500);
            prbot.drive.stopRightSide();
            prbot.drive.setMaxPower(1);
            sleep(500);

            // go forward towards block
            prbot.drive.forward(36, 5);
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
            prbot.intake.close();
            prbot.drive.forward(75, 10);
            // rotate towards foundation
            prbot.drive.rightTurn();
            prbot.drive.setMaxPower(.4);
            prbot.intake.close();
            prbot.pivot.up();
            // lift up block
            prbot.lift.up();
            sleep(700);
            prbot.pivot.stop();
            // get up close to foundation
            prbot.drive.forward(10.5, 2);
            // place block
            prbot.lift.down();
            sleep(600);
            prbot.lift.stop();
            prbot.drive.strafeRight(2, 5);
            prbot.drive.strafeLeft(2, 5);
            sleep(500);
            prbot.intake.open();
            prbot.drive.strafeRight(2, 5);
            prbot.drive.strafeLeft(2, 5);
            sleep(1000);
            //prbot.intake.stop();
            // remove intake from foundation
            prbot.lift.up();
            sleep(600);
            prbot.intake.stop();
            prbot.lift.stop();
            sleep(400);
            // back out of foundation
            prbot.drive.backward(7.5, 2);
            prbot.pivot.down();
            prbot.intake.close();
            sleep(1000);
            prbot.pivot.stop();
            prbot.intake.stop();
            prbot.lift.down();
            sleep(300);
            prbot.lift.stop();
            // turn towards foundation alliance to park
            prbot.drive.rightTurn();
            prbot.drive.setMaxPower(1);
            // drive distance to park under bridge
            prbot.drive.forward(34, 2);
            break;
        }
    }
}
