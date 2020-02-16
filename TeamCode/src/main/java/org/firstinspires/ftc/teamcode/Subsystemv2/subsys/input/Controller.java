package org.firstinspires.ftc.teamcode.Subsystemv2.subsys.input;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;

/**
 * Date: 2/12/20
 * Author: Jacob Marinas
 * Container class to hold DigitalButton and AnalogButton classes for each input
 */
public class Controller implements Constants {

    private AnalogButton left_stick_x;
    private AnalogButton left_stick_y;
    private AnalogButton right_stick_x;
    private AnalogButton right_stick_y;
    private AnalogButton left_trigger;
    private AnalogButton right_trigger;
    private DigitalButton a;
    private DigitalButton b;
    private DigitalButton x;
    private DigitalButton y;
    private DigitalButton dpad_up;
    private DigitalButton dpad_down;
    private DigitalButton dpad_left;
    private DigitalButton dpad_right;
    private DigitalButton left_bumper;
    private DigitalButton right_bumper;
    private DigitalButton right_stick_button;
    private DigitalButton left_stick_button;
    private Gamepad gamepad;

    public Controller() {
        this.left_stick_x = new AnalogButton();
        this.left_stick_y = new AnalogButton();
        this.right_stick_x = new AnalogButton();
        this.right_stick_y = new AnalogButton();
        this.left_trigger = new AnalogButton();
        this.right_trigger = new AnalogButton();
        this.a = new DigitalButton();
        this.b = new DigitalButton();
        this.x = new DigitalButton();
        this.y = new DigitalButton();
        this.dpad_up = new DigitalButton();
        this.dpad_down = new DigitalButton();
        this.dpad_left = new DigitalButton();
        this.dpad_right = new DigitalButton();
        this.left_bumper = new DigitalButton();
        this.right_bumper = new DigitalButton();
        this.right_stick_button = new DigitalButton();
        this.left_stick_button = new DigitalButton();
        //this.gamepad = gamepad;
    }

    /**
     * @return A button
     */
    public DigitalButton getA() {
        return a;
    }

    /**
     * @return B button
     */
    public DigitalButton getB() {
        return b;
    }

    /**
     * @return X button
     */
    public DigitalButton getX() {
        return x;
    }

    /**
     * @return Y button
     */
    public DigitalButton getY() {
        return y;
    }

    /**
     * @return Left bumper button
     */
    public DigitalButton getLeftBumper() {
        return left_bumper;
    }

    /**
     * @return Right bumper button
     */
    public DigitalButton getRightBumper() {
        return right_bumper;
    }

    /**
     * @return Dpad up button
     */
    public DigitalButton getDpadUp() {
        return dpad_up;
    }

    /**
     * @return Dpad down button
     */
    public DigitalButton getDpadDown() {
        return dpad_down;
    }

    /**
     * @return Dpad left button
     */
    public DigitalButton getDpadLeft() {
        return dpad_left;
    }

    /**
     * @return Dpad right button
     */
    public DigitalButton getDpadRight() {
        return dpad_right;
    }

    /**
     * Sets previousState to currentState of all buttons
     */
    public void updatePrevious() {
        a.previous();
        b.previous();
        x.previous();
        y.previous();
        dpad_up.previous();
        dpad_down.previous();
        dpad_left.previous();
        dpad_right.previous();
        left_stick_button.previous();
        right_stick_button.previous();
    }

    /**
     * Sets state of currentState to gamepad buttons
     * @param a state
     * @param b state 
     * @param x state 
     * @param y state
     * @param dpad_up state
     * @param dpad_down state
     * @param dpad_left state
     * @param dpad_right state
     * @param right_stick_button state
     * @param left_stick_button state
     */
    public void updateCurrent(boolean a, boolean b, boolean x, boolean y,
                              boolean dpad_up, boolean dpad_down, boolean dpad_left, boolean dpad_right,
                              boolean right_stick_button, boolean left_stick_button) {
        this.a.setState(a);
        this.b.setState(b);
        this.x.setState(x);
        this.y.setState(y);
        this.dpad_up.setState(dpad_up);
        this.dpad_down.setState(dpad_down);
        this.dpad_left.setState(dpad_left);
        this.dpad_right.setState(dpad_right);
        this.right_stick_button.setState(right_stick_button);
        this.left_stick_button.setState(left_stick_button);
    }
}
