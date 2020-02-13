package org.firstinspires.ftc.teamcode.Subsystemv2.subsystems.input;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsystems.Constants;

@Disabled
public class DigitalButton implements Button {

    private Gamepad gamepad;
    private int button;
    private String currentButton;
    private boolean currentState, previousState;

    public DigitalButton(Gamepad gamepad, int button) {
        this.gamepad = gamepad;
        this.button = button;
    }


    public void setButton(int button) {
        switch (button) {
            case Constants.GAMEPAD_A:
                currentButton = "gamepad.a";
                break;
            case Constants.GAMEPAD_B:
                currentButton = "gamepad.b";
                break;
            case Constants.GAMEPAD_X:
                currentButton = "gamepad.x";
                break;
            case Constants.GAMEPAD_Y:
                currentButton = "gamepad.y";
                break;
        }
    }

    public boolean updateState(boolean state) {
        if (state) {
            currentState = state;
        }
        return false;
    }

    @Override
    public boolean isPressed() {
        return false;
    }

    @Override
    public boolean isHeld() {
        return false;
    }

    @Override
    public boolean isReleased() {
        return false;
    }

}
