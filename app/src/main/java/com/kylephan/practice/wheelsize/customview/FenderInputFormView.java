package com.kylephan.practice.wheelsize.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kylephan.practice.wheelsize.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FenderInputFormView extends LinearLayout {

    private Unbinder unbinder;

    @BindView(R.id.title_textview) TextView titleTextView;
    @BindView(R.id.depth_et) EditText depthET;
    @BindView(R.id.height_et) EditText heightET;
    @BindView(R.id.angle_et) EditText angleET;

    private Boolean visible = false;

    public FenderInputFormView(Context context) {
        super(context);
//        LayoutInflater.from(context).inflate(R.layout.input_form_layout, this);

//        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(R.layout.input_form_layout, this, true);

        init(context);
    }

    public FenderInputFormView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        LayoutInflater.from(context).inflate(R.layout.input_form_layout, this);

        init(context);
    }

    public FenderInputFormView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Log.d("CUSTOM INPUT FORM", "KP## INIT FORM");

        ViewGroup.inflate(context, R.layout.fender_input_form_layout, this);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unbinder.unbind();
    }

    public void showView() {
        if (visible) {
            this.setVisibility(INVISIBLE);
            visible = !visible;
        } else {
            this.setVisibility(VISIBLE);
            visible = !visible;
        }
        Log.d("INPUT FORM", "KP## SHOW VIEW : " + visible);
    }


    public void setTitleTextView(String title) {
        titleTextView.setText(title);
    }

    public void setTitleTextView(int titleId) {
        titleTextView.setText(titleId);
    }

    public float getFenderDepth() {
        if (!depthET.getText().toString().isEmpty()) {
            return Float.parseFloat(depthET.getText().toString());
        } else {
            return 0;
        }
    }

    public float getFenderHeight() {
        if (!heightET.getText().toString().isEmpty()) {
            return Float.parseFloat(heightET.getText().toString());
        } else {
            return 0;
        }
    }

    public float getFenderAngle() {
        if (!angleET.getText().toString().isEmpty()) {
            return Float.parseFloat(angleET.getText().toString());
        } else {
            return 0;
        }
    }
}
