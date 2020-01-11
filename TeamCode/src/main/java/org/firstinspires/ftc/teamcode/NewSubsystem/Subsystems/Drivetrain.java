package org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Drivetrain subsystem
 */

public class Drivetrain implements Constants {

    private DcMotor frontRight, frontLeft, backRight, backLeft;
    private double power = STOP;
    private Status baseStatus = Status.NEUTRAL;
    private int tickGoal;
    private Telemetry telemetry;

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
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else {
            frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            //backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            //backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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

    public void forward(){
        frontRight.setPower(-power);
        frontLeft.setPower(-power);
        backRight.setPower(-power);
        backLeft.setPower(-power);
        baseStatus = Status.FORWARDS;
    }
    
    public void backward() {
        frontRight.setPower(power);
        frontLeft.setPower(power);
        backRight.setPower(power);
        backLeft.setPower(power);
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
        frontRight.setPower(-power);
        frontLeft.setPower(power);
        backRight.setPower(-power);
        backLeft.setPower(power);
        baseStatus = Status.LEFT;
    }

    public void rotateRight(){
        frontRight.setPower(power);
        frontLeft.setPower(-power);
        backRight.setPower(power);
        backLeft.setPower(-power);
        baseStatus = Status.RIGHT;
    }

    public void strafeLeft(){
        frontRight.setPower(-power);
        frontLeft.setPower(power);
        backRight.setPower(power);
        backLeft.setPower(-power);
        baseStatus = Status.LEFT;
    }

    public void strafeRight(){
        frontRight.setPower(power);
        frontLeft.setPower(-power);
        backRight.setPower(-power);
        backLeft.setPower(power);
        baseStatus = Status.RIGHT;
    }

    public void stop(){
        frontRight.setPower(STOP);
        frontLeft.setPower(STOP);
        backRight.setPower(STOP);
        backLeft.setPower(STOP);
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

    public void forward(double inches){
        tickGoal = (int) (TICKS_PER_IN * inches);
        resetEncoders();
        runUsingEncoders();
        setTargetPositions(tickGoal);
        runToPositions();
        while(getFrontLeftEncoder() < tickGoal || getFrontRightEncoder() < tickGoal){
            frontRight.setPower(power);
            frontLeft.setPower(power);
            backRight.setPower(power);
            backLeft.setPower(power);


            telemEncoderPos();

        }
        stop();
    }

    public void backward(double inches){
        tickGoal = (int) (TICKS_PER_IN * inches);
        resetEncoders();
        runUsingEncoders();
        setTargetPositions(-tickGoal);
        runToPositions();
        while(getFrontLeftEncoder() > tickGoal || getFrontRightEncoder() > tickGoal){
            frontRight.setPower(-power);
            frontLeft.setPower(-power);
            backRight.setPower(-power);
            backLeft.setPower(-power);


            telemEncoderPos();

        }
        stop();
    }

    public void rotateLeft(double inches){
        tickGoal = (int) (TICKS_PER_IN * inches);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setTargetPosition(tickGoal);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(anyBusy()){
            frontRight.setPower(power);
            frontLeft.setPower(-(power/2));
            backRight.setPower(power);
            backLeft.setPower(0);


            telemFrontRightPos();

        }
        stop();
    }

    public void rotateRight(double inches){
        tickGoal = (int) (TICKS_PER_IN * inches);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setTargetPosition(tickGoal);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(anyBusy()){
            frontRight.setPower(-(power/2));
            frontLeft.setPower(power);
            backRight.setPower(0);
            backLeft.setPower(power);


            telemFrontLeftPos();

        }
        stop();
    }

    public void resetEncoders(){
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void runUsingEncoders(){
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setTargetPositions(int pos){
        frontRight.setTargetPosition(pos);
        frontLeft.setTargetPosition(pos);
    }

    public void runToPositions(){
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public boolean anyBusy(){
        return frontLeft.isBusy() || frontRight.isBusy();
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

    public int getTickGoal(){
        return tickGoal;
    }

    public void telemEncoderPos(){
        addTickGoal();
        addFrontLeftEncoderPos();
        addFrontRightEncoderPos();
        telemetry.update();
    }

    public void telemFrontRightPos(){
        addTickGoal();
        addFrontRightEncoderPos();
        telemetry.update();
    }

    public void telemFrontLeftPos(){
        addTickGoal();
        addFrontLeftEncoderPos();
        telemetry.update();
    }

    public void addTickGoal(){
        telemetry.addData("TickGoal", getTickGoal());
    }

    public void addFrontLeftEncoderPos(){
        telemetry.addData("FrontLeft", getFrontLeftEncoder());
    }

    public void addFrontRightEncoderPos(){
        telemetry.addData("FrontRight", getFrontRightEncoder());
    }
}
