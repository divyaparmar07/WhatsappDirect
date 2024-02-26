package com.example.whatsappdirect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class SettingActivity extends AppCompatActivity {

    TextInputEditText e1;
    Button b1;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("Settings");

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        b1 = (Button) findViewById(R.id.btn1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1 = (TextInputEditText) findViewById(R.id.abcd);
                String text = e1.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("savedText", text);
                editor.apply();
            }
        });

        // Retrieve the saved text when the activity starts
        String savedText = sharedPreferences.getString("savedText", "");
        TextView textView = findViewById(R.id.abcd);
        textView.setText(savedText);
    }

}