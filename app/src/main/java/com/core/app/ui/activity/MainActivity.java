package com.core.app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.core.app.R;
import com.core.app.base.MvpActivity;
import com.core.app.data.UserData;
import com.core.app.presenter.UserPresenter;
import com.core.app.ui.model.User;
import com.core.app.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpActivity<UserPresenter> implements UserData {

    ImageView imageView2;
    TextView textView2;
    EditText editText;

    TextView button2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        textView2 = (TextView) findViewById(R.id.textView2);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        editText = (EditText) findViewById(R.id.editText);
        button2 = (TextView) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.loadMainBanner();
            }
        });
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(this);
    }

    @Override
    public void getDataSuccess(User response) {
        textView2.setText(response.getInfo().getSeed());
        editText.setText(response.getResults().get(0).getEmail());
        ImageUtils.loadUrl(this, response.getResults().get(0).getPicture().getThumbnail(), imageView2);

    }

    @Override
    public void getDataFail(String msg) {

    }
}
