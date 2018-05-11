package com.creditmandir.weather.parser;

import android.text.TextUtils;
import com.creditmandir.weather.model.WeatherData;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {
    public static List<WeatherData> getWeather(String result) {
        List<WeatherData> weatherDataList = new ArrayList();
        if (TextUtils.isEmpty(result)) {
            return null;
        }
        try {
            JSONArray listArray = new JSONObject(result).optJSONArray(JsonConstants.LIST);
            for (int i = 0; i < listArray.length(); i++) {
                JSONObject dataObj = listArray.getJSONObject(i);
                String cityName = dataObj.optString(JsonConstants.NAME);
                WeatherData data = new WeatherData();
                JSONObject mainObj = dataObj.optJSONObject(JsonConstants.MAIN);
                JSONObject windObj = dataObj.optJSONObject(JsonConstants.WIND);
                String pressure = mainObj.optString(JsonConstants.PRESSURE);
                String humidity = mainObj.optString(JsonConstants.HUMIDITY);
                String speed = windObj.optString(JsonConstants.SPEED);
                String temp = mainObj.optString(JsonConstants.TEMP);
                data.setHumidity(humidity);
                data.setPressure(pressure);
                data.setWindSpeed(speed);
                data.setCityName(cityName);
                data.setTemp(temp);
                weatherDataList.add(data);
            }
            return weatherDataList;
        } catch (JSONException e) {
            e.printStackTrace();
            return weatherDataList;
        }
    }
}
