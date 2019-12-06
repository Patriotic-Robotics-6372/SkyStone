package org.firstinspires.ftc.teamcode.Deprecated.Practice;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

    /*
     author: Chris Tolosa
     date: 10/1/19
     summary: I setup the encoder drive distance
     */
@Disabled
@Autonomous (name="Autonomous Program")
public class AutonomousPR extends LinearOpMode {
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor leftLift;
    DcMotor rightLift;
    DcMotor leftPivot;
    DcMotor rightPivot;

    Servo pinch;
    Servo leftT;
    Servo rightT;


    double TICKS_PER_IN = 1120/(4*Math.PI);

    public void encoderDrive (double inches,double leftPower, double rightPower) {
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int leftWheelDistance = frontLeft.getCurrentPosition () + (int) (TICKS_PER_IN * inches);
        int rightWheelDistance = frontRight.getCurrentPosition() + (int) (TICKS_PER_IN * inches);

        frontLeft.setTargetPosition(leftWheelDistance);
        frontRight.setTargetPosition(rightWheelDistance);

        while( Math.abs(frontLeft.getCurrentPosition()) < leftWheelDistance ||
                Math.abs(frontRight.getCurrentPosition()) < rightWheelDistance) {

            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            frontRight.setPower(rightPower);
            frontLeft.setPower(leftPower);

            backLeft.setPower(frontLeft.getPower());
            backRight.setPower(frontRight.getPower());

            telemetry.addData("Left Enc ", frontLeft.getCurrentPosition());
            telemetry.addData("Right Enc ", frontRight.getCurrentPosition());
            updateTelemetry(telemetry);
            telemetry.update();
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

    }

    @Override
    public void runOpMode() throws InterruptedException {
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        leftLift = hardwareMap.dcMotor.get("leftLift");
        rightLift = hardwareMap.dcMotor.get("rightLift");
        leftPivot = hardwareMap.dcMotor.get("leftPivot");
        rightPivot = hardwareMap.dcMotor.get("rightPivot");

        pinch = hardwareMap.servo.get("pinch");
        leftT = hardwareMap.servo.get("leftT");
        rightT = hardwareMap.servo.get("rightT");

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        pinch.setPosition(0);
        leftT.setPosition(0);
        rightT.setPosition(0);

        telemetry.addData("Autonomous", "Robot is Ready");
        telemetry.update();

        waitForStart();

        //move pivots
        leftPivot.setPower(.1);
        rightPivot.setPower(.1);
        sleep(50); //change ms to go exactly upright

        //move forward for 3 inches
        encoderDrive(3,.5,.5);

        //turn right
        backLeft.setPower(.5);
        frontLeft.setPower(.5);


    }
}
