package org.firstinspires.ftc.teamcode.CuttingEdge.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.CuttingEdge.Hardware.PRRobotv3;
import org.firstinspires.ftc.teamcode.Stable.Hardware.PRRobot;

/*
    author: Jacob Marinas
    date: 12/18/19
    desc: teleop w/ PRRobotv3. left side / right side goes forwards / backwards, strafe, pivot, lift, intake.
 */

//@Disabled
@TeleOp(name = "Driver Control w/ PRRobotv3")
public class TeleOpv8 extends OpMode {
    // use PRRobot that has defined things already; takes in speed parameters
    PRRobotv3 prBot =
            new PRRobotv3("stable");
    @Override
    public void init() {
        prBot.init(hardwareMap);
        prBot.useBrake(false);
        prBot.useEnc(false);
        prBot.setTelemetry(telemetry);
        prBot.start();
    }
    @Override
    public void loop() {
        // define controller variables
        // should we define this in PRRobot? even if autonomous doesn't require it...
        prBot.leftStick1 = gamepad1.left_stick_y;
        prBot.rightStick1 = -gamepad1.right_stick_y;
        prBot.dpadLeft1 = gamepad1.dpad_left;
        prBot.dpadRight1 = gamepad1.dpad_right;
        prBot.dpadUp1 = gamepad1.dpad_up;
        prBot.dpadDown1 = gamepad1.dpad_down;
        prBot.dpadUp2 = gamepad2.dpad_up;
        prBot.dpadDown2 = gamepad2.dpad_down;
        prBot.a2 = gamepad2.a;
        prBot.b2 = gamepad2.b;
        prBot.x2 = gamepad2.x;
        prBot.y2 = gamepad2.y;
        prBot.rightBumper2 = gamepad2.right_bumper;
        prBot.leftBumper2 = gamepad2.left_bumper;

        // to check if controller is being used; true if any of the buttons are pressed, false else

        if (!prBot.getController1() && !prBot.getController2()) {
            prBot.robotStatus();
        }

        // tank drive right
        if (Math.abs(prBot.rightStick1) > .1) {
            // speed modifier to find actual speed
            prBot.rightSideSpeed = prBot.rightStick1 * prBot.baseSpeed;
            // limiting speed to valid values
            prBot.rightSideSpeed = Range.clip(prBot.rightSideSpeed, -1, 1);
            // set powers of right side
            prBot.frontRight.setPower(prBot.rightSideSpeed);
            prBot.backRight.setPower(prBot.rightSideSpeed);
            // set status of right side
            if (prBot.rightStick1 >= .1) {
                prBot.rightSideStatus = PRRobotv3.Status.FORWARDS;
            } else if (prBot.rightStick1 <= -.1) {
                prBot.rightSideStatus = PRRobotv3.Status.BACKWARDS;
            } else {
                prBot.rightSideStatus = PRRobotv3.Status.NEUTRAL;
            }
            prBot.robotStatus();
        } else {
            prBot.frontRight.setPower(prBot.STOP);
            prBot.backRight.setPower(prBot.STOP);
            prBot.rightSideStatus = PRRobotv3.Status.NEUTRAL;
        }
        // tank drive left
        if (Math.abs(prBot.leftStick1) > .1) {
            prBot.leftSideSpeed = prBot.leftStick1 * prBot.baseSpeed;
            prBot.leftSideSpeed = Range.clip(prBot.leftSideSpeed, -1, 1);
            prBot.frontLeft.setPower(prBot.leftSideSpeed);
            prBot.backLeft.setPower(prBot.leftSideSpeed);
            if (prBot.leftStick1 >= .1) {
                prBot.leftSideStatus = PRRobotv3.Status.BACKWARDS;
            } else if (prBot.leftStick1 <= -.1) {
                prBot.leftSideStatus = PRRobotv3.Status.FORWARDS;
            } else {
                prBot.leftSideStatus = PRRobotv3.Status.NEUTRAL;
            }
            prBot.robotStatus();
        } else {
            prBot.frontLeft.setPower(prBot.STOP);
            prBot.backLeft.setPower(prBot.STOP);
            prBot.leftSideStatus = PRRobotv3.Status.NEUTRAL;
        }

        // strafing left
        if (prBot.dpadLeft1) {
            prBot.frontLeft.setPower(-prBot.strafeSpeed);
            prBot.frontRight.setPower(prBot.strafeSpeed);
            prBot.backLeft.setPower(prBot.strafeSpeed);
            prBot.backRight.setPower(-prBot.strafeSpeed);
            prBot.strafeStatus = PRRobotv3.Status.LEFT;
            prBot.robotStatus();
        } else {
            prBot.stop();
            prBot.strafeStatus = PRRobotv3.Status.NEUTRAL;
        }
        // strafing right
        if (prBot.dpadRight1) {
            prBot.frontLeft.setPower(prBot.strafeSpeed);
            prBot.frontRight.setPower(-prBot.strafeSpeed);
            prBot.backLeft.setPower(-prBot.strafeSpeed);
            prBot.backRight.setPower(prBot.strafeSpeed);
            prBot.strafeStatus = PRRobotv3.Status.RIGHT;
            prBot.robotStatus();
        } else {
            prBot.stop();
            prBot.strafeStatus = PRRobotv3.Status.NEUTRAL;
        }
        /*
        if (prBot.dpadUp1) {
            prBot.speedChange(.1, 1);
        }
        if (prBot.dpadDown1) {
            prBot.speedChange(.1, -1);
        }
        */

        if (prBot.b2) {
            prBot.lift.setPower(prBot.liftSpeed);
            prBot.liftStatus = PRRobotv3.Status.UP;
            prBot.robotStatus();
        } else {
            prBot.lift.setPower(prBot.STOP);
            prBot.liftStatus = PRRobotv3.Status.NEUTRAL;
        }
        //down lift
        if (prBot.a2) {
            prBot.lift.setPower(-prBot.liftSpeed);
            prBot.liftStatus = PRRobotv3.Status.DOWN;
            prBot.robotStatus();
        } else {
            prBot.lift.setPower(prBot.STOP);
            prBot.liftStatus = PRRobotv3.Status.NEUTRAL;
        }

        if (prBot.rightBumper2) {
            prBot.leftPinch.setPower(prBot.close);
            prBot.rightPinch.setPower(prBot.open);
            prBot.intakeStatus = PRRobotv3.Status.CLOSE;
            prBot.robotStatus();
        } else {
            prBot.intakeStatus = PRRobotv3.Status.NEUTRAL;
        }
        if (prBot.leftBumper2) {
            prBot.leftPinch.setPower(prBot.open);
            prBot.rightPinch.setPower(prBot.close);
            prBot.intakeStatus = PRRobotv3.Status.OPEN;
            prBot.robotStatus();
        } else {
            prBot.intakeStatus = PRRobotv3.Status.NEUTRAL;
        }

        //pivot intake up
        if (prBot.dpadUp2) {
            prBot.leftPivot.setPower(prBot.pivotIntakeSpeed);
            prBot.rightPivot.setPower(prBot.pivotIntakeSpeed);
            prBot.pivotStatus = PRRobotv3.Status.UP;
            prBot.robotStatus();
        } else if (prBot.dpadDown2) {
            prBot.leftPivot.setPower(-prBot.pivotIntakeSpeed);
            prBot.rightPivot.setPower(-prBot.pivotIntakeSpeed);
            prBot.pivotStatus = PRRobotv3.Status.DOWN;
            prBot.robotStatus();
        } else {
            prBot.leftPivot.setPower(prBot.STOP);
            prBot.rightPivot.setPower(prBot.STOP);
            prBot.pivotStatus = PRRobotv3.Status.NEUTRAL;
        }
    }
}

