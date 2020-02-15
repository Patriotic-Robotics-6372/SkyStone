package org.firstinspires.ftc.teamcode.Subsystemv2.subsys.vision;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsys.Constants;

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

    public void turnOnLED() {
        led = true;
        colorSensor.enableLed(led);
    }

    public void turnOffLED() {
        led = false;
        colorSensor.enableLed(led);
    }

    public boolean getLEDState() {
        return led;
    }

    public int getRed() {
        return colorSensor.red();
    }

    public int getGreen() {
        return colorSensor.green();
    }

    public int getBlue() {
        return colorSensor.blue();
    }

    public int getAlpha() {
        return colorSensor.alpha();
    }

    public float getHue() {
        Color.RGBToHSV(getRed() * 8, getGreen() * 8, getBlue() * 8, hsvValues);
        return hsvValues[0];
    }

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
