package com.creditmandir.weather;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.TextView;

import com.creditmandir.weather.adapter.ListAdapter;
import com.creditmandir.weather.async.WeatherTask;
import com.creditmandir.weather.iterface.iWeatherCallback;
import com.creditmandir.weather.model.WeatherData;

import java.util.List;

public class HomeScreenActivity extends AppCompatActivity implements iWeatherCallback {
    public static final String SHARED_PREF_NAME = "mysharedpref";
    public static final String TEMP_CONVERSION = "tempConversion";
    private ListView cityListView;
    private ListAdapter listAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        this.cityListView = (ListView) findViewById(R.id.cityListView);
        new WeatherTask(this).execute(AppConstants.BASE_URL);
    }

    @Override
    public void onResponse(final List<WeatherData> weather) {
        if (weather != null && weather.size() > 0) {
            this.listAdapter = new ListAdapter(getApplicationContext(), R.layout.list_item, weather);
            this.cityListView.setAdapter(this.listAdapter);
            this.cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    WeatherData weatherData = (WeatherData) weather.get(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(HomeScreenActivity.this);
                    builder.setTitle("Weather Report");
                    TextView input = new TextView(HomeScreenActivity.this);
                    input.setText("Pressure: " + weatherData.getPressure() + "\nHumidity: " + weatherData.getHumidity() + "\nSpeed: " + weatherData.getWindSpeed());
                    builder.setView(input);
                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                }
            });
        }
        Log.d("onResponse", "response" + ((WeatherData) weather.get(2)).getCityName());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                updateList("fahrenheit");
                return true;
            case R.id.item2:
                updateList("celsius");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateList(String value) {
        SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREF_NAME, 0).edit();
        editor.putString(TEMP_CONVERSION, value);
        editor.apply();
        this.listAdapter.notifyDataSetChanged();
    }
}
