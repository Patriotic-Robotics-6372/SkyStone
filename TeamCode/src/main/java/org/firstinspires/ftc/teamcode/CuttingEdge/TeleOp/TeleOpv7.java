package org.firstinspires.ftc.teamcode.CuttingEdge.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.teamcode.CuttingEdge.Hardware.PRRobot;

/*
    author: Jacob Marinas
    date: 11/18/19
    desc: teleop w/ PRRobot. left side / right side goes forwards / backwards, strafe, pivot, lift, intake.
 */

@TeleOp(name = "Driver Control w/ PRRobot")
public class TeleOpv7 extends OpMode {
    // use PRRobot that has defined things already; takes in speed parameters
    PRRobot prBot =
            new PRRobot();
    //hwMap = new HardwareMap();
    @Override
    public void init() {
        prBot.init(hardwareMap);
        prBot.noBrake();
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

        prBot.controller1 = (prBot.leftStick1 != 0) || (prBot.rightStick1 != 0) || prBot.dpadLeft1 || prBot.dpadRight1 || prBot.dpadUp1 || prBot.dpadDown1;
        prBot.controller2 = prBot.dpadUp2 || prBot.dpadDown2 || prBot.a2 || prBot.b2 || prBot.rightBumper2 || prBot.leftBumper2 || prBot.x2 || prBot.y2;

        if (!prBot.controller1 && !prBot.controller2) {
            prBot.robotStatus(telemetry);
        }

        // tank drive right
        if (Math.abs(prBot.rightStick1) > .1) {
            // speed modifier to find actual speed
            prBot.rightSideSpeed = prBot.rightStick1 * prBot.baseSpeed;
            // limiting speed to valid values
            if (prBot.rightSideSpeed > 1) {
                prBot.rightSideSpeed = 1;
            } else if (prBot.rightSideSpeed < -1) {
                prBot.rightSideSpeed = -1;
            }
            // set powers of right side
            prBot.frontRight.setPower(prBot.rightSideSpeed);
            prBot.backRight.setPower(prBot.rightSideSpeed);
            // set status of right side
            if (prBot.rightStick1 >= .1) {
                prBot.rightSideStatus = 1;
            } else if (prBot.rightStick1 <= -.1) {
                prBot.rightSideStatus = -1;
            } else {
                prBot.rightSideStatus = 0;
            }
            prBot.robotStatus(telemetry);
        } else {
            prBot.frontRight.setPower(0);
            prBot.backRight.setPower(0);
            prBot.rightSideStatus = 0;
        }
        // tank drive left
        if (Math.abs(prBot.leftStick1) > .1) {
            if (prBot.leftSideSpeed > 1) {
                prBot.leftSideSpeed = 1;
            } else if (prBot.leftSideSpeed < -1) {
                prBot.leftSideSpeed = -1;
            }
            prBot.frontLeft.setPower(prBot.leftSideSpeed);
            prBot.backLeft.setPower(prBot.leftSideSpeed);
            if (prBot.leftStick1 >= .1) {
                prBot.leftSideStatus = 1;
            } else if (prBot.leftStick1 <= -.1) {
                prBot.leftSideStatus = -1;
            } else {
                prBot.leftSideStatus = 0;
            }
            prBot.robotStatus(telemetry);
        } else {
            prBot.frontLeft.setPower(0);
            prBot.backLeft.setPower(0);
            prBot.leftSideStatus = 0;
        }

        // strafing left
        if (prBot.dpadLeft1) {
            prBot.frontLeft.setPower(-prBot.strafeSpeed);
            prBot.frontRight.setPower(prBot.strafeSpeed);
            prBot.backLeft.setPower(prBot.strafeSpeed);
            prBot.backRight.setPower(-prBot.strafeSpeed);
            prBot.strafeStatus = -1;
            prBot.robotStatus(telemetry);
        } else {
            prBot.frontLeft.setPower(0);
            prBot.frontRight.setPower(0);
            prBot.backLeft.setPower(0);
            prBot.backRight.setPower(0);
            prBot.strafeStatus = 0;
        }
        // strafing right
        if (prBot.dpadRight1) {
            prBot.frontLeft.setPower(prBot.strafeSpeed);
            prBot.frontRight.setPower(-prBot.strafeSpeed);
            prBot.backLeft.setPower(-prBot.strafeSpeed);
            prBot.backRight.setPower(prBot.strafeSpeed);
            prBot.strafeStatus = 1;
            prBot.robotStatus(telemetry);
        } else {
            prBot.frontLeft.setPower(0);
            prBot.frontRight.setPower(0);
            prBot.backLeft.setPower(0);
            prBot.backRight.setPower(0);
            prBot.strafeStatus = 0;
        }

        if (prBot.dpadUp1) {
            prBot.speedChange(.1, 1);
        }
        if (prBot.dpadDown1) {
            prBot.speedChange(.1, -1);
        }

        if (prBot.b2) {
            prBot.lift.setPower(prBot.liftSpeed);
            prBot.liftStatus = 1;
            prBot.robotStatus(telemetry);
        } else {
            prBot.lift.setPower(0);
            prBot.liftStatus = 0;
        }
        //down lift
        if (prBot.a2) {
            prBot.lift.setPower(-prBot.liftSpeed);
            prBot.liftStatus = -1;
            prBot.robotStatus(telemetry);
        } else {
            prBot.lift.setPower(0);
            prBot.liftStatus = 0;
        }

        if (prBot.x2) {
            prBot.liftSpeed -= .1;
        }

        if (prBot.y2) {
            prBot.liftSpeed += .1;
        }

        if (prBot.rightBumper2) {
            prBot.leftPinch.setPower(prBot.close);
            prBot.rightPinch.setPower(prBot.open);
            prBot.intakeStatus = -1;
            prBot.robotStatus(telemetry);
        } else {
            prBot.intakeStatus = 0;
        }
        if (prBot.leftBumper2) {
            prBot.leftPinch.setPower(prBot.open);
            prBot.rightPinch.setPower(prBot.close);
            prBot.intakeStatus = 1;
            prBot.robotStatus(telemetry);
        } else {
            prBot.intakeStatus = 0;
        }

        //pivot intake up
        if (prBot.dpadUp2) {
            prBot.leftPivot.setPower(prBot.pivotIntakeSpeed);
            prBot.rightPivot.setPower(prBot.pivotIntakeSpeed);
            prBot.pivotStatus = 1;
            prBot.robotStatus(telemetry);
        } else if (prBot.dpadDown2) {
            prBot.leftPivot.setPower(-prBot.pivotIntakeSpeed);
            prBot.rightPivot.setPower(-prBot.pivotIntakeSpeed);
            prBot.pivotStatus = -1;
            prBot.robotStatus(telemetry);
        } else {
            prBot.leftPivot.setPower(0);
            prBot.rightPivot.setPower(0);
            prBot.pivotStatus = 0;
        }
    }
}

