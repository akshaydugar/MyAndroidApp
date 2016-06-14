package com.sample.myandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    Button handleFileButton;
    Button makeNetworkRequestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleFileButton = (Button) findViewById(R.id.button_file_op);
        makeNetworkRequestButton = (Button) findViewById(R.id.button_network_op);

        handleFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                handleFileButtonClick();
            }
        });

        makeNetworkRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                handleNetworkRequestButtonClick();
            }
        });

    }

    private void handleFileButtonClick()
    {
        Intent fileActivityIntent = new Intent(this, FileActivity.class);
        startActivity(fileActivityIntent);

    }

    private void handleNetworkRequestButtonClick()
    {
        Intent networkRequestIntent = new Intent(this, NetworkRequestActivity.class);
        startActivity(networkRequestIntent);
    }
}
