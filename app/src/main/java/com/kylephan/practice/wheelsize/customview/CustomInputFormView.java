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

public class CustomInputFormView extends LinearLayout {

    private Unbinder unbinder;

    @BindView(R.id.title_textview) TextView titleTextView;
    @BindView(R.id.width_et) EditText tireWidthET;
    @BindView(R.id.sidewall_et) EditText sidewallET;
    @BindView(R.id.wheel_diameter_et) EditText diameterET;
    @BindView(R.id.wheel_width_et) EditText wheelWidthET;
    @BindView(R.id.wheel_offset_et) EditText offsetET;
    @BindView(R.id.camber_et) EditText camberET;

    private Boolean visible = false;

    public CustomInputFormView(Context context) {
        super(context);

        init(context);
    }

    public CustomInputFormView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        LayoutInflater.from(context).inflate(R.layout.input_form_layout, this);

        init(context);
    }

    public CustomInputFormView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Log.d("CUSTOM INPUT FORM", "KP## INIT FORM");

        ViewGroup.inflate(context, R.layout.wheel_input_form_layout, this);
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

    public int getTireWidth() {
        if (!tireWidthET.getText().toString().isEmpty()) {
            return Integer.parseInt(tireWidthET.getText().toString());
        } else {
            return 0;
        }
    }

    public int getSidewallRatio() {
        if (!sidewallET.getText().toString().isEmpty()) {
            return Integer.parseInt(sidewallET.getText().toString());
        } else {
            return 0;
        }
    }

    public int getWheelDiameter() {
        if (!diameterET.getText().toString().isEmpty()) {
            return Integer.parseInt(diameterET.getText().toString());
        } else {
            return 0;
        }
    }

    public float getWheelWidth() {
        if (!wheelWidthET.getText().toString().isEmpty()) {
            return Float.parseFloat(wheelWidthET.getText().toString());
        } else {
            return 0;
        }
    }

    public float getWheelOffset() {
        if (!offsetET.getText().toString().isEmpty()) {
            return Float.parseFloat(offsetET.getText().toString());
        } else {
            return 0;
        }
    }

    public float getWheelCamber() {
        if (!camberET.getText().toString().isEmpty()) {
            return Float.parseFloat(camberET.getText().toString());
        } else {
            return 0;
        }
    }

    public boolean tireWidthEmpty() {
        if (!tireWidthET.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean sidewallEmpty() {
        if (!sidewallET.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean diameterEmpty() {
        if (!diameterET.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean wheelWidthEmpty() {
        if (!wheelWidthET.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean offsetEmpty() {
        if (!offsetET.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean camberEmpty() {
        if (!camberET.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
