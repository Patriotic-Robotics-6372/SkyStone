package org.firstinspires.ftc.teamcode.Subsystemv2.subsystems;

public interface Constants {
    int STOP = 0;

    double TICKS_PER_IN = 1120/(4*Math.PI);

    int LIFT_MAX = 1000;
    int LIFT_MIN = -500;

    enum Status {
        FORWARDS, BACKWARDS, UP, DOWN, LEFT, RIGHT, OPEN, CLOSE, NEUTRAL, NORTH, SOUTH, EAST, WEST;
    }
}

