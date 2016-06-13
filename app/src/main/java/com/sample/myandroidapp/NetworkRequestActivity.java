package com.sample.myandroidapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by zaraahmed on 6/11/16.
 */

public class NetworkRequestActivity extends AppCompatActivity {
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";
    private static final String CITY_QUERY_PARAM = "q=";
    private static final String APP_ID_QUERY_PARAM = "&APPID=";
    private static final String UNITS_QUERY_PARAM = "&units=";
    private static final String UNITS_QUERY_VALUE = "metric";
    private static final String API_KEY = "f09ef55aafe285c2363b7b459b6db8fb";
    EditText nameOfCityET;
    Button getCurrentWeatherButton;
    TextView resultTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        nameOfCityET = (EditText) findViewById(R.id.name_of_city);

        getCurrentWeatherButton = (Button) findViewById(R.id.get_current_weather);
        getCurrentWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentWeather();
            }
        });

        resultTV = (TextView) findViewById(R.id.result);
    }

    private void getCurrentWeather() {
        String cityName = nameOfCityET.getText().toString().trim();
        new GetWeatherTask(cityName).execute();
    }

    private class GetWeatherTask extends AsyncTask<Void, Void, String> {
        private final String urlString;

        public GetWeatherTask(String cityName) {
            urlString = BASE_URL + CITY_QUERY_PARAM + cityName + UNITS_QUERY_PARAM + UNITS_QUERY_VALUE + APP_ID_QUERY_PARAM + API_KEY;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                String response = IOUtils.toString(inputStream);
                JSONObject jsonResponse = new JSONObject(response);

                JSONArray weatherArray = jsonResponse.getJSONArray("weather");
                JSONObject weather = weatherArray.getJSONObject(0);
                String weatherString = weather.getString("main");

                JSONObject main = jsonResponse.getJSONObject("main");
                String temperatureString = main.getString("temp");

                String result = "Weather: " + weatherString + "\nTemperature: " + temperatureString + "\u2103";
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            resultTV.setText(result);
        }
    }
}
