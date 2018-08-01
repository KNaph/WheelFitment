package com.kylephan.practice.wheelsize.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.kylephan.practice.wheelsize.R;
import com.kylephan.practice.wheelsize.model.Spec;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CalculatorFragment extends Fragment {

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

    @OnClick(R.id.submit_button)
    public void onClick() {
        if(!tireWidthET.getText().toString().isEmpty()
                && !sidewallET.getText().toString().isEmpty()
                && !diameterET.getText().toString().isEmpty()
                && !wheelWidthET.getText().toString().isEmpty()
                && !offsetET.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Should work", Toast.LENGTH_LONG).show();

            spec.setAllSpecs(
                    Integer.parseInt(tireWidthET.getText().toString()),
                    Integer.parseInt(sidewallET.getText().toString()),
                    Integer.parseInt(diameterET.getText().toString()),
                    Float.parseFloat(wheelWidthET.getText().toString()),
                    Float.parseFloat(offsetET.getText().toString()));
        } else {
            Toast.makeText(getContext(), R.string.complete_form_prompt, Toast.LENGTH_LONG).show();
        }

    }
}
