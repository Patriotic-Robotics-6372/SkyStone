package org.firstinspires.ftc.teamcode.Old.CuttingEdge.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Old.Stable.Hardware.PRRobot;

/*
    author: Jacob Marinas
    date: 12/12/19
    desc: test motors for pivot, without encoders
 */
@Disabled
@TeleOp (name = "pivotTest")
public class pivotTest extends OpMode {
    PRRobot prBot = new PRRobot(1, .5, 1, .8, 1, 1);
    @Override
    public void init() {
        prBot.init(hardwareMap);
        prBot.useBrake(false);
        prBot.useEnc(false);
    }

    @Override
    public void loop() {
        if (gamepad1.x) {
            prBot.leftPivot.setPower(prBot.pivotIntakeSpeed);
            prBot.rightPivot.setPower(-prBot.pivotIntakeSpeed);
        } else if (gamepad1.y) {
            prBot.leftPivot.setPower(-prBot.pivotIntakeSpeed);
            prBot.rightPivot.setPower(prBot.pivotIntakeSpeed);
        } else {
            prBot.leftPivot.setPower(prBot.STOP);
            prBot.rightPivot.setPower(prBot.STOP);
        }
        telemetry.addData("leftPivot pow: ", prBot.leftPivot.getPower());
        telemetry.addData("rightPivot pow: ", prBot.rightPivot.getPower());
        updateTelemetry(telemetry);
        telemetry.update();
    }
}
