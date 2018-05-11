package com.creditmandir.weather.async;

import android.os.AsyncTask;
import com.creditmandir.weather.http.HttpClient;
import com.creditmandir.weather.iterface.iWeatherCallback;
import com.creditmandir.weather.model.WeatherData;
import com.creditmandir.weather.parser.JsonParser;
import java.util.List;

public class WeatherTask extends AsyncTask<String, Void, List<WeatherData>> {
    private iWeatherCallback mIWeatherCallback;

    public WeatherTask(iWeatherCallback callback) {
        this.mIWeatherCallback = callback;
    }

    protected List<WeatherData> doInBackground(String... params) {
        return JsonParser.getWeather(new HttpClient().getWeatherData(params[0]));
    }

    protected void onPostExecute(List<WeatherData> weatherData) {
        super.onPostExecute(weatherData);
        this.mIWeatherCallback.onResponse(weatherData);
    }
}
