package com.bawei.miaoguoqing0709.model.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class Gridadapter extends GridView {
    public Gridadapter(Context context) {
        super(context);
    }

    public Gridadapter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Gridadapter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
