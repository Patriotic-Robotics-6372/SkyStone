package org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems;

interface Constants {
    double TICKS_PER_IN = 1120/4*Math.PI;
    int STOP = 0;
    enum Status {
        FORWARDS, BACKWARDS, UP, DOWN, LEFT, RIGHT, OPEN, CLOSE, NEUTRAL,
    }
}
