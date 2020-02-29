package org.firstinspires.ftc.teamcode.Subsystemv1.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystemv1.Subsystems.Robot;

@Autonomous (name = "autonTest", group = "Test")
public class prAutonLift extends LinearOpMode {
    Robot prbot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap);
        prbot.telem.setTelemetry(telemetry);
        prbot.drive.setTelemetry(telemetry);
        prbot.drive.setBrake(true);
        prbot.drive.setEnc(true);
        prbot.lift.setBrake(true);
        prbot.drive.setMaxPower(.5);
        prbot.intake.setMaxPower(1);
        prbot.lift.setMaxPower(.8);
        prbot.pivot.setMaxPower(.5);

        boolean MOVE = false;
        boolean TURN = false;
        boolean LIFT = false;
        boolean INTAKE = true;


        waitForStart();

        if (MOVE) {
            prbot.drive.forward(36, 4);
            sleep(500);
            prbot.drive.backward(36, 4);
            sleep(500);
            prbot.drive.forward(36, 4);
            sleep(500);
            prbot.drive.backward(36, 4);
            sleep(500);

            prbot.drive.forward(36, 4);
            sleep(500);
            prbot.drive.backward(36, 4);
            sleep(500);

            prbot.drive.forward(36, 4);
            sleep(500);
            prbot.drive.backward(36, 4);
            sleep(500);

            prbot.drive.forward(36, 4);
            sleep(500);
            prbot.drive.backward(36, 4);
            sleep(500);
        }

        if (TURN) {
            prbot.drive.leftTurn();
            sleep(500);
            prbot.drive.rightTurn();
            sleep(500);
            prbot.drive.leftTurn();
            sleep(500);
            prbot.drive.rightTurn();
            sleep(500);
            prbot.drive.leftTurn();
            sleep(500);
            prbot.drive.rightTurn();
            sleep(500);
            prbot.drive.leftTurn();
            sleep(500);
            prbot.drive.rightTurn();
            sleep(500);
        }

        if (LIFT) {
            prbot.lift.down();
            sleep(2000);
            prbot.lift.stop();
            sleep(500);
            prbot.lift.up();
            sleep(2000);
            prbot.lift.stop();
            sleep(500);
            prbot.lift.down();
            sleep(1500);
            prbot.lift.stop();
            sleep(500);
            prbot.lift.up();
            sleep(1500);
            prbot.lift.stop();
            sleep(500);
            prbot.lift.down();
            sleep(1000);
            prbot.lift.stop();
            sleep(500);
            prbot.lift.up();
            sleep(1000);
            prbot.lift.stop();
            sleep(500);
        }

        if (INTAKE) {
            prbot.intake.open();
            sleep(2000);
            prbot.intake.stop();
            sleep(2000);
            prbot.intake.close();
            sleep(2000);
            prbot.intake.stop();
            sleep(2000);
            prbot.intake.open();
            sleep(2000);
            prbot.intake.stop();
            sleep(2000);
            prbot.intake.close();
            sleep(2000);
            prbot.intake.stop();
            sleep(2000);
            prbot.intake.open();
            sleep(2000);
            prbot.intake.stop();
            sleep(2000);
            prbot.intake.close();
            sleep(2000);
            prbot.intake.stop();
            sleep(2000);
        }

    }
}
