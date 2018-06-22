package com.zjut.alan.pinkwallet.module.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.zjut.alan.pinkwallet.R;
import com.zjut.alan.pinkwallet.module.home.HomeActivity;
import com.zjut.alan.saluandroid.base.SaluRxBaseActivity;
import com.zjut.alan.saluandroid.tools.view.SaluStatusBarHelper;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * 启动页面
 * @author salu
 */
public class SplashActivity extends SaluRxBaseActivity {
    private WeakReference<Activity> splashReference;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        splashReference = new WeakReference<>(this);
        //处理状态栏颜色为白色
        SaluStatusBarHelper.tintStatusBar(splashReference.get(), getResources().getColor(R.color.white));
        SaluStatusBarHelper.setStatusBarDarkMode(splashReference.get());//dark mode
        Observable.timer(3000, TimeUnit.MILLISECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> finishTask());
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void finishTask() {
        //TODO:此处需要进行登陆判断
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
