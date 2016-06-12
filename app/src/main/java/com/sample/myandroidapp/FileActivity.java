package com.sample.myandroidapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zaraahmed on 6/11/16.
 */

public class FileActivity extends AppCompatActivity
{

    Button saveTextButton;
    Button openTextButton;
    EditText nameOfFileET;
    EditText contentToSaveET;
    TextView contentToShowTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_activity_layout);
        saveTextButton = (Button) findViewById(R.id.save_text_button);
        openTextButton = (Button) findViewById(R.id.open_file_button);
        nameOfFileET = (EditText) findViewById(R.id.name_of_file);
        contentToSaveET = (EditText) findViewById(R.id.text_to_save);
        contentToShowTV = (TextView) findViewById(R.id.text_to_show);

        saveTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                contentToSaveET.setVisibility(View.VISIBLE);
                contentToShowTV.setVisibility(View.GONE);
                saveData();
            }
        });

        openTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                contentToSaveET.setVisibility(View.GONE);
                contentToShowTV.setVisibility(View.VISIBLE);
                showData();
            }
        });
    }

    private void showData()
    {
        try {
            String fileName = nameOfFileET.getText().toString();

            File fileToOpen = new File(getFilesDir(), fileName);

            StringBuffer content = new StringBuffer();

            byte[] bytes = new byte[1024];
            if (!fileToOpen.exists()) {
                Toast.makeText(this, "A file with this name does not exist", Toast.LENGTH_SHORT).show();
            }
            else {
                FileInputStream fi = new FileInputStream(fileToOpen);

                while (fi.read(bytes) != -1) {

                    content.append(new String(bytes));

                }
                fi.close();
                contentToShowTV.setText(content);
            }
        }
        catch (Exception e) {

        }
    }

    private void saveData()
    {
        try {
            String fileName = nameOfFileET.getText().toString();

            File fileToWriteTo = new File(getFilesDir(), fileName);
            if (fileToWriteTo.exists()) {
                Toast.makeText(this, "A file with this name already exists", Toast.LENGTH_SHORT).show();
            }
            else {
                FileOutputStream fo = new FileOutputStream(fileToWriteTo);
                fo.write(contentToSaveET.getText().toString().getBytes());
                fo.close();
                Toast.makeText(this, "File saved", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {

        }

    }
}
