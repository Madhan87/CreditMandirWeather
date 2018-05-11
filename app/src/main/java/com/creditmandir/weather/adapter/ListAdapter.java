package com.creditmandir.weather.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.creditmandir.weather.HomeScreenActivity;
import com.creditmandir.weather.R;
import com.creditmandir.weather.model.WeatherData;
import com.creditmandir.weather.util.Utils;

import java.util.List;

public class ListAdapter extends ArrayAdapter<WeatherData> {
    private int listItemLayout;
    private Context mContext;

    private static class ViewHolder {
        TextView txtCityName;
        TextView txtTemp;

        private ViewHolder() {
        }
    }

    public ListAdapter(Context context, int layoutId, List<WeatherData> itemList) {
        super(context, layoutId, itemList);
        this.listItemLayout = layoutId;
        this.mContext = context;
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        WeatherData weatherData = (WeatherData) getItem(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(this.listItemLayout, parent,
                    false);
            viewHolder.txtCityName = (TextView) convertView.findViewById(R.id.cityTextView);
            viewHolder.txtTemp = (TextView) convertView.findViewById(R.id.tempTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtCityName.setText(weatherData.getCityName());
        float f = Float.parseFloat(weatherData.getTemp());
        String tempValue = Float.toString(Utils.toCelsius(f)) + "C";
        String name = this.mContext.getSharedPreferences(HomeScreenActivity.SHARED_PREF_NAME,
                0).getString(HomeScreenActivity.TEMP_CONVERSION, null);
        if (!TextUtils.isEmpty(name)) {
            if (name.equalsIgnoreCase("fahrenheit")) {
                tempValue = Float.toString(Utils.toFahrenheit(f)) + "F";
            } else if (name.equalsIgnoreCase("celsius")) {
                tempValue = Float.toString(Utils.toCelsius(f)) + "C";
            }
        }
        viewHolder.txtTemp.setText("Temp: " + tempValue);
        return convertView;
    }
}
