package com.bawei.miaoguoqing0709.presenter;

import android.util.Log;

import com.bawei.miaoguoqing0709.Constant;
import com.bawei.miaoguoqing0709.model.http.HttpUtils;
import com.bawei.miaoguoqing0709.view.interfaces.initView;

public class HomePresenter {

    private final HttpUtils instance;

    public HomePresenter(){
        instance = HttpUtils.getInstance();
    }
    public initView ini;
    public void getView(initView view) {
        this.ini=view;
    }
    public void attach(){
        this.ini=null;
    }
    public void getLoan() {
        instance.getAsyncTask(Constant.BULS_RES, new HttpUtils.GetBack() {
            @Override
            public void getData(String data) {
                Log.e("ASD",""+data);
                ini.ok(data);
            }
        });
    }
}
