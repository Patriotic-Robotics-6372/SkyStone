package org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems.Lift;
import org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems.Pivot;
import org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems.Telem;

public class Robot {

    public Drivetrain drive;
    public Intake intake;
    public Lift lift;
    public Pivot pivot;
    public Telem telem;

    public void init(HardwareMap hwMap){
        drive = new Drivetrain(hwMap.dcMotor.get("frontLeft"), hwMap.dcMotor.get("frontRight"), hwMap.dcMotor.get("backLeft"), hwMap.dcMotor.get("backRight"));
        intake = new Intake(hwMap.crservo.get("leftPinch"), hwMap.crservo.get("rightPinch"));
        lift = new Lift(hwMap.dcMotor.get("lift"));
        pivot = new Pivot(hwMap.dcMotor.get("leftPivot"), hwMap.dcMotor.get("rightPivot"));
        telem = new Telem(drive, intake, lift, pivot);
    }
}
