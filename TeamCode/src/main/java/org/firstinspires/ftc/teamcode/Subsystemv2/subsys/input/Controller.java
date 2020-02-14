package org.firstinspires.ftc.teamcode.Subsystemv2.subsys.input;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;

public class Controller implements Constants {

    AnalogButton left_stick_x;
    AnalogButton left_stick_y;
    AnalogButton right_stick_x;
    AnalogButton right_stick_y;
    AnalogButton left_trigger;
    AnalogButton right_trigger;
    DigitalButton a;
    DigitalButton b;
    DigitalButton x;
    DigitalButton y;
    DigitalButton dpad_up;
    DigitalButton dpad_down;
    DigitalButton dpad_left;
    DigitalButton dpad_right;
    DigitalButton right_stick_button;
    DigitalButton left_stick_button;

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
        this.right_stick_button = new DigitalButton();
        this.left_stick_button = new DigitalButton();
    }
}
