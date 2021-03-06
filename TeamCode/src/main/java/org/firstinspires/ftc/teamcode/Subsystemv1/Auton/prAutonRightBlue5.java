package org.firstinspires.ftc.teamcode.Subsystemv1.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystemv1.Subsystems.Robot;

//@Disabled
@Autonomous (name = "prAuton rightBlue pulledHorizontal")
public class prAutonRightBlue5 extends LinearOpMode {
    Robot prbot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap);
        prbot.telem.setTelemetry(telemetry);
        prbot.drive.setTelemetry(telemetry);
        prbot.drive.setBrake(true);
        prbot.drive.setEnc(true);
        prbot.lift.setBrake(false);
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

            sleep(3000);

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
            //prbot.drive.leftTurn();
            //prbot.drive.rotateLeft(1.2, 1);
            prbot.drive.rotateLeft(19, 3);
            // turn towards alliance bridge
            prbot.drive.setMaxPower(1);
            prbot.intake.close();
            prbot.drive.forward(71, 10);
            // rotate towards foundation
            prbot.drive.strafeLeft(9, 3);
            prbot.drive.setMaxPower(.4);
            prbot.intake.close();
            prbot.pivot.up();
            // lift up block
            prbot.lift.up();
            sleep(700);
            prbot.pivot.stop();
            // get up close to foundation
            prbot.drive.forward(14, 2);
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
            prbot.drive.backward(15, 2);
            prbot.lift.down();
            sleep(300);
            prbot.lift.stop();
            prbot.pivot.down();
            prbot.intake.close();
            sleep(1000);
            prbot.pivot.stop();
            prbot.intake.stop();
            //prbot.drive.strafeRight(9, 3);
            prbot.drive.rotateRight(2, 2);
            // turn towards foundation alliance to park
            prbot.drive.setMaxPower(1);
            // drive distance to park under bridge
            prbot.drive.backward(30, 2);
            break;
        }
    }
}
