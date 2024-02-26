package com.example.whatsappdirect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class DashboardActivity extends AppCompatActivity {

    private static final String WHATSAPP_PACKAGE = "com.whatsapp";

    RadioGroup radioGroup;
    RadioButton radioButton, radioButton1;
    TextInputEditText e1, msg;
    Button whatsapp;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButton = (RadioButton) findViewById(R.id.default_message);
        radioButton1 = (RadioButton) findViewById(R.id.custom_message);

        e1 = (TextInputEditText) findViewById(R.id.abcd);
        msg = (TextInputEditText) findViewById(R.id.msg);
        whatsapp = (Button) findViewById(R.id.btn1);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.custom_message) {
                    msg.setVisibility(View.VISIBLE);
                    whatsapp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            String single_number = e1.getText().toString();
                            String custom_message = msg.getText().toString();

                            sendToWhatsApp(single_number, custom_message);

                        }
                    });
                } else if (checkedId == R.id.default_message) {
                    msg.setVisibility(View.GONE);
                    whatsapp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            String single_number = e1.getText().toString();

                            String custom_message = sharedPreferences.getString("savedText", "");
                            ;

                            sendToWhatsApp(single_number, custom_message);

                        }
                    });
                }
            }
        });
//        radioButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                msg.setVisibility(View.GONE);
//                whatsapp.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        String single_number = e1.getText().toString();
//
//                        String custom_message = sharedPreferences.getString("savedText", "");;
//
//                        sendToWhatsApp(single_number, custom_message);
//
//                    }
//                });
//            }
//        });
//        radioButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                msg.setVisibility(View.VISIBLE);
//                whatsapp.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        String single_number = e1.getText().toString();
//                        String custom_message = msg.getText().toString();
//
//                        sendToWhatsApp(single_number, custom_message);
//
//                    }
//                });
//            }
//        });

//        whatsapp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String single_number = e1.getText().toString();
//                String custom_message = msg.getText().toString();
//
//                sendToWhatsApp(single_number, custom_message);
//
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.setting:
                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                break;
            case R.id.share:
//                startActivity(new Intent(FirstActivity.this,RelativeLayoutActivity.class));
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.setPackage("com.whatsapp");
                intent.putExtra(Intent.EXTRA_TEXT, "This is Divya Parmar");
                startActivity(Intent.createChooser(intent, "Suggest to Friends"));
                break;
            case R.id.privacy_policy:
                Toast.makeText(getApplicationContext(), "Privacy Policy", Toast.LENGTH_SHORT).show();
                break;
            case R.id.terms_and_condition:
                Toast.makeText(getApplicationContext(), "Terms and Condition", Toast.LENGTH_SHORT).show();
                break;
            case R.id.disclaimer:
                Toast.makeText(getApplicationContext(), "Disclaimer", Toast.LENGTH_SHORT).show();
                break;
            case R.id.other_apps:
                Toast.makeText(getApplicationContext(), "Other Apps", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return false;
    }

    private void sendToWhatsApp(String phoneNumber, String message) {
        String url = "https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + message;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

}