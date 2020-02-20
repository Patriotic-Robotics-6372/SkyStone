package org.firstinspires.ftc.teamcode.Subsystemv2.subsys;

public interface Constants {
    
    // Used in setPower to stop motors, servos
    int STOP = 0;

    // Ratio between one inch and encoder count
    double TICKS_PER_IN = 1120/(4*Math.PI);

    // Throttle speeds
    double THROTTLE_ON = 0.5;
    double THROTTLE_OFF = 1;

    // Tick counts used in lift encoder methods
    int LEVEL_ZERO = 0;
    int LEVEL_ONE = 1120;
    int LEVEL_TWO = 2240;
    int LEVEL_THREE = 3360;
    int MIN_LEVEL = 0;
    int MAX_LEVEL = 3;

    // Range of hue values for skystone, block, nothing
    float HUE_SKYSTONE_MIN = 70;
    float HUE_SKYSTONE_MAX = 105;
    float HUE_BLOCK_MIN = 35;
    float HUE_BLOCK_MAX = 45;
    float HUE_NOTHING_MIN = 125;
    float HUE_NOTHING_MAX = 150;

    // Used in determining which autonomous to run
    enum Block {
        ONE, TWO, THREE;
    }

    // Used for describing status of subsystem
    enum Status {
        FORWARDS, BACKWARDS,
        UP, DOWN,
        LEFT, RIGHT,
        OPEN, CLOSE,
        NEUTRAL,
        NORTH, SOUTH, EAST, WEST,
        RED, GREEN, BLUE,
        DARK, LIGHT,
        NORMAL, AUTO;
    }
}

