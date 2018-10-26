package com.kylephan.practice.wheelsize.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kylephan.practice.wheelsize.R;
import com.kylephan.practice.wheelsize.model.Spec;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CalculatorFragment extends Fragment {

    private static final String TAG = CalculatorFragment.class.getSimpleName();

    private Canvas canvas;
    private Paint paint;
    private Bitmap bitmap;

    @BindView(R.id.spec_canvas) ImageView specImageView;
    @BindView(R.id.compare_canvas) ImageView compareImageView;

    @BindView(R.id.width_et) EditText tireWidthET;
    @BindView(R.id.sidewall_et) EditText sidewallET;
    @BindView(R.id.wheel_diameter_et) EditText diameterET;
    @BindView(R.id.wheel_width_et) EditText wheelWidthET;
    @BindView(R.id.wheel_offset_et) EditText offsetET;
    @BindView(R.id.camber_et) EditText camberET;

    private Spec spec = new Spec();

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
        ButterKnife.bind(this, view);

        return view;
    }

    private void drawSpec(Spec spec, View view) {
        Log.d(TAG, "KP## Drawing spec");
        Log.d(TAG, "KP## VIEW WIDTH : " + view.getWidth());
        Log.d(TAG, "KP## VIEW HEIGHT : " + view.getHeight());

//        int viewWidth = view.getWidth();
//        int viewHeight = view.getHeight();

        int viewWidth = convertDiptoPix(getContext(), 400);
        int viewHeight = convertDiptoPix(getContext(), 400);

        Log.d(TAG, "KP## VIEW WIDTH : " + viewWidth + " | VIEW HEIGHT : " + viewHeight);

        int viewCenterX = viewWidth / 2;
        int viewCenterY = viewHeight / 2;

        Log.d(TAG, "KP## VIEW CENTER X : " + viewCenterX + " | VIEW CENTER Y : " + viewCenterY);

//        Creating colors
        int wheelColor = ContextCompat.getColor(getContext(), R.color.wheel_color);
        int tireColor = ContextCompat.getColor(getContext(), R.color.tire_color);
        int crosshairColor = ContextCompat.getColor(getContext(), R.color.crosshair_color);

//        Set up canvas
        bitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);
        specImageView.setImageBitmap(bitmap);
        canvas = new Canvas(bitmap);

//        Set up paint
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(crosshairColor);
        canvas.drawLine(viewCenterX, 0, viewCenterX, 2960, paint);
        canvas.drawLine(0, viewCenterY, 2960, viewCenterY, paint);

//        Save canvas to draw camber
        canvas.save();
        canvas.rotate(spec.getCamber(), viewCenterX, viewCenterY);

//        Draw tire
        drawTire(tireColor, viewCenterX, viewCenterY);

//        Draw wheel
        drawWheel(wheelColor, viewCenterX, viewCenterY);

//        DEBUG LINES
        canvas.restore();

        view.invalidate();
    }

    private void drawTire(int color, int viewCenterX, int viewCenterY) {
        paint.setColor(color);
        canvas.drawLines(spec.getLinePoints(viewCenterX, viewCenterY), paint);
    }

    private void drawWheel(int color, int viewCenterX, int viewCenterY) {
        paint.setColor(color);
        canvas.drawRect(spec.getWheelRect(viewCenterX, viewCenterY), paint);
        canvas.drawLines(spec.getOffsetPoints(viewCenterX, viewCenterY), paint);
    }

    private boolean checkValues() {
        boolean valid = false;
        if (!tireWidthET.getText().toString().isEmpty()
                && !sidewallET.getText().toString().isEmpty()
                && !diameterET.getText().toString().isEmpty()
                && !wheelWidthET.getText().toString().isEmpty()
                && !offsetET.getText().toString().isEmpty()
                && !camberET.getText().toString().isEmpty()) {

            if ((Integer.parseInt(tireWidthET.getText().toString()) < 1) ||
                    (Integer.parseInt(sidewallET.getText().toString()) < 1) ||
                    (Integer.parseInt(diameterET.getText().toString()) < 1) ||
                    (Float.parseFloat(wheelWidthET.getText().toString()) < 1 )) {

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
    public void onClick() {
//        drawSpec(spec, specImageView);
        if(checkValues()) {


            spec.setAllSpecs(
                    Integer.parseInt(tireWidthET.getText().toString()),
                    Integer.parseInt(sidewallET.getText().toString()),
                    Integer.parseInt(diameterET.getText().toString()),
                    Float.parseFloat(wheelWidthET.getText().toString()),
                    Float.parseFloat(offsetET.getText().toString()),
                    Float.parseFloat(camberET.getText().toString()));

            drawSpec(spec, specImageView);


        }
    }

    public int convertDiptoPix(Context context, float dip) {
        int value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
        return value;
    }
}
