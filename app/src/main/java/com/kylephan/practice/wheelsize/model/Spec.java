package com.kylephan.practice.wheelsize.model;

import android.graphics.Rect;
import android.graphics.RectF;

public class Spec {

    private static final String TAG = Spec.class.getSimpleName();

    private int tireWidth;
    private int tireProfile;
    private int wheelDiameter;
    private float wheelWidth;
    private float wheelOffset;
    private float camber;

    public Spec() {

    }

    public Spec(int tireWidth, int tireProfile, int wheelDiameter, float wheelWidth, float wheelOffset, float camber) {
        this.tireWidth = tireWidth;
        this.tireProfile = tireProfile;
        this.wheelDiameter = wheelDiameter;
        this.wheelWidth = wheelWidth;
        this.wheelOffset = wheelOffset;
        this.camber = camber;
    }

    public void setAllSpecs(int tireWidth, int tireProfile, int wheelDiameter, float wheelWidth, float wheelOffset, float camber) {
        this.tireWidth = tireWidth;
        this.tireProfile = tireProfile;
        this.wheelDiameter = wheelDiameter;
        this.wheelWidth = wheelWidth;
        this.wheelOffset = wheelOffset;
        this.camber = camber;
    }

    public float[] getLinePoints(int viewCenterX, int viewCenterY) {
        float[] points = new float[24];

        points[0] = (float) (viewCenterX - (getWheelWidthMM()/2)) + getWheelOffset();
        points[1] = (float) (viewCenterY - (getWheelDiameterMM()/2));
        points[2] = viewCenterX - (getTireWidth()/2) + getWheelOffset();
        points[3] = (float) ((viewCenterY  - (getWheelDiameterMM()/ 2)) - (getTireWidth() * getTireProfileRatio()));

        points[4] = viewCenterX - (getTireWidth()/2) + getWheelOffset();
        points[5] = (float) ((viewCenterY  - (getWheelDiameterMM()/ 2)) - (getTireWidth() * getTireProfileRatio()));
        points[6] = viewCenterX + (getTireWidth()/2) + getWheelOffset();
        points[7] = (float) ((viewCenterY  - (getWheelDiameterMM()/ 2)) - (getTireWidth() * getTireProfileRatio()));

        points[8] = viewCenterX + (getTireWidth()/2) + getWheelOffset();
        points[9] = (float) ((viewCenterY  - (getWheelDiameterMM()/ 2)) - (getTireWidth() * getTireProfileRatio()));
        points[10] = (float) (viewCenterX + (getWheelWidthMM()/2)) + getWheelOffset();
        points[11] = (float) (viewCenterY - (getWheelDiameterMM()/2));

        points[12] = (float) (viewCenterX - (getWheelWidthMM()/2)) + getWheelOffset();
        points[13] = (float) (viewCenterY + (getWheelDiameterMM()/2));
        points[14] = viewCenterX - (getTireWidth()/2) + getWheelOffset();
        points[15] = (float) ((viewCenterY  + (getWheelDiameterMM()/ 2)) + (getTireWidth() * getTireProfileRatio()));

        points[16] = viewCenterX - (getTireWidth()/2) + getWheelOffset();
        points[17] = (float) ((viewCenterY  + (getWheelDiameterMM()/ 2)) + (getTireWidth() * getTireProfileRatio()));
        points[18] = viewCenterX + (getTireWidth()/2) + getWheelOffset();
        points[19] = (float) ((viewCenterY  + (getWheelDiameterMM()/ 2)) + (getTireWidth() * getTireProfileRatio()));

        points[20] = viewCenterX + (getTireWidth()/2) + getWheelOffset();
        points[21] = (float) ((viewCenterY  + (getWheelDiameterMM()/ 2)) + (getTireWidth() * getTireProfileRatio()));
        points[22] = (float) (viewCenterX + (getWheelWidthMM()/2)) + getWheelOffset();
        points[23] = (float) (viewCenterY + (getWheelDiameterMM()/2));

        return points;
    }

    public int getTireWidth() {
        return tireWidth;
    }

    public double getTireProfileRatio() {
        return ((double)tireProfile/100);
    }

    public double getWheelDiameterMM() {
        return wheelDiameter * 25.4;
    }

    public double getWheelWidthMM() {
        return wheelWidth * 25.4;
    }

    public RectF getWheelRect(int viewCenterX, int  viewCenterY) {
        RectF rect = new RectF((float) (viewCenterX - getWheelWidthMM() / 2) + getWheelOffset(),
                (float) (viewCenterY  - (getWheelDiameterMM()/ 2)),
                (float) (viewCenterX + (getWheelWidthMM() / 2)) + getWheelOffset(),
                (float) (viewCenterY  + (getWheelDiameterMM()/ 2)));
        return rect;
    }

    public float getWheelOffset() {
        return wheelOffset;
    }

    public float[] getOffsetPoints(int viewCenterX, int viewCenterY) {
        float[] points = new float[4];
        float offsetX = viewCenterX - getWheelOffset();

        points[0] = offsetX + getWheelOffset();
        points[1] = (float) (viewCenterY - (getWheelDiameterMM() * 0.15));
        points[2] = offsetX + getWheelOffset();
        points[3] = (float) (viewCenterY + (getWheelDiameterMM() * 0.15));

        return points;
    }

    public float getCamber() {
        return (camber * -1);
    }
}
