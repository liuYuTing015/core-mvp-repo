package com.core.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.core.app.R;
import com.core.app.utils.MqttSend;

public class MainActivity extends AppCompatActivity {

    public TextView textView;
    public TextView button2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        button2 = (TextView) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DaggerActivity.class);
                startActivity(intent);
            }
        });
        MqttSend mqttSend = new MqttSend();
        mqttSend.sendMessage();

    }


    public void showUserName(String name) {
        textView.setText(name);
    }
}
