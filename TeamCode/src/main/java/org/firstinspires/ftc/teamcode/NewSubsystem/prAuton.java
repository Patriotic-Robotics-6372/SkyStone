package org.firstinspires.ftc.teamcode.NewSubsystem;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Test auton to see if subsystems work
 */

@Autonomous (name = "Subsystem test")
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

        prbot.drive.forward();                  // go forward for 1 second
        prbot.telem.addBase();
        prbot.telem.update();
        sleep(1000);
        prbot.drive.stop();
        prbot.drive.backwards();                // go backward for 1 second
        prbot.telem.addBase();
        prbot.telem.update();
        sleep(1000);
        prbot.drive.stop();                     // move left side for 1 second
        prbot.drive.moveLeftSide(1);
        prbot.telem.addBase();
        prbot.telem.update();
        sleep(1000);
        prbot.drive.stop();                     // move right side for 1 second
        prbot.drive.moveRightSide(1);
        prbot.telem.addBase();
        prbot.telem.update();
        sleep(1000);
        prbot.drive.stop();                     // stop base for 1 second
        prbot.telem.addBase();
        prbot.telem.update();
        sleep(1000);
        prbot.lift.up();                        // lift up for 1 second
        prbot.telem.addLift();
        prbot.telem.update();
        sleep(2000);
        prbot.lift.down();                      // lift down for 1 second
        prbot.telem.addLift();
        prbot.telem.update();
        sleep(1000);
        prbot.lift.stop();                      // stop lift for 1 second
        prbot.telem.addLift();
        prbot.telem.update();
        sleep(1000);
        prbot.intake.open();                    // open intake for 2 seconds
        prbot.telem.addIntake();
        prbot.telem.update();
        sleep(2000);
        prbot.intake.close();                   // close intake for 1 second
        prbot.telem.addIntake();
        prbot.telem.update();
        sleep(1000);
        prbot.intake.stop();                    // stop intake for 1 second
        prbot.telem.addIntake();
        prbot.telem.update();
        sleep(1000);
        prbot.pivot.down();                     // pivot down for 0.5 seconds
        prbot.telem.addPivot();
        prbot.telem.update();
        sleep(500);
        prbot.pivot.up();                       // pivot up for 0.5 seconds
        prbot.telem.addPivot();
        prbot.telem.update();
        sleep(500);
        prbot.pivot.stop();                     // stop pivot for 0.5 seconds
        prbot.telem.addPivot();
        prbot.telem.update();
        sleep(500);
        prbot.telem.addAll();
        prbot.telem.update();
        sleep(5000);
    }
}
