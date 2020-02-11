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
    /**
     * Initialize directions of motors
     */
    public void init(){
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);

        stop();
    }

    /**
     * Set drivetrain to use or not use encoders
     * @param has to set if using encoders
     */
    public void setEnc(boolean has){
        if (has){
            resetEncoders();
            runUsingEncoders();
        } else {
            runWithoutEncoders();
        }
    }

    /**
     * @return the current mode of the motors
     */

    public Enum[] getModes(){
        return new Enum[]{frontRight.getMode(), frontLeft.getMode(), backRight.getMode(), backLeft.getMode()};
    }

    /**
     * Set drivetrain to use or not use brake
     * @param has to set if using brake
     */
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

    /**
     * Set drivetrain's telemetry
     * @param telem to set telemetry
     */
    public void setTelemetry(Telemetry telem){
        this.telemetry = telem;
    }

    /**
     * @return the motors' ZeroPowerBehavior state
     */
    public Enum[] getZPB(){
        return new Enum[]{frontRight.getZeroPowerBehavior(), frontLeft.getZeroPowerBehavior(), backRight.getZeroPowerBehavior(), backLeft.getZeroPowerBehavior()};
    }

    /**
     * Set the maximum speed the drivetrain can go at
     * @param power
     */
    public void setMaxPower(double power){
        this.power = power;
    }

    /**
     * @return the current maximum power it can do
     */
    public double getMaxPower(){
        return power;
    }

    /**
     * Set the speeds of each of the motors
     * @param fR frontRight motor speed
     * @param fL frontLeft motor speed
     * @param bR backRight motor speed
     * @param bL backLeft motor speed
     */
    public void setBase(double fR, double fL, double bR, double bL){
        frontRight.setPower(fR);
        frontLeft.setPower(fL);
        backRight.setPower(bR);
        backLeft.setPower(bL);
    }

    /**
     * Basic forward
     */
    public void forward(){
        setBase(-power, -power, -power, -power);
        baseStatus = Status.FORWARDS;
    }

    /**
     * Basic backward
     */
    public void backward() {
        setBase(power, power, power, power);
        baseStatus = Status.BACKWARDS;
    }

    /**
     * Moves left side of robot
     * @param pow set the speed
     */
    public void moveLeftSide(double pow){
        frontLeft.setPower(pow);
        backLeft.setPower(pow);
    }

    /**
     * Moves the right side of robot
     * @param pow set the speed
     */
    public void moveRightSide(double pow){
        frontRight.setPower(pow);
        backRight.setPower(pow);
    }

    /**
     * Basic left pivot turn
     */
    public void rotateLeft(){
        setBase(-power, power, -power, power);
        baseStatus = Status.LEFT;
    }

    /**
     * Basic right pivot turn
     */
    public void rotateRight(){
        setBase(power, -power, power, -power);
        baseStatus = Status.RIGHT;
    }

    /**
     * Turns approximately 90 degrees left
     */
    public void leftTurn(){
        setMaxPower(.8);
        rotateLeft(20, 5);
    }

    /**
     * Turns approximately 90 degrees right
     */
    public void rightTurn(){
        setMaxPower(.8);
        rotateRight(20, 5);
    }

    /**
     * Basic left strafe
     */
    public void strafeLeft(){
        setBase(-power, power, power, -power);
        baseStatus = Status.LEFT;
    }

    /**
     * Basic right strafe
     */
    public void strafeRight(){
        setBase(power, -power, -power, power);
        baseStatus = Status.RIGHT;
    }

    /**
     * Sets speed of all motors to zero
     */
    public void stop(){
        setBase(STOP, STOP, STOP, STOP);
        baseStatus = Status.NEUTRAL;
    }

    /**
     * Sets speed of left side to zero
     */
    public void stopLeftSide(){
        frontLeft.setPower(STOP);
        backLeft.setPower(STOP);
    }

    /**
     * Sets speed of right side to zero
     */
    public void stopRightSide(){
        frontRight.setPower(STOP);
        backRight.setPower(STOP);
    }

    /**
     * Encoder method for abstract strafing
     * @param leftInches
     * @param rightInches
     * @param timeoutS
     */
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

    /**
     * Encoder method for abstract movement besides strafing
     * @param leftInches
     * @param rightInches
     * @param timeoutS
     */
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

    /**
     * Encoder method for strafing left
     * @param inches how many inches / 2
     * @param timeoutS maximum time it should take
     */
    public void strafeLeft(double inches, double timeoutS){
        driveStrafe(inches, inches, timeoutS);
    }

    /**
     * Encoder method for strafing right
     * @param inches how many inches / 2
     * @param timeoutS maximum time it should take
     */
    public void strafeRight(double inches, double timeoutS){
        driveStrafe(-inches, -inches, timeoutS);
    }

    /**
     * Encoder method for backwards movement
     * @param inches how many inches
     * @param timeoutS maximum time it should take
     */
    public void backward(double inches, double timeoutS){
        drive(inches, inches, timeoutS);
        baseStatus = Status.BACKWARDS;
    }

    /**
     * Encoder method for forwards movement
     * @param inches how many inches
     * @param timeoutS maximum time it should take
     */
    public void forward(double inches, double timeoutS){
        drive(-inches, -inches, timeoutS);
        baseStatus = Status.FORWARDS;
    }

    /**
     * Encoder method for pivot turning left
     * @param inches how many rotations
     * @param timeoutS maximum time it should take
     */
    public void rotateLeft(double inches, double timeoutS){
        drive(inches, -inches, timeoutS);
        baseStatus = Status.LEFT;
    }

    /**
     * Encoder method for pivot turning right
     * @param inches how many rotations
     * @param timeoutS maximum time it should take
     */
    public void rotateRight(double inches, double timeoutS){
        drive(-inches, inches, timeoutS);
        baseStatus = Status.RIGHT;
    }

    /**
     * Resets encoder values of motors to zero
     */
    private void resetEncoders(){
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * Sets motors to use encoders
     */
    public void runUsingEncoders(){
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Sets motors to not use encoders
     */
    public void runWithoutEncoders(){
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * Sets motors' target positions
     * @param fR frontRight target position
     * @param fL frontLeft target position
     * @param bR backRight target position
     * @param bL backLeft target position
     */
    private void setTargetPositions(int fR, int fL, int bR, int bL){
        frontRight.setTargetPosition(fR);
        frontLeft.setTargetPosition(fL);
        backRight.setTargetPosition(bR);
        backLeft.setTargetPosition(bL);
    }

    /**
     * Sets motors to go to position
     */
    private void runToPositions(){
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * @return true if all motors are currently going to position, false otherwise
     */
    public boolean anyBusy(){
        return frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy();
    }

    /**
     * @return status of base
     */
    public Status getStatus(){
        return baseStatus;
    }

    /**
     * @return speeds of motors
     */
    public double[] getSpeeds(){
        return new double[]{frontLeft.getPower(), frontRight.getPower(), backLeft.getPower(), backRight.getPower()};
    }

    /**
     * @return frontRight motor encoder position
     */
    public int getFrontRightEncoder(){
        return frontRight.getCurrentPosition();
    }

    /**
     * @return frontLeft motor encoder position
     */
    public int getFrontLeftEncoder(){
        return frontLeft.getCurrentPosition();
    }

    /**
     * @return backRight motor encoder position
     */
    public int getBackRightEncoder(){
        return backRight.getCurrentPosition();
    }

    /**
     * @return backLeft motor encoder position
     */
    public int getBackLeftEncoder(){
        return backLeft.getCurrentPosition();
    }

    /**
     * @return tick goal of left side
     */
    public int getLeftTickGoal(){
        return leftTickGoal;
    }

    /**
     * @return tick goal of right side
     */
    public int getRightTickGoal(){
        return rightTickGoal;
    }

    /**
     * @return Telemetry method to return encoder positions and tick goal
     */
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
