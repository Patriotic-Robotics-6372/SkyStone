package org.firstinspires.ftc.teamcode.Subsystemv1.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

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
    private Gamepad gamepad1, gamepad2;

    public Telem(Drivetrain drive, Intake intake, Lift lift, Pivot pivot){
        this.drive = drive;
        this.intake = intake;
        this.lift = lift;
        this.pivot = pivot;
    }

    public void setTelemetry(Telemetry telemetry){
        this.telemetry = telemetry;
    }

    public void setGamepad1(Gamepad gamepad) {
        this.gamepad1 = gamepad;
    }

    public void setGamepad2(Gamepad gamepad) {
        this.gamepad2 = gamepad;
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

    public void addEncoders(){
        telemetry.addData("LeftTickGoal", drive.getLeftTickGoal());
        telemetry.addData("RightTickGoal", drive.getRightTickGoal());
        telemetry.addData("FrontLeftPos", drive.getFrontLeftEncoder());
        telemetry.addData("FrontRightPos", drive.getFrontRightEncoder());
        telemetry.addData("BackLeftPos", drive.getBackLeftEncoder());
        telemetry.addData("BackRightPos", drive.getBackRightEncoder());
    }

    public void addFrontLeftEncoder(){
        telemetry.addData("TickGoal", drive.getLeftTickGoal());
        telemetry.addData("FrontLeftPos", drive.getFrontLeftEncoder());
    }

    public void addFrontRightEncoder(){
        telemetry.addData("TickGoal", drive.getRightTickGoal());
        telemetry.addData("FrontRightPos", drive.getFrontRightEncoder());
    }

    public void addBackLeftEncoder(){
        telemetry.addData("TickGoal", drive.getLeftTickGoal());
        telemetry.addData("BackLeftPos", drive.getBackLeftEncoder());
    }

    public void addBackRightEncoder(){
        telemetry.addData("TickGoal", drive.getRightTickGoal());
        telemetry.addData("BackRightPos", drive.getBackRightEncoder());
    }

    public void addBaseMode(){
        telemetry.addData("BaseMode", drive.getModes());
    }

    public void addBaseZPB(){
        telemetry.addData("BaseZPB", drive.getZPB());
    }

    public void addGamepad1() {
        telemetry.addData("Gamepad1LeftStickY", gamepad1.left_stick_y);
        telemetry.addData("Gamepad1RightStickY", gamepad1.right_stick_y);
        telemetry.addData("Gamepad1DpadLeft", gamepad1.dpad_left);
        telemetry.addData("Gamepad1DpadRight", gamepad1.dpad_right);
        telemetry.addData("Gamepad1DpadUp", gamepad1.dpad_up);
        telemetry.addData("Gamepad1DpadDown", gamepad1.dpad_down);
    }

    public void addGamepad2() {
        telemetry.addData("Gamepad2LeftBumper", gamepad2.left_bumper);
        telemetry.addData("Gamepad2RightBumper", gamepad2.right_bumper);
        telemetry.addData("Gamepad2Y", gamepad2.y);
        telemetry.addData("Gamepad2A", gamepad2.a);
        telemetry.addData("Gamepad2DpadUp", gamepad2.dpad_up);
        telemetry.addData("Gamepad2DpadDown", gamepad2.dpad_down);
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
