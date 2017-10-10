package com.core.app.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.core.app.R;
import com.core.app.model.UserName;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity {

    TextView text;
    @Inject
    UserName userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        text = (TextView) findViewById(R.id.textView);
        initData();

    }

    public void showUserName(String name) {
        text.setText(name);
    }

    private void initData() {
        //DaggerTestActivityComponent.builder().build().inject(this);
        userName.setName("测试");
        text.setText(userName.getName());
    }

}
