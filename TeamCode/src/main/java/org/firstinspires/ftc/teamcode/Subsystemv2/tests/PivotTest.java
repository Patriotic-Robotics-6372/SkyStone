package org.firstinspires.ftc.teamcode.Subsystemv2.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.pivot.Pivot;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.telemetry.Telem;

/**
 * Date: 2/14/20
 * Author: Jacob Mairnas
 * Test program to check pivot functionality and data
 */
@Disabled
@TeleOp (name = "PivotTest", group = "Test")
public class PivotTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Pivot pivot = new Pivot(hardwareMap.dcMotor.get("leftPivot"), hardwareMap.dcMotor.get("rightPivot"));
        Telem telem = new Telem(pivot, telemetry);
        pivot.setMaxPower(.5);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.dpad_up) {
                pivot.up();
            } else if (gamepad1.dpad_down) {
                pivot.down();
            } else {
                pivot.stop();
            }
            telem.addPivot();
            telem.update();
        }
    }
}
