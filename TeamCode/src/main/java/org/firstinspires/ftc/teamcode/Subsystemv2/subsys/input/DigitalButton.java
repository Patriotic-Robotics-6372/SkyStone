package org.firstinspires.ftc.teamcode.Subsystemv2.subsys.input;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;

/**
 * Date: 2/15/20
 * Author: Jacob Marinas
 * Lets buttons have greater functionality. Use this class to tell if the button has been pressed, held, or released.
 */
@Disabled
public class DigitalButton implements Button {

    private boolean currentState, previousState;
    private int states = 2;
    private boolean toggle = false;

    public DigitalButton() {

    }

    public DigitalButton(int states) {
        this.states = states;

    }

    /**
     * Sets currentState to true
     */
    public void pressed() {
        currentState = true;
    }

    /**
     * Sets previousState to currentState
     */
    public void previous() {
        previousState = currentState;
    }

    /**
     * Sets currentState to false
     */
    public void released() {
        currentState = false;
    }

    /**
     * @param state of currentState
     */
    public void setState(boolean state) {
        currentState = state;
    }

    /**
     * When method is true, it inverts the toggle
     * @return true if button has been pressed on the first tick, false otherwise
     */
    @Override
    public boolean isPressed() {
        if (currentState == true && previousState == false) {
            toggle = !toggle;
        }
        return currentState == true && previousState == false;
    }

    /**
     * @return true if button has been pressed for more than one tick, false otherwise
     */
    @Override
    public boolean isHeld() {
        return currentState == true && previousState == true;
    }

    /**
     * @return true if button has been released for one tick, false otherwise
     */
    @Override
    public boolean isReleased() {
        return currentState == false && previousState == true;
    }

    /**
     * @return currentState
     */
    @Override
    public boolean getCurrentState() {
        return currentState;
    }

    /**
     * @return previousState
     */
    @Override
    public boolean getPreviousState() {
        return previousState;
    }

    /**
     * @return toggle; on or off
     */
    public boolean getToggle() {
        return toggle;
    }

}
