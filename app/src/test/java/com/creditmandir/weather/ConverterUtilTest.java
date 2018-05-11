package com.creditmandir.weather;

import static junit.framework.Assert.assertEquals;

import com.creditmandir.weather.util.Utils;

import org.junit.Test;

/**
 * Created by mj on 5/11/2018.
 */

public class ConverterUtilTest {
    @Test
    public void testConvertFahrenheitToCelsius() {
        float actual = Utils.toFahrenheit(100);
        // expected value is 212
        float expected = 212;
        // use this method because float is not precise
        assertEquals("Conversion from celsius to fahrenheit failed", expected, actual, 0.001);
    }

    @Test
    public void testConvertCelsiusToFahrenheit() {
        float actual = Utils.toCelsius(212);
        // expected value is 100
        float expected = 100;
        // use this method because float is not precise
        assertEquals("Conversion from celsius to fahrenheit failed", expected, actual, 0.001);
    }

}
