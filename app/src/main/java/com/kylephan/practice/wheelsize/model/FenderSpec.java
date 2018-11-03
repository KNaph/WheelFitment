package com.kylephan.practice.wheelsize.model;

import android.util.Log;

public class FenderSpec {

    private static final String TAG = FenderSpec.class.getSimpleName();

    private static final int fenderLength = 150;
    private static final int perpendicular = 90;

    /**
     *              calcDepth
     *               ________
     *              |       /
     *              |     /
     *  calcHeight  |   / fenderLength
     *              |  /
     *              |/
     *
     */
    private float depth;
    private float height;
    private float angle;

    private float calcHeight;
    private float calcDepth;

    public FenderSpec() {

    }

    public FenderSpec(float depth, float height, float angle) {
        this.depth = depth;
        this.height = height;
        this.angle = perpendicular - angle;
    }

    public void setAllSpecs(float depth, float height, float angle) {
        this.depth = depth;
        this.height = height;
        this.angle = perpendicular - angle;

        calculateDepth();
        calculateHeight();
    }

    private float calculateHeight() {
        calcHeight = (float)(fenderLength * Math.sin(Math.toRadians(angle)));

        return calcHeight;
    }

    private float calculateDepth() {
        calcDepth = (float)(fenderLength * Math.cos(Math.toRadians(angle)));

        return  calcDepth;
    }

    public float[] getLinePoints(int viewCenterX, int viewCenterY) {
        float[] points = new float[4];

        points[0] = viewCenterX - depth;
        points[1] = viewCenterY - height;
        points[2] = (viewCenterX - depth) + calcDepth;
        points[3] = (viewCenterY - height) - calcHeight;

        Log.d(TAG, "KP## DEPTH : " + depth + " HEIGHT : " + height + " CALCDEPTH : " + calcDepth + " CALCHEIGHT " + calcHeight);
        Log.d(TAG, "KP## POINTS : 1: " + points[0] + " 2: " + points[1] + " 3: " + points[2] + " 4: " + points[3]);

        return points;
    }

}
