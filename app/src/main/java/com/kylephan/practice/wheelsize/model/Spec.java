package com.kylephan.practice.wheelsize.model;

import android.util.Log;

public class Spec {

    private static final String TAG = Spec.class.getSimpleName();

    private int tireWidth;
    private int tireProfile;
    private int wheelDiameter;
    private float wheelWidth;
    private float wheelOffset;

    public Spec() {

    }

    public Spec(int tireWidth, int tireProfile, int wheelDiameter, float wheelWidth, float wheelOffset) {
        this.tireWidth = tireWidth;
        this.tireProfile = tireProfile;
        this.wheelDiameter = wheelDiameter;
        this.wheelWidth = wheelWidth;
        this.wheelOffset = wheelOffset;
    }

    public void setAllSpecs(int tireWidth, int tireProfile, int wheelDiameter, float wheelWidth, float wheelOffset) {
        this.tireWidth = tireWidth;
        this.tireProfile = tireProfile;
        this.wheelDiameter = wheelDiameter;
        this.wheelWidth = wheelWidth;
        this.wheelOffset = wheelOffset;
    }

    public int getTireWidth() {
        return tireWidth;
    }

    public void setTireWidth(int tireWidth) {
        this.tireWidth = tireWidth;
    }

    public int getTireProfile() {
        return tireProfile;
    }

    public double getTireProfileRatio() {
        return ((double)tireProfile/100);
    }

    public void setTireProfile(int tireProfile) {
        this.tireProfile = tireProfile;
    }

    public int getWheelDiameter() {
        return wheelDiameter;
    }

    public double getWheelDiameterMM() {
        return wheelDiameter * 25.4;
    }

    public void setWheelDiameter(int wheelDiameter) {
        this.wheelDiameter = wheelDiameter;
    }

    public float getWheelWidth() {
        return wheelWidth;
    }

    public double getWheelWidthMM() {
        return wheelWidth * 25.4;
    }

    public void setWheelWidth(float wheelWidth) {
        this.wheelWidth = wheelWidth;
    }

    public float getWheelOffset() {
        return wheelOffset;
    }

    public void setWheelOffset(float wheelOffset) {
        this.wheelOffset = wheelOffset;
    }
}
