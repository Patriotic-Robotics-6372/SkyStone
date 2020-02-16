package org.firstinspires.ftc.teamcode.Subsystemv2.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Robot;

/**
 * Date: 2/14/20
 * Author: Jacob Marinas
 * Autonomous that figures out which version of the autonomous to run
 */
@Autonomous(name = "AutonTest", group = "Test")
public class prAutonTest extends LinearOpMode {
    Robot prbot = new Robot();
    Constants.Block block;
    String name;
    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap, telemetry);
        prbot.setMode(Constants.Status.AUTO);
        waitForStart();
        while (opModeIsActive()) {
            sleep(3000);
            prbot.getTelem().addColorSensor();
            prbot.getTelem().update();
            if (prbot.getColorSensor().isSkystone()) {
                //block = Constants.Block.ONE;
                name = "One";
            } else {
                prbot.getDrivetrain().strafeRight(3);
                sleep(3000);
                prbot.getTelem().addColorSensor();
                prbot.getTelem().update();
                if (prbot.getColorSensor().isSkystone()) {
                    //block = Constants.Block.TWO;
                    name = "Two";
                } else {
                    prbot.getDrivetrain().strafeRight(3);
                    sleep(3000);
                    prbot.getTelem().addColorSensor();
                    prbot.getTelem().update();
                    //block = Constants.Block.THREE;
                    name = "Three";
                }
            }
            telemetry.addData("Block # of Skystone", name);
            telemetry.update();
            sleep(5000);
            break;
        }
    }
}
