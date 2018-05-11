package com.creditmandir.weather.util;

/**
 * Created by mj on 5/11/2018.
 */

public class Utils {
    public static float toFahrenheit(float celsius) {
        return (9.0f * (celsius / 5.0f)) + 32.0f;
    }

    public static float toCelsius(float fahrenheit) {
        return ((fahrenheit - 32.0f) * 5.0f) / 9.0f;
    }
}
