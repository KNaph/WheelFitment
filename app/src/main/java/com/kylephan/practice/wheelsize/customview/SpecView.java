package com.kylephan.practice.wheelsize.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.kylephan.practice.wheelsize.R;

public class SpecView extends RelativeLayout {

    private static final String TAG = SpecView.class.getSimpleName();


    public SpecView(Context context) {
        super(context);
        init(context);
    }

    public SpecView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SpecView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public SpecView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        Log.d(TAG, "KP## INIT SPEC VIEW");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.spec_view, this);

    }

    @Override
    public void onDraw(Canvas canvas) {

    }
}
