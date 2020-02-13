package org.firstinspires.ftc.teamcode.Subsystemv2.subsystems.vision;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.Subsystemv2.subsystems.Constants;

public class SensorColor implements Constants {

    ColorSensor colorSensor;
    boolean led;

    public SensorColor(ColorSensor cS) {
        this.colorSensor = cS;
    }

    public ColorSensor getColorSensor() {
        return colorSensor;
    }

    public void turnOnLED() {
        colorSensor.enableLed(true);
        led = true;
    }

    public void turnOffLED() {
        colorSensor.enableLed(false);
        led = false;
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

    public int getHue() {
        return colorSensor.argb();
    }

    public boolean isSkystone() {
        if (!getLEDState()) {
            turnOnLED();
        }
        if (getHue() > 90) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBlock() {
        if (!getLEDState()) {
            turnOnLED();
        }
        if (!isSkystone() && getHue() < 50) {
            return true;
        } else {
            return false;
        }
    }

    public Status getColor() {
        if (getRed() > getGreen() && getRed() > getBlue()) {
            return Status.RED;
        } else if (getGreen() > getBlue()) {
            return Status.GREEN;
        } else {
            return Status.BLUE;
        }
    }

}
