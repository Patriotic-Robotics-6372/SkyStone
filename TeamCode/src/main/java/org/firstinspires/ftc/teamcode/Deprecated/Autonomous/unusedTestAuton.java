package org.firstinspires.ftc.teamcode.Deprecated.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous (name = "yes")
public class unusedTestAuton extends LinearOpMode {

    public DcMotor yes;
    @Override
    public void runOpMode() throws InterruptedException {
        yes = hardwareMap.dcMotor.get("yes");
        waitForStart();
        yes.setPower(1);
    }
}
