package org.firstinspires.ftc.teamcode.Subsystemv2.subsys.input;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
public class AnalogButton implements Button {

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

    @Override
    public boolean getCurrentState() {
        return false;
    }

    @Override
    public boolean getPreviousState() {
        return false;
    }
}
