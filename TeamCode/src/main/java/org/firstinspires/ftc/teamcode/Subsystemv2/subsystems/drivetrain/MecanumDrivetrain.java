package org.firstinspires.ftc.teamcode.Subsystemv2.subsystems.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MecanumDrivetrain extends Drivetrain {

    public MecanumDrivetrain(DcMotor fL, DcMotor fR, DcMotor bL, DcMotor bR) {
        super(fL, fR, bL, bR);
    }

    @Override
    public void setRunMode(DcMotor.RunMode runMode) {
        super.setRunMode(runMode);
    }
}
