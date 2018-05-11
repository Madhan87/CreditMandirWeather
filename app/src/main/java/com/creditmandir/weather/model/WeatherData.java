package com.creditmandir.weather.model;

public class WeatherData {
    private String mCityName;
    private String mHumidity;
    private String mPressure;
    private String mTemp;
    private String mWindSpeed;

    public String getTemp() {
        return this.mTemp;
    }

    public void setTemp(String temp) {
        this.mTemp = temp;
    }

    public String getCityName() {
        return this.mCityName;
    }

    public void setCityName(String cityName) {
        this.mCityName = cityName;
    }

    public String getPressure() {
        return this.mPressure;
    }

    public void setPressure(String pressure) {
        this.mPressure = pressure;
    }

    public String getHumidity() {
        return this.mHumidity;
    }

    public void setHumidity(String humidity) {
        this.mHumidity = humidity;
    }

    public String getWindSpeed() {
        return this.mWindSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.mWindSpeed = windSpeed;
    }
}
