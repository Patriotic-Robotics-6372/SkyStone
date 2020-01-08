package org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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
        telemetry.addData("Base", drive.getSpeeds());
        telemetry.addData("Intake", intake.getSpeeds());
        telemetry.addData("Lift", lift.getSpeed());
        telemetry.addData("Pivot", pivot.getSpeeds());
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
        telemetry.addData("IntakeSpeed", intake.getSpeeds());
        telemetry.addData("IntakeStatus", intake.getStatus());
        telemetry.addData("IntakeMaxPower", intake.getMaxPower());
    }

    public void addLift(){
        telemetry.addData("LiftSpeed", lift.getSpeed());
        telemetry.addData("LiftStatus", lift.getStatus());
        telemetry.addData("LiftMaxPower", lift.getMaxPower());
    }

    public void addPivot(){
        telemetry.addData("PivotSpeed", pivot.getSpeeds());
        telemetry.addData("PivotStatus", pivot.getStatus());
        telemetry.addData("PivotMaxPower", pivot.getMaxPower());
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
