package org.firstinspires.ftc.teamcode.Stable.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.CuttingEdge.Hardware.PRRobot;
/*
    author: Jacob Marinas
    date: 12/5/19
    desc: simple park that goes under the bridge from back
 */
@Autonomous (name = "parkLong")
public class parkLong extends LinearOpMode {
    PRRobot prBot = new PRRobot("stable");
    @Override
    public void runOpMode() throws InterruptedException {
        prBot.init(hardwareMap);
        waitForStart();
        prBot.move(.6, .6, PRRobot.Status.FORWARDS);
        sleep(2800);
    }
}