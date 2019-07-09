package com.bawei.miaoguoqing0709.view.baseactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bawei.miaoguoqing0709.R;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        bindView();
        bindData();
    }

    public abstract int bindLayout();

    public abstract void bindView();

    public abstract void bindData();
}
