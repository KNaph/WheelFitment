package com.kylephan.practice.wheelsize.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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

    public float[] getLinePoints(int viewCenterX, int viewCenterY) {
        float[] points = new float[24];
//        List<Float> pointsoints = new ArrayList<>();
        points[0] = (float) (viewCenterX - (getWheelWidthMM()/2));
        points[1] = (float) (viewCenterY - (getWheelDiameterMM()/2));
        points[2] = viewCenterX - (getTireWidth()/2);
        points[3] = (float) ((viewCenterY  - (getWheelDiameterMM()/ 2)) - (getTireWidth() * getTireProfileRatio()));

        points[4] = viewCenterX - (getTireWidth()/2);
        points[5] = (float) ((viewCenterY  - (getWheelDiameterMM()/ 2)) - (getTireWidth() * getTireProfileRatio()));
        points[6] = viewCenterX + (getTireWidth()/2);
        points[7] = (float) ((viewCenterY  - (getWheelDiameterMM()/ 2)) - (getTireWidth() * getTireProfileRatio()));

        points[8] = viewCenterX + (getTireWidth()/2);
        points[9] = (float) ((viewCenterY  - (getWheelDiameterMM()/ 2)) - (getTireWidth() * getTireProfileRatio()));
        points[10] = (float) (viewCenterX + (getWheelWidthMM()/2));
        points[11] = (float) (viewCenterY - (getWheelDiameterMM()/2));

        points[12] = (float) (viewCenterX - (getWheelWidthMM()/2));
        points[13] = (float) (viewCenterY + (getWheelDiameterMM()/2));
        points[14] = viewCenterX - (getTireWidth()/2);
        points[15] = (float) ((viewCenterY  + (getWheelDiameterMM()/ 2)) + (getTireWidth() * getTireProfileRatio()));

        points[16] = viewCenterX - (getTireWidth()/2);
        points[17] = (float) ((viewCenterY  + (getWheelDiameterMM()/ 2)) + (getTireWidth() * getTireProfileRatio()));
        points[18] = viewCenterX + (getTireWidth()/2);
        points[19] = (float) ((viewCenterY  + (getWheelDiameterMM()/ 2)) + (getTireWidth() * getTireProfileRatio()));

        points[20] = viewCenterX + (getTireWidth()/2);
        points[21] = (float) ((viewCenterY  + (getWheelDiameterMM()/ 2)) + (getTireWidth() * getTireProfileRatio()));
        points[22] = (float) (viewCenterX + (getWheelWidthMM()/2));
        points[23] = (float) (viewCenterY + (getWheelDiameterMM()/2));

        return points;
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
