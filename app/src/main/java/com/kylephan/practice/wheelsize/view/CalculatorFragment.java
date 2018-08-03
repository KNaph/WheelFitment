package com.kylephan.practice.wheelsize.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kylephan.practice.wheelsize.R;
import com.kylephan.practice.wheelsize.customview.SpecView;
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

    @BindView(R.id.width_et) EditText tireWidthET;
    @BindView(R.id.sidewall_et) EditText sidewallET;
    @BindView(R.id.wheel_diameter_et) EditText diameterET;
    @BindView(R.id.wheel_width_et) EditText wheelWidthET;
    @BindView(R.id.wheel_offset_et) EditText offsetET;

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

        int viewWidth = view.getWidth();
        int viewHeight = view.getHeight();

        int viewCenterX = viewWidth / 2;
        int viewCenterY = viewHeight / 2;

        bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        specImageView.setImageBitmap(bitmap);
        canvas = new Canvas(bitmap);
//        canvas.drawColor(Color.red(123));


        paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
//
        int color = ContextCompat.getColor(getContext(), R.color.colorAccent);
        int color2 = ContextCompat.getColor(getContext(), R.color.colorPrimary);
        paint.setColor(color2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
//        canvas.drawColor(color2);


//        canvas.drawRect(viewCenterX - (spec.getTireWidth()/2),
//                (float) ((viewCenterY  - (spec.getWheelDiameterMM()/ 2)) - (spec.getTireWidth() * spec.getTireProfileRatio())),
//                viewCenterX + (spec.getTireWidth()/2),
//                (float) ((viewCenterY  + (spec.getWheelDiameterMM()/ 2)) + (spec.getTireWidth()* spec.getTireProfileRatio())), paint);


        canvas.drawLines(spec.getLinePoints(viewCenterX, viewCenterY), paint);


        paint.setColor(color);
        canvas.drawRect((float) (viewCenterX - (spec.getWheelWidthMM()/2)),
                (float) (viewCenterY  - (spec.getWheelDiameterMM()/ 2)),
                (float) (viewCenterX + (spec.getWheelWidthMM()/2)),
                (float) (viewCenterY  + (spec.getWheelDiameterMM()/ 2)), paint);


        view.invalidate();
    }

    private boolean checkValues() {
        boolean valid = false;
        if (!tireWidthET.getText().toString().isEmpty()
                && !sidewallET.getText().toString().isEmpty()
                && !diameterET.getText().toString().isEmpty()
                && !wheelWidthET.getText().toString().isEmpty()
                && !offsetET.getText().toString().isEmpty()) {

            if ((Integer.parseInt(tireWidthET.getText().toString()) < 1) ||
                    (Integer.parseInt(sidewallET.getText().toString()) < 1) ||
                    (Integer.parseInt(diameterET.getText().toString()) < 1) ||
                    (Float.parseFloat(wheelWidthET.getText().toString()) < 1)) {

                Toast.makeText(getContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                valid = false;
            } else {
                Toast.makeText(getContext(), "Calculating fitment", Toast.LENGTH_SHORT).show();
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
                    Float.parseFloat(offsetET.getText().toString()));

            drawSpec(spec, specImageView);
        }
    }
}
