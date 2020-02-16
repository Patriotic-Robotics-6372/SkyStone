package org.firstinspires.ftc.teamcode.Subsystemv2.subsys.vision;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;

/**
 * Date: 2/14/20
 * Author: Jacob Marinas
 * Color sensor subsystem. Used to detect skystone in autonomous
 */
public class SensorColor implements Constants {

    ColorSensor colorSensor;
    float hsvValues[] = {0F,0F,0F};
    boolean led;

    public SensorColor(ColorSensor cS) {
        this.colorSensor = cS;
    }

    public ColorSensor getColorSensor() {
        return colorSensor;
    }

    /**
     * Turns on the light on the color sensor
     */
    public void turnOnLED() {
        led = true;
        colorSensor.enableLed(led);
    }

    /**
     * Turns off the light on the colorsensor
     */
    public void turnOffLED() {
        led = false;
        colorSensor.enableLed(led);
    }

    /**
     * @return state of light
     */
    public boolean getLEDState() {
        return led;
    }

    /**
     * @return red value
     */
    public int getRed() {
        return colorSensor.red();
    }

    /**
     * @return green value
     */
    public int getGreen() {
        return colorSensor.green();
    }

    /**
     * @return blue value
     */
    public int getBlue() {
        return colorSensor.blue();
    }

    /**
     * @return alpha value
     */
    public int getAlpha() {
        return colorSensor.alpha();
    }

    /**
     * @return hue value
     */
    public float getHue() {
        Color.RGBToHSV(getRed() * 8, getGreen() * 8, getBlue() * 8, hsvValues);
        return hsvValues[0];
    }

    /**
     * @return true if hue value matches Skystone range, false otherwise
     */
    public boolean isSkystone() {
        if (!getLEDState()) {
            turnOnLED();
        }
        if (getHue() < HUE_SKYSTONE_MAX && getHue() > HUE_SKYSTONE_MIN) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return true if hue value matches block range, false otherwise
     */
    public boolean isBlock() {
        if (!getLEDState()) {
            turnOnLED();
        }
        if (getHue() < HUE_BLOCK_MAX && getHue() > HUE_BLOCK_MIN) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return true if isSkystone() and isBlock() are false, false otherwise
     */
    public boolean isNothing() {
        if (!getLEDState()) {
            turnOnLED();
        }
        /*
        if (getHue() < HUE_NOTHING_MAX && getHue() > HUE_NOTHING_MIN) {
            return true;
        } else {
            return false;
        }
         */
        if (!isSkystone() && !isBlock()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return dominant color out of red, green, blue
     */
    public String getColor() {
        if (getRed() > getGreen() && getRed() > getBlue()) {
            return "RED";
        } else if (getGreen() > getBlue()) {
            return "GREEN";
        } else {
            return "BLUE";
        }
    }

}
