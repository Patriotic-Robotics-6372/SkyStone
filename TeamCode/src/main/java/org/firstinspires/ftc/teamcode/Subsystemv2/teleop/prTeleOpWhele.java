package org.firstinspires.ftc.teamcode.Subsystemv2.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;
import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Robot;

/**
 * Date: 2/15/20
 * Author: Jacob Marinas
 * TeleOp that can strafe using the sticks.
 */
@TeleOp(name = "TeleopWhele", group = "Test")
public class prTeleOpWhele extends LinearOpMode {
    Robot prbot = new Robot();
    double move, slide, spin;
    double leftMove, leftSlide, rightMove, rightSlide;
    @Override
    public void runOpMode() throws InterruptedException {
        prbot.init(hardwareMap, telemetry);
        prbot.setMode(Constants.Status.NORMAL);
        waitForStart();
        while (opModeIsActive()) {

            prbot.getGp1().getLeftBumper().previous();
            prbot.getGp1().getLeftBumper().setState(gamepad1.left_bumper);
            prbot.getGp1().getLeftBumper().isPressed();

            if (prbot.getGp1().getLeftBumper().getToggle()) {
                prbot.getDrivetrain().throttleOn();
            } else {
                prbot.getDrivetrain().throttleOff();
            }

//            move = gamepad1.left_stick_y;
//            slide = -gamepad1.left_stick_x;
//            spin = gamepad1.right_stick_x;
//            prbot.getDrivetrain().setBase(move + slide + spin,
//                    move - slide - spin,
//                    move - slide + spin,
//                    move + slide - spin);

//            rightMove = gamepad1.right_stick_y;
//            leftMove = gamepad1.left_stick_y;
//            rightSlide = gamepad1.right_stick_x;
//            leftSlide = -gamepad1.left_stick_x;
//            spin = gamepad1.right_stick_x;
//            prbot.getDrivetrain().setBase(leftMove + leftSlide + spin,
//                    rightMove - rightSlide - spin,
//                    leftMove - leftSlide + spin,
//                    rightMove + rightSlide - spin);
             rightSlide = gamepad1.right_stick_x;
             leftSlide = gamepad1.left_stick_x;
             rightMove = gamepad1.right_stick_y;
             leftMove = gamepad1.left_stick_y;
             prbot.getDrivetrain().setRightSide(rightSlide + rightMove, -rightSlide + rightMove);
             prbot.getDrivetrain().setLeftSide(-leftSlide + leftMove, leftSlide + leftMove);


            // Lift

            if (gamepad2.a){
                prbot.getLift().up();
            } else if (gamepad2.y){
                prbot.getLift().down();
            } else {
                prbot.getLift().stop();
            }

            // Intake

            prbot.getGp2().getLeftBumper().previous();
            prbot.getGp2().getLeftBumper().setState(gamepad2.left_bumper);
            prbot.getGp2().getLeftBumper().isPressed();

            prbot.getGp2().getRightBumper().previous();
            prbot.getGp2().getRightBumper().setState(gamepad2.right_bumper);
            prbot.getGp2().getRightBumper().isPressed();

            if (prbot.getGp2().getLeftBumper().getToggle()) {
                prbot.getIntake().close();
            } else {
                prbot.getIntake().open();
            }

            // Pivot

            if (gamepad2.dpad_down){
                prbot.getPivot().up();
            } else if (gamepad2.dpad_up){
                prbot.getPivot().down();
            } else {
                prbot.getPivot().stop();
            }

            prbot.getTelem().addIntake();
            prbot.getTelem().update();
        }
    }
}
