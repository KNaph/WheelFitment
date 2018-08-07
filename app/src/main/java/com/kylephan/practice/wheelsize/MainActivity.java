package com.kylephan.practice.wheelsize;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kylephan.practice.wheelsize.view.CalculatorFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            if(findViewById(R.id.fragment_container) != null) {
                Fragment fragment = new CalculatorFragment();
                Fragment fragment1 = new CalculatorFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.fragment_container, fragment);
                ft.commit();
            }
        }

    }
}
