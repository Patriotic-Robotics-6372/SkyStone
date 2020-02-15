package org.firstinspires.ftc.teamcode.Subsystemv2.subsys.input;

public interface Button {
    public boolean currentState = false;
    public boolean previousState = currentState;
    public boolean isPressed();
    public boolean isHeld();
    public boolean isReleased();
    public boolean getCurrentState();
    public boolean getPreviousState();
}
