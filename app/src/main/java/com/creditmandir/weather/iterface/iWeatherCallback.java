package com.creditmandir.weather.iterface;

import com.creditmandir.weather.model.WeatherData;
import java.util.List;

public interface iWeatherCallback {
    void onResponse(List<WeatherData> list);
}
