package com.creditmandir.weather.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    public String getWeatherData(String url) {
        HttpURLConnection con = null;
        InputStream is = null;
        try {
            con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                buffer.append(line + "\r\n");
            }
            is.close();
            con.disconnect();
            String stringBuffer = buffer.toString();
            try {
                is.close();
            } catch (Throwable th) {
            }
            try {
                con.disconnect();
                return stringBuffer;
            } catch (Throwable th2) {
                return stringBuffer;
            }
        } catch (Throwable th3) {
        }
        con.disconnect();
        return null;
    }
}
