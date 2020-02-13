package org.firstinspires.ftc.teamcode.Subsystemv2.subsystems;

public interface Constants {
    int STOP = 0;

    double TICKS_PER_IN = 1120/(4*Math.PI);

    int LEVEL_ZERO = 0;
    int LEVEL_ONE = 300;
    int LEVEL_TWO = 600;
    int LEVEL_THREE = 900;
    int MIN_LEVEL = 0;
    int MAX_LEVEL = 3;

    int GAMEPAD_A = 0;
    int GAMEPAD_B = 1;
    int GAMEPAD_X = 2;
    int GAMEPAD_Y = 3;

    enum Status {
        FORWARDS, BACKWARDS,
        UP, DOWN,
        LEFT, RIGHT,
        OPEN, CLOSE,
        NEUTRAL,
        NORTH, SOUTH, EAST, WEST,
        RED, GREEN, BLUE,
        DARK, LIGHT;
    }
}

