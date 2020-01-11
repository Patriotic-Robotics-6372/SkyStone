package org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Telemetry subsystem
 *  MAKE SURE TO USE SETTELEMETRY
 */

public class Telem {

    private Telemetry telemetry;
    private Drivetrain drive;
    private Intake intake;
    private Lift lift;
    private Pivot pivot;

    public Telem(Drivetrain drive, Intake intake, Lift lift, Pivot pivot){
        this.drive = drive;
        this.intake = intake;
        this.lift = lift;
        this.pivot = pivot;
    }

    public void setTelemetry(Telemetry telemetry){
        this.telemetry = telemetry;
    }

    public void addSpeeds(){
        telemetry.addData("Base", drive.getSpeeds().toString());
        telemetry.addData("Intake", intake.getSpeeds().toString());
        telemetry.addData("Lift", lift.getSpeed());
        telemetry.addData("Pivot", pivot.getSpeeds().toString());
    }

    public void addStatus(){
        telemetry.addData("Base", drive.getStatus());
        telemetry.addData("Intake", intake.getStatus());
        telemetry.addData("Lift", lift.getStatus());
        telemetry.addData("Pivot", pivot.getStatus());
    }

    public void addMaxPowers(){
        telemetry.addData("Base", drive.getMaxPower());
        telemetry.addData("Intake", intake.getMaxPower());
        telemetry.addData("Lift", lift.getMaxPower());
        telemetry.addData("Pivot", pivot.getMaxPower());
    }

    public void addBase(){
        telemetry.addData("BaseSpeed", drive.getSpeeds());
        telemetry.addData("BaseStatus", drive.getStatus());
        telemetry.addData("BaseMaxPower", drive.getMaxPower());
        addBaseMode();
        addBaseZPB();
    }

    public void addIntake(){
        telemetry.addData("IntakeSpeed", intake.getSpeeds().toString());
        telemetry.addData("IntakeStatus", intake.getStatus());
        telemetry.addData("IntakeMaxPower", intake.getMaxPower());
    }

    public void addLift(){
        telemetry.addData("LiftSpeed", lift.getSpeed());
        telemetry.addData("LiftStatus", lift.getStatus());
        telemetry.addData("LiftMaxPower", lift.getMaxPower());
    }

    public void addPivot(){
        telemetry.addData("PivotSpeed", pivot.getSpeeds().toString());
        telemetry.addData("PivotStatus", pivot.getStatus());
        telemetry.addData("PivotMaxPower", pivot.getMaxPower());
    }

    public void addEncoders(){
        telemetry.addData("TickGoal", drive.getTickGoal());
        telemetry.addData("FrontLeftPos", drive.getFrontLeftEncoder());
        telemetry.addData("FrontRightPos", drive.getFrontRightEncoder());
    }

    public void addFrontLeftEncoder(){
        telemetry.addData("TickGoal", drive.getTickGoal());
        telemetry.addData("FrontLeftPos", drive.getFrontLeftEncoder());
    }

    public void addFrontRightEncoder(){
        telemetry.addData("TickGoal", drive.getTickGoal());
        telemetry.addData("FrontRightPos", drive.getFrontRightEncoder());
    }

    public void addBaseMode(){
        telemetry.addData("BaseMode", drive.getModes());
    }

    public void addBaseZPB(){
        telemetry.addData("BaseZPB", drive.getZPB());
    }

    public void addAll(){
        addBase();
        addIntake();
        addLift();
        addPivot();
    }

    public void update(){
        telemetry.update();
    }
}
