package org.firstinspires.ftc.teamcode.Subsystemv2.Subsystems;

public interface Constants {
    int STOP = 0;

    double TICKS_PER_IN = 1120/(4*Math.PI);

    int LEVEL_ONE = 0;
    int LEVEL_TWO = 300;
    int LEVEL_THREE = 600;
    int LEVEL_FOUR = 900;
    int MIN_LEVEL = 0;
    int MAX_LEVEL = 4;


    enum Status {
        FORWARDS, BACKWARDS, UP, DOWN, LEFT, RIGHT, OPEN, CLOSE, NEUTRAL, NORTH, SOUTH, EAST, WEST;
    }
}

