package com.sample.myandroidapp;

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
        String cityName = nameOfCityET.getText().toString();
        // Do network call
    }

}
