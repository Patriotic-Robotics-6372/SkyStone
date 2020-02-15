package org.firstinspires.ftc.teamcode.Subsystemv2.subsys.input;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;

@Disabled
public class DigitalButton implements Button {

    private boolean currentState, previousState;
    private boolean toggle = false;

    public DigitalButton() {

    }

    public void pressed() {
        currentState = true;
    }

    public void previous() {
        previousState = currentState;
    }

    public void released() {
        currentState = false;
    }

    public void setState(boolean state) {
        currentState = state;
    }

    @Override
    public boolean isPressed() {
        if (currentState == true && previousState == false) {
            toggle = !toggle;
        }
        return currentState == true && previousState == false;
    }

    @Override
    public boolean isHeld() {
        return currentState == true && previousState == true;
    }

    @Override
    public boolean isReleased() {
        return currentState == false && previousState == true;
    }

    @Override
    public boolean getCurrentState() {
        return currentState;
    }

    @Override
    public boolean getPreviousState() {
        return previousState;
    }

    public boolean getToggle() {
        return toggle;
    }

}
