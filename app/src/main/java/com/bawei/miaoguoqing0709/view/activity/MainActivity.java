package com.bawei.miaoguoqing0709.view.activity;

import android.util.Log;
import android.widget.ScrollView;

import com.bawei.miaoguoqing0709.Constant;
import com.bawei.miaoguoqing0709.R;
import com.bawei.miaoguoqing0709.model.adapter.Gridadapter;
import com.bawei.miaoguoqing0709.model.adapter.LtAdapter;
import com.bawei.miaoguoqing0709.model.bean.Bean;
import com.bawei.miaoguoqing0709.model.http.CallBack;
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
    public void ok(final String str) {
        pull.setMode(PullToRefreshBase.Mode.BOTH);
        ltAdapter = new LtAdapter(list, MainActivity.this);
        grid.setAdapter(ltAdapter);

        //上下拉刷新
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
                if (page==1){
                    list.clear();
                }
                try {
                    JSONObject object = new JSONObject(str);
                    JSONArray result = object.getJSONArray("result");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject obj = (JSONObject) result.get(i);
                        String masterPic = obj.getString("masterPic");
                        String commodityName = obj.getString("commodityName");
                        Bean bean = new Bean(commodityName, masterPic);
                        list.add(bean);
                        Log.e("aa", "onsuccess: "+bean.getCommodityName());
                    }

                    ltAdapter.notifyDataSetChanged();
                    pull.onRefreshComplete();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//               HttpUtils.getInstance().getData(Constant.BULS_RES + page, new CallBack() {
//                   @Override
//                   public void onsuccess(String data) {
//                       Log.e("qwe",""+data);
//
//                   }
//               });
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
