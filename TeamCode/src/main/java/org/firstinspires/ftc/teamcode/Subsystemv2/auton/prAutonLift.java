package org.firstinspires.ftc.teamcode.Subsystemv2.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.lift.Lift;

/**
 * Date: 2/15/20
 * Author: Jacob Marinas
 * Autonomous that attempts to move lift by encoder counts
 */
@Autonomous(name = "AutonLift", group = "Test")
public class prAutonLift extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Lift lift = new Lift(hardwareMap.dcMotor.get("lift"));
        lift.setMaxPower(.15);
        lift.setTelemetry(telemetry);
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.increaseLevel();
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.updateLevel();
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.increaseLevel();
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.updateLevel();
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.increaseLevel();
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.updateLevel();
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.increaseLevel();
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.updateLevel();
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.decreaseLevel();
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.updateLevel();
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.decreaseLevel();
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.updateLevel();
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.decreaseLevel();
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.updateLevel();
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.decreaseLevel();
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.updateLevel();
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.setLevel(3);
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.updateLevel();
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.setLevel(0);
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            lift.updateLevel();
            telemetry.addData("Level", lift.getCurrentLevel());
            telemetry.addData("Current Power", lift.getLift().getPower());
            telemetry.update();
            sleep(1000);
            break;
        }
    }
}
