package org.firstinspires.ftc.teamcode.NewSubsystem.Subsystems;

interface Constants {
    double TICKS_PER_IN = 1120/(4*Math.PI);
    int STOP = 0;
    int LIFT_MAX = 1000;
    int LIFT_MIN = -500;
    enum Status {
        FORWARDS, BACKWARDS, UP, DOWN, LEFT, RIGHT, OPEN, CLOSE, NEUTRAL;
    }
}
