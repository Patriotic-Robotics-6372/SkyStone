package org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Drivetrain subsystem
 */

public class Drivetrain implements Constants {

    private DcMotor frontRight, frontLeft, backRight, backLeft;
    private double power = STOP;
    private Status baseStatus = Status.NEUTRAL;
    private int leftTickGoal, rightTickGoal;
    private Telemetry telemetry;
    private ElapsedTime runtime = new ElapsedTime();

    public Drivetrain(DcMotor fL, DcMotor fR, DcMotor bL, DcMotor bR){
        frontLeft = fL;
        frontRight = fR;
        backLeft = bL;
        backRight = bR;
        
        init();
    }
    
    public void init(){
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);

        stop();
    }
    
    public void setEnc(boolean has){
        if (has){
            resetEncoders();
            runUsingEncoders();
        } else {
            runWithoutEncoders();
        }
    }

    public Enum[] getModes(){
        return new Enum[]{frontRight.getMode(), frontLeft.getMode(), backRight.getMode(), backLeft.getMode()};
    }

    public void setBrake(boolean has){
        if (has) {
            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } else {
            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }

    public void setTelemetry(Telemetry telem){
        this.telemetry = telem;
    }

    public Enum[] getZPB(){
        return new Enum[]{frontRight.getZeroPowerBehavior(), frontLeft.getZeroPowerBehavior(), backRight.getZeroPowerBehavior(), backLeft.getZeroPowerBehavior()};
    }

    public void setMaxPower(double power){
        this.power = power;
    }

    public double getMaxPower(){
        return power;
    }

    public void setBase(double fR, double fL, double bR, double bL){
        frontRight.setPower(fR);
        frontLeft.setPower(fL);
        backRight.setPower(bR);
        backLeft.setPower(bL);
    }

    public void forward(){
        setBase(-power, -power, -power, -power);
        baseStatus = Status.FORWARDS;
    }
    
    public void backward() {
        setBase(power, power, power, power);
        baseStatus = Status.BACKWARDS;
    }

    public void moveLeftSide(double pow){
        frontLeft.setPower(pow);
        backLeft.setPower(pow);
    }

    public void moveRightSide(double pow){
        frontRight.setPower(pow);
        backRight.setPower(pow);
    }

    public void rotateLeft(){
        setBase(-power, power, -power, power);
        baseStatus = Status.LEFT;
    }

    public void rotateRight(){
        setBase(power, -power, power, -power);
        baseStatus = Status.RIGHT;
    }

    public void leftTurn(){
        setMaxPower(.8);
        rotateLeft(19.5, 5);
    }

    public void rightTurn(){
        setMaxPower(.8);
        rotateRight(19.5, 5);
    }

    public void strafeLeft(){
        setBase(-power, power, power, -power);
        baseStatus = Status.LEFT;
    }

    public void strafeRight(){
        setBase(power, -power, -power, power);
        baseStatus = Status.RIGHT;
    }

    public void stop(){
        setBase(STOP, STOP, STOP, STOP);
        baseStatus = Status.NEUTRAL;
    }

    public void stopLeftSide(){
        frontLeft.setPower(STOP);
        backLeft.setPower(STOP);
    }

    public void stopRightSide(){
        frontRight.setPower(STOP);
        backRight.setPower(STOP);
    }

    public void driveStrafe(double leftInches, double rightInches, double timeoutS){
        leftTickGoal = (int) (TICKS_PER_IN * leftInches);
        rightTickGoal = (int) (TICKS_PER_IN * rightInches);
        resetEncoders();
        setTargetPositions(-rightTickGoal, leftTickGoal, rightTickGoal, -leftTickGoal);
        runToPositions();

        runtime.reset();
        setBase(power, power, power, power);
        while (anyBusy() &&
                (runtime.seconds() < timeoutS)) {
            telemEncoderPos();
        }
        stop();
        runUsingEncoders();
    }

    public void drive(double leftInches, double rightInches, double timeoutS){
        leftTickGoal = (int) (TICKS_PER_IN * leftInches);
        rightTickGoal = (int) (TICKS_PER_IN * rightInches);
        resetEncoders();
        setTargetPositions(rightTickGoal, leftTickGoal, rightTickGoal, leftTickGoal);
        runToPositions();

        runtime.reset();
        setBase(power, power, power, power);
        while (anyBusy() &&
                (runtime.seconds() < timeoutS)) {
            telemEncoderPos();
        }
        stop();
        runUsingEncoders();
    }

    public void strafeLeft(double inches, double timeoutS){
        driveStrafe(inches, inches, timeoutS);
    }

    public void strafeRight(double inches, double timeoutS){
        driveStrafe(-inches, -inches, timeoutS);
    }

    public void backward(double inches, double timeoutS){
        drive(inches, inches, timeoutS);
        baseStatus = Status.BACKWARDS;
    }

    public void forward(double inches, double timeoutS){
        drive(-inches, -inches, timeoutS);
        baseStatus = Status.FORWARDS;
    }

    public void rotateLeft(double inches, double timeoutS){
        drive(inches, -inches, timeoutS);
        baseStatus = Status.LEFT;
    }

    public void rotateRight(double inches, double timeoutS){
        drive(-inches, inches, timeoutS);
        baseStatus = Status.RIGHT;
    }

    private void resetEncoders(){
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void runUsingEncoders(){
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void runWithoutEncoders(){
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    private void setTargetPositions(int fR, int fL, int bR, int bL){
        frontRight.setTargetPosition(fR);
        frontLeft.setTargetPosition(fL);
        backRight.setTargetPosition(bR);
        backLeft.setTargetPosition(bL);
    }

    private void runToPositions(){
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public boolean anyBusy(){
        return frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy();
    }

    public Status getStatus(){
        return baseStatus;
    }

    public double[] getSpeeds(){
        return new double[]{frontLeft.getPower(), frontRight.getPower(), backLeft.getPower(), backRight.getPower()};
    }

    public int getFrontRightEncoder(){
        return frontRight.getCurrentPosition();
    }

    public int getFrontLeftEncoder(){
        return frontLeft.getCurrentPosition();
    }

    public int getBackRightEncoder(){
        return backRight.getCurrentPosition();
    }

    public int getBackLeftEncoder(){
        return backLeft.getCurrentPosition();
    }

    public int getLeftTickGoal(){
        return leftTickGoal;
    }

    public int getRightTickGoal(){
        return rightTickGoal;
    }

    private void telemEncoderPos(){
        addTickGoal();
        addFrontLeftEncoderPos();
        addFrontRightEncoderPos();
        addBackLeftEncoderPos();
        addBackRightEncoderPos();
        telemetry.update();
    }

    private void telemFrontRightPos(){
        addTickGoal();
        addFrontRightEncoderPos();
        telemetry.update();
    }

    private void telemFrontLeftPos(){
        addTickGoal();
        addFrontLeftEncoderPos();
        telemetry.update();
    }

    private void telemBackRightPos(){
        addTickGoal();
        addBackRightEncoderPos();
        telemetry.update();
    }

    private void telemBackLeftPos(){
        addTickGoal();
        addBackLeftEncoderPos();
        telemetry.update();
    }


    public void addTickGoal(){
        addLeftTickGoal();
        addRightTickGoal();
    }

    public void addLeftTickGoal(){
        telemetry.addData("LeftTickGoal", getLeftTickGoal());
    }

    public void addRightTickGoal(){
        telemetry.addData("RightTickGoal", getRightTickGoal());
    }

    private void addFrontLeftEncoderPos(){
        telemetry.addData("FrontLeft", getFrontLeftEncoder());
    }

    private void addFrontRightEncoderPos(){
        telemetry.addData("FrontRight", getFrontRightEncoder());
    }

    private void addBackLeftEncoderPos(){
        telemetry.addData("BackLeft", getBackLeftEncoder());
    }

    private void addBackRightEncoderPos(){
        telemetry.addData("BackRight", getBackRightEncoder());
    }
}
