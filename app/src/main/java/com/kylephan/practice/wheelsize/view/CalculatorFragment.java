package com.kylephan.practice.wheelsize.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kylephan.practice.wheelsize.R;
import com.kylephan.practice.wheelsize.customview.CustomInputFormView;
import com.kylephan.practice.wheelsize.model.Spec;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CalculatorFragment extends Fragment {

    private static final String TAG = CalculatorFragment.class.getSimpleName();

    private Canvas canvas;
    private Paint paint;
    private Bitmap bitmap;

    private int viewWidth;
    private int viewHeight;

    @BindView(R.id.spec_canvas) ImageView specImageView;
    @BindView(R.id.compare_canvas) ImageView compareImageView;
    @BindView(R.id.fender_canvas) ImageView fenderImageView;

    @BindView(R.id.advanced_checkbox) CheckBox advancedCheckbox;
    @BindView(R.id.form_holder) LinearLayout formHolder;

    @BindView(R.id.inputform1) CustomInputFormView form1;
    @BindView(R.id.inputform2) CustomInputFormView form2;


    private Spec spec = new Spec();
    private Spec spec2 = new Spec();

    public CalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
//        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, getView());
        viewWidth = convertDiptoPix(getContext(), 400);
        viewHeight = convertDiptoPix(getContext(), 400);

        form1.setTitleTextView(R.string.current_spec);
        form2.setTitleTextView(R.string.new_spec);
        form2.setZ(-1);
        advancedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    form2.animate()
                            .translationY((-form2.getHeight()))
                            .alpha(1.0f)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    formHolder.setElevation(0);
                                    form2.setVisibility(View.GONE);
                                }
                            });
                } else {
                    form2.setVisibility(View.VISIBLE);
                    form2.animate()
                            .translationY(0)
                            .alpha(1.0f)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    formHolder.setElevation(1);
                                }
                            });
                }
            }
        });
    }

    private void drawSpec(Spec spec, ImageView imageView) {
        Log.d(TAG, "KP## Drawing spec");
        Log.d(TAG, "KP## VIEW WIDTH : " + imageView.getWidth());
        Log.d(TAG, "KP## VIEW HEIGHT : " + imageView.getHeight());

        Log.d(TAG, "KP## VIEW WIDTH : " + viewWidth + " | VIEW HEIGHT : " + viewHeight);

        int viewCenterX = viewWidth / 2;
        int viewCenterY = viewHeight / 2;

        Log.d(TAG, "KP## VIEW CENTER X : " + viewCenterX + " | VIEW CENTER Y : " + viewCenterY);

//        Creating colors
        int wheelColor;
        int tireColor = ContextCompat.getColor(getContext(), R.color.tire_color);
        int crosshairColor = ContextCompat.getColor(getContext(), R.color.crosshair_color);

        if (imageView == specImageView) {
            wheelColor = ContextCompat.getColor(getContext(), R.color.wheel_color);
        } else {
            wheelColor = ContextCompat.getColor(getContext(), R.color.wheel_color2);
        }

//        Set up canvas
//        bitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);
//        imageView.setImageBitmap(bitmap);
//        canvas = new Canvas(bitmap);
        setBitmap(imageView, viewWidth, viewHeight);

//        Set up paint
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(crosshairColor);
        canvas.drawLine(viewCenterX, 0, viewCenterX, imageView.getWidth(), paint);
        canvas.drawLine(0, viewCenterY, 2960, imageView.getHeight(), paint);

//        Save canvas to draw camber
        canvas.save();
        canvas.rotate(spec.getCamber(), viewCenterX, viewCenterY);

//        Draw tire
        drawTire(spec, tireColor, viewCenterX, viewCenterY);

//        Draw wheel
        drawWheel(spec, wheelColor, viewCenterX, viewCenterY);

//        DEBUG LINES
        canvas.restore();

        imageView.invalidate();
    }

    private void drawTire(Spec spec, int color, int viewCenterX, int viewCenterY) {
        paint.setColor(color);
        canvas.drawLines(spec.getLinePoints(viewCenterX, viewCenterY), paint);
    }

    private void drawWheel(Spec spec, int color, int viewCenterX, int viewCenterY) {
        paint.setColor(color);
        canvas.drawRect(spec.getWheelRect(viewCenterX, viewCenterY), paint);
        canvas.drawLines(spec.getOffsetPoints(viewCenterX, viewCenterY), paint);
    }

    private void setBitmap(ImageView imageView, int viewWidth, int viewHeight) {
        //        Set up canvas
        bitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);
        imageView.setImageBitmap(bitmap);
        canvas = new Canvas(bitmap);
    }

    private boolean checkValues() {
        boolean valid = false;
        if (form1.tireWidthEmpty()
                && form1.sidewallEmpty()
                && form1.diameterEmpty()
                && form1.wheelWidthEmpty()
                && form1.offsetEmpty()
                && form1.camberEmpty()) {

            if ((form1.getTireWidth() < 1) ||
                    (form1.getSidewallRatio() < 1) ||
                    (form1.getWheelDiameter() < 1) ||
                    (form1.getWheelWidth() < 1 )) {

                Toast.makeText(getContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                valid = false;
            } else {
                Toast.makeText(getContext(), "Calculating", Toast.LENGTH_SHORT).show();
                valid = true;
            }
        } else {
            Toast.makeText(getContext(), R.string.complete_form_prompt, Toast.LENGTH_LONG).show();
        }

        return valid;
    }

    @OnClick(R.id.submit_button)
    public void onSubmitClick() {
//        drawSpec(spec, specImageView);
        if(checkValues()) {

            spec.setAllSpecs(
                    form1.getTireWidth(),
                    form1.getSidewallRatio(),
                    form1.getWheelDiameter(),
                    form1.getWheelWidth(),
                    form1.getWheelOffset(),
                    form1.getWheelCamber());

            drawSpec(spec, specImageView);

            spec2.setAllSpecs(
                    form2.getTireWidth(),
                    form2.getSidewallRatio(),
                    form2.getWheelDiameter(),
                    form2.getWheelWidth(),
                    form2.getWheelOffset(),
                    form2.getWheelCamber());

            drawSpec(spec2, compareImageView);
        }
    }

    @OnClick(R.id.clear_button)
    public void onClearClick() {
        setBitmap(specImageView, viewWidth, viewHeight);
        setBitmap(compareImageView, viewWidth, viewHeight);
        setBitmap(fenderImageView, viewWidth, viewHeight);
    }

    public int convertDiptoPix(Context context, float dip) {
        int value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
        return value;
    }


}
