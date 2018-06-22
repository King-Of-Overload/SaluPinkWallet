package com.zjut.alan.pinkwallet.tools.interpolator;

import android.view.animation.Interpolator;

/**
 * spring弹性差值器
 */
public class SpringScaleInterpolator implements Interpolator{
    //弹性因数
    private float factor;

    public SpringScaleInterpolator(float factor) {
        this.factor = factor;
    }
    @Override
    public float getInterpolation(float input) {
        return (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
    }
}
