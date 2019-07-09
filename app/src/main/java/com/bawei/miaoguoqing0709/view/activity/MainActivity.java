package com.bawei.miaoguoqing0709.view.activity;

import android.util.Log;
import android.widget.ScrollView;

import com.bawei.miaoguoqing0709.Constant;
import com.bawei.miaoguoqing0709.R;
import com.bawei.miaoguoqing0709.model.adapter.Gridadapter;
import com.bawei.miaoguoqing0709.model.adapter.LtAdapter;
import com.bawei.miaoguoqing0709.model.bean.Bean;
import com.bawei.miaoguoqing0709.model.http.HttpUtils;
import com.bawei.miaoguoqing0709.presenter.HomePresenter;
import com.bawei.miaoguoqing0709.view.baseactivity.BaseActivity;
import com.bawei.miaoguoqing0709.view.interfaces.initView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements initView {
    private List<Bean> list=new ArrayList<>();
    private int page=1;
    private PullToRefreshScrollView pull;
    private Gridadapter grid;
    private HomePresenter homePresenter;
    private LtAdapter ltAdapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void bindView() {
        pull = findViewById(R.id.pull);
        grid = findViewById(R.id.grid);
    }

    @Override
    public void bindData() {
        homePresenter = new HomePresenter();
        homePresenter.getView(this);
        homePresenter.getLoan();
    }

    @Override
    public void ok(String str) {
        ltAdapter = new LtAdapter(list, MainActivity.this);
        grid.setAdapter(ltAdapter);
        pull.setMode(PullToRefreshBase.Mode.BOTH);

        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                page=1;
                getDatean();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                page++;
                getDatean();
            }
            private void getDatean() {
                HttpUtils.getInstance().getAsyncTask(Constant.BULS_RES + page, new HttpUtils.GetBack() {
                    @Override
                    public void getData(String data) {
                        Log.e("ASD",""+data);
                        if (page==1){
                            list.clear();
                        }
                        try {
                            JSONObject object = new JSONObject(data);
                            JSONArray result = object.getJSONArray("result");
                            for (int i = 0; i < result.length(); i++) {
                                JSONObject obj = (JSONObject) result.get(i);
                                String masterPic = obj.getString("masterPic");
                                String commodityName = obj.getString("commodityName");
                                list.add(new Bean(commodityName,masterPic));
                            }
                            ltAdapter.notifyDataSetChanged();
                            pull.onRefreshComplete();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }
//内存溢出
    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.attach();
    }
}
