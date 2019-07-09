package com.bawei.miaoguoqing0709.model.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.miaoguoqing0709.R;
import com.bawei.miaoguoqing0709.model.bean.Bean;
import com.bumptech.glide.Glide;

import java.util.List;
//配置器
public class LtAdapter extends BaseAdapter {
private List<Bean> list;
private Context context;

    public LtAdapter(List<Bean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.one,null);
            holder=new ViewHolder();
            holder.ima=convertView.findViewById(R.id.ima);
            holder.tv=convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Bean bean = list.get(position);
        Log.e("aaa", "getView: "+list.get(position).getCommodityName() );
        holder.tv.setText(bean.getCommodityName());
        Glide.with(convertView).load(bean.getMasterPic()).into(holder.ima);
        return convertView;
    }
    class ViewHolder{
        ImageView ima;
        TextView tv;
    }
}
