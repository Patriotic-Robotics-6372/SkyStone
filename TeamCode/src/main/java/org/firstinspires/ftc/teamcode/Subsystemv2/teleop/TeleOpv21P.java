package org.firstinspires.ftc.teamcode.Subsystemv2.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Robot;

@TeleOp (name = "TeleOpv2P1")
public class TeleOpv21P extends LinearOpMode {
    Robot prbot = new Robot();
    double leftMove, leftSlide, rightMove, rightSlide;
    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap, telemetry);
        prbot.setMode(Constants.Status.NORMAL);
        waitForStart();
        while (opModeIsActive()) {

//            prbot.getGp1().getLeftBumper().previous();
//            prbot.getGp1().getLeftBumper().setState(gamepad1.left_bumper);
//            prbot.getGp1().getLeftBumper().isPressed();
//
//            if (prbot.getGp1().getLeftBumper().getToggle()) {
//                prbot.getDrivetrain().throttleOn();
//            } else {
//                prbot.getDrivetrain().throttleOff();
//            }

            if (gamepad1.a) {
                prbot.getDrivetrain().setSpeedPercentage(.5);
            }

            if (gamepad1.b) {
                prbot.getDrivetrain().setSpeedPercentage(.75);
            }

            if (gamepad1.x) {
                prbot.getDrivetrain().setSpeedPercentage(.25);
            }

            if (gamepad1.y) {
                prbot.getDrivetrain().setSpeedPercentage(1);
            }

            rightSlide = gamepad1.right_stick_x;
            leftSlide = gamepad1.left_stick_x;
            rightMove = gamepad1.right_stick_y;
            leftMove = gamepad1.left_stick_y;
            prbot.getDrivetrain().setRightSide(rightSlide + rightMove, -rightSlide + rightMove);
            prbot.getDrivetrain().setLeftSide(-leftSlide + leftMove, leftSlide + leftMove);

            // Lift

            if (gamepad1.right_trigger > .1){
                prbot.getLift().getLift().setPower(gamepad1.right_trigger);
            } else if (gamepad1.left_trigger > .1){
                prbot.getLift().getLift().setPower(-gamepad1.left_trigger);
            } else {
                prbot.getLift().stop();
            }

            // Intake

//            prbot.getGp1().getRightBumper().previous();
//            prbot.getGp1().getRightBumper().setState(gamepad1.right_bumper);
//            prbot.getGp1().getRightBumper().isPressed();
//
//            if (prbot.getGp1().getRightBumper().getToggle()) {
//                prbot.getIntake().close();
//            } else {
//                prbot.getIntake().open();
//            }

            if (gamepad1.right_bumper) {
                prbot.getIntake().close();
            } else if (gamepad1.left_bumper){
                prbot.getIntake().open();
            } else {
                prbot.getIntake().stop();
            }

            // Pivot

            if (gamepad1.dpad_up){
                prbot.getPivot().up();
            } else if (gamepad1.dpad_down){
                prbot.getPivot().down();
            } else {
                prbot.getPivot().stop();
            }

            prbot.getTelem().addDrivetrain();
            prbot.getTelem().update();
        }
    }
}
