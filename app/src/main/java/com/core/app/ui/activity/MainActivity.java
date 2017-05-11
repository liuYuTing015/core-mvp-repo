package com.core.app.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.core.app.R;
import com.core.app.base.MvpActivity;
import com.core.app.service.MainModel;
import com.core.app.ui.adapter.MainPresenter;
import com.core.app.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView {

    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.button2)
    Button button2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ImageUtils.loadUrl(this, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494420051325&di=54d86ef9cd27702a5c9120c8ec4c928c&imgtype=0&src=http%3A%2F%2Fstatic11.photo.sina.com.cn%2Fmiddle%2F543f8834h87b7cd05af3a%26690", imageView2);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @OnClick(R.id.button2)
    public void onButton(View view) {
        mPresenter.loadMainBanner();
    }

    @Override
    public void getDataSuccess(MainModel response) {
        textView2.setText(response.getWeatherinfo().getCity());
        editText.setText(response.getWeatherinfo().getTime());

    }

    @Override
    public void getDataFail(String msg) {

    }
}
