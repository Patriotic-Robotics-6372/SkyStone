package org.firstinspires.ftc.teamcode.Subsystemv2.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Robot;

@TeleOp(name = "TeleopWhele", group = "Test")
public class prTeleOpWhele extends LinearOpMode {
    Robot prbot = new Robot();
    double move, slide, spin;
    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap, telemetry);
        prbot.setMode(Constants.Status.NORMAL);
        waitForStart();
        while (opModeIsActive()) {
            move = -gamepad1.left_stick_y;
            slide = gamepad1.left_stick_x;
            spin = gamepad1.right_stick_x;
            prbot.getDrivetrain().setBase(move + slide + spin,
                    move - slide - spin,
                    move - slide + spin,
                    move + slide - spin);
        }
    }
}
