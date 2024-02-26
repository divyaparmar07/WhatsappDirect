package com.example.whatsappdirect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Dashboard extends AppCompatActivity {

    TextInputLayout e1, msg;
    Button whatsapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);

//        e1 = (TextInputLayout) findViewById(R.id.outlinedTextField);
        msg = (TextInputLayout) findViewById(R.id.msg);
        whatsapp = (Button) findViewById(R.id.btn1);
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
                Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
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

}