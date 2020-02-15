package org.firstinspires.ftc.teamcode.Subsystemv2.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.telemetry.Telem;

@TeleOp (name = "DrivetrainTest", group = "Test")
public class DrivetrainTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Drivetrain drive = new Drivetrain(hardwareMap.dcMotor.get("frontLeft"), hardwareMap.dcMotor.get("frontRight"), hardwareMap.dcMotor.get("backLeft"), hardwareMap.dcMotor.get("backRight"));
        Telem telem = new Telem(drive, telemetry);
        drive.setMaxPower(1);
        waitForStart();
        while (opModeIsActive()) {
            if (Math.abs(gamepad1.left_stick_y) > .1 || Math.abs(gamepad1.right_stick_y) > .1) {
                drive.setLeftSide(gamepad1.left_stick_y);
                drive.setRightSide(gamepad1.right_stick_y);
            } else {
                drive.stop();
            }
            telem.addDrivetrain();
            telem.update();
        }
    }
}
